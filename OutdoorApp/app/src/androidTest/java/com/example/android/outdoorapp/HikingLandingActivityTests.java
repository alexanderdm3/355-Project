package com.example.android.outdoorapp;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.android.outdoorapp.Utilities.HikingConditions;
import com.example.android.outdoorapp.Utilities.Place;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class HikingLandingActivityTests {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<HikingConditions> mActivityTestRule2 = new ActivityTestRule<>(HikingConditions.class, true, false);

    //User Story #18
    @Test
    public void hikingStatesExistTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("Hiking"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.container),
                        withParent(allOf(withId(R.id.main_content),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.stateSpin),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("<States>"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    //User Story #18
    @Test
    public void trailExistsTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("Hiking"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.container),
                        withParent(allOf(withId(R.id.main_content),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.stateSpin),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("Arizona"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.citySpin),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("Apache Junction"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.radiusField),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.radiusField),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("50"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.radiusField), withText("50"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.btnSearch),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.label), withText("Mokaac Trail"),
                        childAtPosition(
                                allOf(withId(R.id.listView),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

    }

    //User Story #18
    @Test
    public void trailPageExistsTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("Hiking"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.container),
                        withParent(allOf(withId(R.id.main_content),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.stateSpin),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("California"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.citySpin),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("Anaheim"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.radiusField),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.radiusField),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.radiusField),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("50"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.radiusField), withText("50"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.btnSearch),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.label), withText("Monte Cristo"),
                        childAtPosition(
                                withId(R.id.listView),
                                2),
                        isDisplayed()));
        appCompatTextView4.perform(click());

    }
    //User Story #19
    @Test
    public void RadiusTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("Hiking"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.container),
                        withParent(allOf(withId(R.id.main_content),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.stateSpin),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("Colorado"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.citySpin),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("Aspen"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.radiusField),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.radiusField),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("25"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.radiusField), withText("25"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.btnSearch),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

    }

}
