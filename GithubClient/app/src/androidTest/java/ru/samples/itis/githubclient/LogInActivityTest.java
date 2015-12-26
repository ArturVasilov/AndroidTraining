package ru.samples.itis.githubclient;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Artur Vasilov
 */
@RunWith(AndroidJUnit4.class)
public class LogInActivityTest {

    /*
    @IdRes
    private static final int LOGIN_INPUT_ID = R.id.logInEdit;

    @IdRes
    private static final int PASSWORD_INPUT_ID = R.id.passwordEdit;

    @IdRes
    private static final int LOGIN_BUTTON_ID = R.id.logInButton;
    */

    @Rule
    public ActivityTestRule<LogInActivity> mActivityRule = new ActivityTestRule<>(LogInActivity.class, false, false);

    private Context mContext;

    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getContext();
        Intents.init();
    }

    /*
    @Test
    public void testUiElements() throws Exception {
        onView(withId(LOGIN_INPUT_ID)).check(matches(
                allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(""), withHint(R.string.log_in_hint))));

        onView(withId(PASSWORD_INPUT_ID)).check(matches(
                allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(""), withHint(R.string.password_hint))));

        onView(withId(LOGIN_BUTTON_ID)).check(matches(
                allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(R.string.log_in_text))));
    }

    @Test
    public void testLogInFail() throws Exception {
        onView()
    }

    @Test
    public void testLogInCorrect() throws Exception {

    }*/
}
