package com.example.gameofthrones;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gameofthrones.episodes.activities.EpisodeDetailActivity;
import com.example.gameofthrones.episodes.activities.EpisodesActivity;
import com.example.gameofthrones.episodes.models.Episode;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jagadeshseeram on 7/4/17.
 */

@RunWith(AndroidJUnit4.class)
public class EpisodeActivityTest {


    public static final int ITEM_CLICK_INDEX = 3;
    @Rule
    public ActivityTestRule<EpisodesActivity> mActivityRule =
            new ActivityTestRule(EpisodesActivity.class);

    /*
     * Check the Item count that was downloaded
     */
    @Test
    public void checkTheEpisodes() {
        onView(withId(R.id.episodes_list)).check(new RecyclerViewItemCountAssertion(mActivityRule.getActivity().getCount()));
    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }


    /**
     * Check the DetailsActivity that is launched from the ItemClick
     */
    @Test
    public void checkTheDetailsActivity() {
        IdlingPolicies.setIdlingResourceTimeout(5, TimeUnit.SECONDS);
        onView(withId(R.id.episodes_list))
                .perform(actionOnItemAtPosition(0, click()));
        Assert.assertEquals(getActivityInstance().getClass(), EpisodeDetailActivity.class);
    }

    /**
     * Checks the Item Click Episode title with a DetailsActivity Title
     */
    @Test
    public void checkTheDetailsActivityTitle() {
        IdlingPolicies.setIdlingResourceTimeout(5, TimeUnit.SECONDS);
        Episode episode = mActivityRule.getActivity().getEpisode(ITEM_CLICK_INDEX);
        onView(withId(R.id.episodes_list))
                .perform(actionOnItemAtPosition(ITEM_CLICK_INDEX, click()));
        Assert.assertEquals(getActivityInstance().getTitle(), episode.name);
    }

    /**
     * Get the current activity instance
     *
     * @return
     */
    private Activity getActivityInstance() {
        final Activity[] currentActivity = {null};
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity[0] = (Activity) resumedActivities.iterator().next();
                }
            }
        });
        return currentActivity[0];
    }

}
