package com.example.adam.timemanagerultimate;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.google.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by adam on 19.3.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private final String LEAVE_TIME_STRING = "Add leaveTime";
    private final String ARRIVAL_TIME_STRIG = "Add arrivalTime";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    @Rule
    public ActivityTestRule<WorkTimeRecordListActivity> mActivityRule2 = new ActivityTestRule<>(
            WorkTimeRecordListActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
    }

    @Test
    public void doRecordButtonStateChangeTest() {
        onView(withId(R.id.doRecordButton)).check(matches(withText(ARRIVAL_TIME_STRIG)));
        onView(withId(R.id.doRecordButton))
                .perform(click())
                .check(matches(isDisplayed()));
        onView(withId(R.id.doRecordButton)).check(matches(withText(LEAVE_TIME_STRING)));
    }

    @Test
    public void checkInWorkFieldChange() {
        onView(withId(R.id.doRecordButton)).check(matches(withText(LEAVE_TIME_STRING)));
        onView(withId(R.id.doRecordButton))
                .perform(click())
                .check(matches(isDisplayed()));
        onView(withId(R.id.showStatisticsButton))
                .perform(click());
        onView(withId(R.id.goHomeButton))
                .perform(click());
        onView(withId(R.id.doRecordButton)).check(matches(withText(ARRIVAL_TIME_STRIG)));
    }

    @Test
    public void testCRUD() {
//        onView(withId(R.id.showStatisticsButton))
//                .perform(click());
//        onView(withId(R.id.workTimeRecordview)).check(matches(isDisplayed()));


    }
}