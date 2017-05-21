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

public class TrailLandingActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<HikingConditions> mActivityTestRule2 = new ActivityTestRule<>(HikingConditions.class, true, false);

    //User Story #19
    @Test
    public void trailMapTest() {

        ArrayList<Place> places = new ArrayList<Place>();
        Place place1 = new Place();

        place1.setLon("-149.92248");
        place1.setLat("61.20863");
        place1.setCity("Anchorage");
        place1.setCountry("United States");
        place1.setDescription("Downtown Anchorage, 2nd street");
        place1.setName("Tony Knowles Coastal Trail");
        place1.setState("Alaska");
        places.add(0, place1);

        Intent intent = new Intent();
        intent.putExtra("place", place1);

        mActivityTestRule2.launchActivity(intent);

        ViewInteraction map = onView(withId(R.id.gmbttn));
        map.perform(click());


        try {
            Thread.sleep(500);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }


    /*
        This test shows that a trail description is displayed for a
        given trail. Since this is a mock trail the default description
        is given. User Story #18
     */
    @Test
    public void trailExistsTest() {

        ArrayList<Place> places = new ArrayList<Place>();
        Place place1 = new Place();

        place1.setLon("-149.92248");
        place1.setLat("61.20863");
        place1.setCity("Anchorage");
        place1.setCountry("United States");
        place1.setDescription("Downtown Anchorage, 2nd street");
        place1.setName("Tony Knowles Coastal Trail");
        place1.setState("Alaska");

        places.add(0, place1);

        Intent intent = new Intent();
        intent.putExtra("place", place1);

        final String trailDesc = "No Trail Data Available";
        mActivityTestRule2.launchActivity(intent);

        ViewInteraction trail = onView(withId(R.id.actTV));

        trail.check(matches(withText(trailDesc)));


        try {
            Thread.sleep(500);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }

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
    public void trailTester() {
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
                allOf(withText("Benson"), isDisplayed()));
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

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.label), withText("Fantasy Island"),
                        childAtPosition(
                                withId(R.id.listView),
                                1),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.describe), withText("All singletrack. 19 miles of trails with 6 loops. This trail is all about leaning into curves as you are mostly cornering around wavy trails thru the desert. This trail is espically beautiful in late winter and early spring after good winter rains when wildflowers abound. Created by mountain bikers for mountain bikers. Surrounding area is being developed for housing but local biking community have saved this fun gem thru activism. "), isDisplayed()));
        appCompatTextView5.perform(click());

    }

//safe
    /*
      This test shows that the current weather conditions
      can be displayed for a hiking location. User Story #6
      */
    @Test
    public void weatherTest1() {

        ArrayList<Place> places = new ArrayList<Place>();
        Place place1 = new Place();

        place1.setLon("-149.92248");
        place1.setLat("61.20863");
        place1.setCity("Anchorage");
        place1.setCountry("United States");
        place1.setDescription("Downtown Anchorage, 2nd street");
        place1.setName("Tony Knowles Coastal Trail");
        place1.setState("Alaska");
        places.add(0, place1);

        Intent intent = new Intent();
        intent.putExtra("place", place1);

        mActivityTestRule2.launchActivity(intent);

        ViewInteraction weather = onView(withId(R.id.wthr));
        weather.perform(click());


        try {
            Thread.sleep(500);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }

    /**********************************************************************************************
     Test Plan for User Story 6 Scenario B
     1. First open the application and scroll right to the hiking side, click california for state and then Anaheim as the city. Put in a search radius of 100 miles. Then select a trail that comes up.
     2. Check the weather button and see if it matches the current conditions of that hiking area (this is done externally to test by using weather.com and typing the that hiking location)
     3. Checking externally and confirming that the button is showing the current weather press back and wait for changes in weather conditions at the hiking location.
     4. Once the weather conditions have changed for a specific trail reload the same trail using step 1 and confirm that the button has updated it's look to match the new current conditions.
     **********************************************************************************************/
    //User Story #6
    @Test
    public void weatherTest3() {
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

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.wthr), isDisplayed()));
        appCompatImageButton2.perform(click());

        pressBack();


    }



}
