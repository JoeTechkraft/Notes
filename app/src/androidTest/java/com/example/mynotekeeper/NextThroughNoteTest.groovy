package com.example.mynotekeeper
import android.content.Context
import android.view.LayoutInflater
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4.class)
@LargeTest
 class NextThroughNoteTest {
    @Rule
    /*public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule(MainActivity.class); */

    @Test
    public void NextThroughNotes() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes));

        onView(withId(R.id.list_items)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
       for (int index =0; index < notes.size(); index++) {
           NoteInfo note = notes.get(index);

           onView(withId(R.id.spinner_courses)).check(
                   matches(withSpinnerText(note.getcourse().getTitle())));
           onView(withId(R.id.text_note_title)).check(matches(withText(note.getTitle())));
           onView(withId(R.id.text_note_text)).check(matches(withText(note.getText())));

           if (index < notes.size() -1)
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click());
       }
        onView(withId(R.id.action_next)).check(matches(not(isEnabled())))
        pressBack();
    }
}
