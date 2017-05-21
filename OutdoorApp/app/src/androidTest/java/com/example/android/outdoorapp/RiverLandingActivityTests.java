package com.example.android.outdoorapp;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class RiverLandingActivityTests {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //User Story #16 Test
    @Test
    public void directionsIconExistsTest() {
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
                allOf(withId(R.id.button),
                        withParent(allOf(withId(R.id.activity_river_conditions),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

    }

    //User Story #16 Test
    @Test
    public void coordinatesSelectableTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.aCTextView),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("JAMES RIVER (RICHMOND)"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.riverConfirm), //withText("Go"), this is an image button now
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.geoLocation), withText("Coordinates: -77.5469314, 37.5632022"), isDisplayed()));
        appCompatTextView.perform(longClick());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView = onView(
                allOf(withId(R.id.geoLocation), withText("Coordinates: -77.5469314, 37.5632022"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Coordinates: -77.5469314, 37.5632022")));

    }

    //User Story #16 Test
    @Test
    public void riverNameSelectableTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.aCTextView),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("JAMES RIVER (RICHMOND)"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.riverConfirm), //withText("Go"), this is an image button now
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.button),
                        childAtPosition(
                                allOf(withId(R.id.activity_river_conditions),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

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

    /**********************************************************************************
     Test Plan for User Story 16 Scenario A
     1. First open the application and type in the location, “JAMES RIVER (BENT CREEK).”
     2. Then hit the Go button and enter the River Landing activity.
     3. Highlight the river location coordinates and copy them.
     4. Open a different directions application and paste the copied coordinates to it.
     5. Verify that the pasted coordinates are the same as the coordinates displayed on the River Landing activity.

     Test Plan for User Story 16 Scenario C
     1. First open the application and type in the location, “JAMES RIVER (BENT CREEK).”
     2. Then hit the Go button and enter the River Landing activity.
     3. Highlight the river name and copy it.
     4. Open a different directions application and paste the copied coordinates to it.
     5. Verify that the pasted coordinates are the same as the coordinates displayed on the River Landing activity.
     ************************************************************************************/



    //User Story #16 Test
    @Test
    public void weatherLoadTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.aCTextView),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatAutoCompleteTextView.perform(click());

        ViewInteraction appCompatAutoCompleteTextView2 = onView(
                allOf(withId(R.id.aCTextView),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatAutoCompleteTextView2.perform(replaceText("JAMES RIVER (BENT CREEK)"), closeSoftKeyboard());

        pressBack();

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

    }

    /**********************************************************************************************
     Test Plan for User Story 2 Scenario B
     1. First open the application and type in the location, "JAMES RIVER (BENT CREEK)".
     2. Check the weather button and see if it matches the current conditions of the river (this is done externally to test by using weather.com and typing the that river location)
     3. Checking externally and confirming that the button is showing the current weather press back and wait for changes in weather conditions at the river location.
     4. Once the weather conditions have changed for a specific river reload the river using step 1 and confirm that the button has updated it's look to match the new current conditions.
     **********************************************************************************************/


    //User Story #14 Test
    @Test
    public void GageDischargeDataTest() {
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

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.riverConfirm), //withText("Go"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

    }



    //User Story #14 Test
    @Test
    public void GraphDataTest() {
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

    }

    /***********************************************************************************************
    Test Plan for User Story 14 Scenario C
    1. First open the application and type in the location, "JAMES RIVER (BENT CREEK)".
    2. By looking underneath the map and coordinates you will find the location of the discharge amount and the gage height of the river.
    3. When you first look at it has a certain discharge amount and  a certain gage height.
    4. If there is updated data when you hit back and then reload the river location by performing step 1 the gage height and and discharge amount will have changed.
    ***********************************************************************************************/
}
