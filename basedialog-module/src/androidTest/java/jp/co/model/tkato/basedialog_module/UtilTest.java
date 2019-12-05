package jp.co.model.tkato.basedialog_module;

import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class UtilTest implements BaseDialogFragment.OnClickListener {

    @Rule
    public ActivityScenarioRule<BaseFragmentDialogTestActivity> rule = new ActivityScenarioRule<>(BaseFragmentDialogTestActivity.class);

    @Test
    public void test_isDigit() {

        assertTrue(Util.isDigit("43"));
        assertTrue(Util.isDigit("043"));
        assertTrue(Util.isDigit("000000"));

        ////////

        assertFalse(Util.isDigit(null));
        assertFalse(Util.isDigit(""));

        assertFalse(Util.isDigit("qrwptwq"));
        assertFalse(Util.isDigit("feo0"));
        assertFalse(Util.isDigit("5feo"));

        assertFalse(Util.isDigit("9-3459"));
        assertFalse(Util.isDigit("09-3459"));
        assertFalse(Util.isDigit("0.93459"));
        assertFalse(Util.isDigit("09.3459"));
    }

    @SuppressWarnings("all") // Result of is ignored
    @Test
    public void test_generateListenerContextType_case1_例外チェック() {

    }

    @SuppressWarnings("all")
    @Test
    public void test_generateListenerContextType_値チェック() {

        rule.getScenario().onActivity(activity -> {

            assertEquals(BaseDialogFragment.ListenerContextType.Activity, Util.generateListenerContextType(activity));
            assertEquals(BaseDialogFragment.ListenerContextType.Fragment, Util.generateListenerContextType(new Test用Fragment()));
            assertEquals(BaseDialogFragment.ListenerContextType.Null,     Util.generateListenerContextType(null));

            assertEquals(BaseDialogFragment.ListenerContextType.Other,    Util.generateListenerContextType(this));
            assertEquals(BaseDialogFragment.ListenerContextType.Other,    Util.generateListenerContextType(new BaseDialogFragment.OnClickListener() {
                @Override
                public void onClick(BaseDialogFragment.OnClickListener self, @NonNull String identifier, BaseDialogFragment.ListenerType listenerType, @NonNull DialogInterface dialog) {

                }
            }));
        });
    }

    @SuppressWarnings("all")
    @Test
    public void test_validationListener() {

        try {
            Util.validationListener(this);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        rule.getScenario().onActivity(activity -> {
            try {
                Util.validationListener(activity);
                Util.validationListener(new Test用Fragment());
                Util.validationListener(null);
            } catch (Exception e) {
                fail();
            }
        });
    }

    @Test
    public void validationListenerContextType() {

        rule.getScenario().onActivity(activity -> {

            try {
                Util.validationListenerContextType(BaseDialogFragment.ListenerContextType.Other);
                fail();
            } catch (Exception e) {
                assertTrue(true);
            }

            try {
                Util.validationListenerContextType(BaseDialogFragment.ListenerContextType.Activity);
                Util.validationListenerContextType(BaseDialogFragment.ListenerContextType.Fragment);
                Util.validationListenerContextType(BaseDialogFragment.ListenerContextType.Null);

            } catch (Exception e) {
                fail();
            }
        });
    }

    @Override
    public void onClick(BaseDialogFragment.OnClickListener self, @NonNull String identifier, BaseDialogFragment.ListenerType listenerType, @NonNull DialogInterface dialog) {

    }

    @SuppressWarnings("all")
    public static class Test用Fragment extends Fragment implements BaseDialogFragment.OnClickListener {
        @Override
        public void onClick(BaseDialogFragment.OnClickListener self, @NonNull String identifier, BaseDialogFragment.ListenerType listenerType, @NonNull DialogInterface dialog) {

        }
    }
}