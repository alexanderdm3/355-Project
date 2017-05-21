package com.example.android.outdoorapp;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;



public class RiverSearchActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //User Story #3 Test
    @Test
    public void goToRiverTest1() {

        ViewInteraction test = onView(withId(R.id.aCTextView));
        test.perform(replaceText("JAMES RIVER (BENT CREEK)"), closeSoftKeyboard());

        ViewInteraction test2 = onView(withId(R.id.riverConfirm));
        test2.perform(click());

        ViewInteraction test3 = onView(withId(R.id.location));
        test3.check(matches(withText("JAMES RIVER")));

    }

    //User Story #3 Test
    @Test
    public void goToRiverTest2(){
        ViewInteraction test = onView(withId(R.id.aCTextView));
        test.perform(replaceText("APPOMATTOX RIVER (FARMVILLE)"), closeSoftKeyboard());

        ViewInteraction test2 = onView(withId(R.id.riverConfirm));
        test2.perform(click());

        ViewInteraction test3 = onView(withId(R.id.location));
        test3.check(matches(withText("APPOMATTOX RIVER")));
    }

    //User Story #3 Test
    @Test
    public void goToRiverTest3(){
        ViewInteraction test = onView(withId(R.id.aCTextView));
        test.perform(replaceText("BACK CREEK (DUNDEE)"), closeSoftKeyboard());

        ViewInteraction test2 = onView(withId(R.id.riverConfirm));
        test2.perform(click());

        ViewInteraction test3 = onView(withId(R.id.location));
        test3.check(matches(withText("BACK CREEK")));
    }

    //User Story #5 Test
    @Test
    public void clickableButton() {
        ViewInteraction pushButton = onView(withId(R.id.riverConfirm));

        pushButton.perform(click());


    }

    //User Story #4 Test
    @Test
    public void swipeTest1() {

        ViewInteraction fragment = onView(withId(R.id.container));
        fragment.perform(swipeLeft());


        ViewInteraction hiking = onView((withId(R.id.textView3)));
        hiking.check(matches(withText("Enter the search radius: ")));


    }

    //User Story #4 Test
    @Test
    public void swipeTest2() {
        ViewInteraction swipeView = onView(withId(R.id.container));

        swipeView.perform(swipeLeft());
        try{
            Thread.sleep(500) ;
        }catch(InterruptedException i){
            i.printStackTrace();
        }
        swipeView.perform(swipeRight());
        try{
            Thread.sleep(500) ;
        }catch(InterruptedException i){
            i.printStackTrace();
        }
        swipeView.perform(swipeLeft());

        ViewInteraction hiking = onView((withId(R.id.textView3)));
        hiking.check(matches(withText("Enter the search radius: ")));

    }

    //User Story #4 Test
    @Test
    public void swipeTest3() {
        ViewInteraction swipeView = onView(withId(R.id.container));

        swipeView.perform(swipeLeft());
        try{
            Thread.sleep(500) ;
        }catch(InterruptedException i){
            i.printStackTrace();
        }
        swipeView.perform(swipeRight());
        try{
            Thread.sleep(500) ;
        }catch(InterruptedException i){
            i.printStackTrace();
        }
        swipeView.perform(swipeLeft());

        try{
            Thread.sleep(500) ;
        }catch(InterruptedException i){
            i.printStackTrace();
        }

        swipeView.perform(swipeRight());

        ViewInteraction river = onView((withId(R.id.textView)));
        river.check(matches(withText("Search River:")));


    }

    //User Story #5 Test
    @Test
    public void enterableText() {

        ViewInteraction textEnterable = onView(withId(R.id.aCTextView));
        textEnterable.perform(replaceText("j"), closeSoftKeyboard());

        try {
            Thread.sleep(500);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
        ViewInteraction checkText = onView(withId(R.id.aCTextView));
        checkText.check(matches(withText("j")));
    }

    //User Story #5 Test
    @Test
    public void selectableSearch() {
        ViewInteraction searchBar = onView(withId(R.id.aCTextView));

        searchBar.perform(click());

        ViewInteraction inSearchBar = onView(withId(R.id.aCTextView));

        inSearchBar.check(matches(isDisplayed()));

    }


    //User Story #5 Test
    @Test
    public void dontCrash() {

        ViewInteraction dontCrash = onView(withId(R.id.aCTextView));


        dontCrash.perform(replaceText("hey"), closeSoftKeyboard());


        ViewInteraction pushButton = onView(withId(R.id.riverConfirm));

        pushButton.perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }

        ViewInteraction checkWeStillHere = onView(withId(R.id.textView));

        checkWeStillHere.check(matches(withText("Search River:")));

    }

    //User Story #5 Test
    @Test
    public void clickSearchBar() {
        ViewInteraction searchBar = onView(withId(R.id.aCTextView));

        searchBar.perform(click());

        ViewInteraction inSearchBar = onView(withId(R.id.aCTextView));

        inSearchBar.check(matches(isDisplayed()));

    }
}
