package com.example.android.outdoorapp;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class WeatherActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //User Story #16 Test
    @Test
    public void weatherBackButtonTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.aCTextView),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("JAMES RIVER (BENT CREEK)"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.riverConfirm), //withText("Go"), this is an image button now
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatButton.perform(click());


        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.weatherBtn),
                        withParent(allOf(withId(R.id.activity_river_conditions),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        pressBack();

    }
}

