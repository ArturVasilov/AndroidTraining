package ru.samples.itis.githubclient;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.InputType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.samples.itis.githubclient.activity.auth.LogInActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * @author Artur Vasilov
 */
@RunWith(AndroidJUnit4.class)
public class LogInActivityTest {

    @IdRes
    private static final int CONTENT_FRAME_ID = R.id.content_frame;

    @IdRes
    private static final int LOGIN_INPUT_ID = R.id.loginEdit;

    @IdRes
    private static final int PASSWORD_INPUT_ID = R.id.passwordEdit;

    @IdRes
    private static final int LOGIN_BUTTON_ID = R.id.logInButton;

    @Rule
    public ActivityTestRule<LogInActivity> mActivityRule = new ActivityTestRule<>(LogInActivity.class);

    private Context mContext;

    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getContext();
        Intents.init();
    }

    @Test
    public void testContainerVisible() throws Exception {
        onView(withId(CONTENT_FRAME_ID)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void testUiElements() throws Exception {
        onView(withId(LOGIN_INPUT_ID)).check(matches(
                allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(""), withHint(R.string.login_hint))));

        onView(withId(PASSWORD_INPUT_ID)).check(matches(
                allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(""), withHint(R.string.password_hint),
                        withInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD))));

        onView(withId(LOGIN_BUTTON_ID)).check(matches(
                allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(R.string.log_in))));
    }

    @Test
    public void testLogInFail() throws Exception {

    }

    @Test
    public void testLogInCorrect() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}
