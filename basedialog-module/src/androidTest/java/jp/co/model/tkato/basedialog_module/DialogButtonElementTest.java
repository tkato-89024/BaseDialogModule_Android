package jp.co.model.tkato.basedialog_module;

import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import jp.co.model.tkato.basedialog_module.base.BaseInstrumentedTest;
import jp.co.model.tkato.basedialog_module.test.R;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class DialogButtonElementTest extends BaseInstrumentedTest implements BaseDialogFragment.OnClickListener {

    @Rule
    public ActivityScenarioRule<BaseFragmentDialogTestActivity> rule = new ActivityScenarioRule<>(BaseFragmentDialogTestActivity.class);

    @SuppressWarnings("all")
    @Test
    public void test_getTitle_getTitleId_case1_文字列の直接設定の場合() throws InterruptedException {

        countSetup(1);

            rule.getScenario().onActivity(activity -> {
            IDialogButtonElement element;

            final String nullString = null;
            element = new DialogButtonElement(nullString);
            assertNull(element.getTitle());

            element = new DialogButtonElement("Hoge");
            assertEquals("Hoge", element.getTitle());

            element = new DialogButtonElement("Fuga", true, activity);
            assertEquals("Fuga", element.getTitle());

            getLatch().countDown();
        });

        getLatch().await(1000, TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("all")
    @Test
    public void test_getTitle_getTitleId_case2_文字列リソースIDの設定の場合() throws InterruptedException {

        countSetup(1);

        rule.getScenario().onActivity(activity -> {

            IDialogButtonElement element;

            final Integer nullId = null;
            element = new DialogButtonElement(nullId);
            assertNull(element.getTitle());
            assertNull(element.getTitleId());

            element = new DialogButtonElement(-1);
            assertNull(element.getTitle());
            assertEquals(Integer.valueOf(-1), element.getTitleId());

            element = new DialogButtonElement(R.string.android_test_title);
            assertNull(element.getTitle());
            assertEquals(Integer.valueOf(R.string.android_test_title), element.getTitleId());

            element = new DialogButtonElement(R.string.android_test_title, true, activity);
            assertNull(element.getTitle());
            assertEquals(Integer.valueOf(R.string.android_test_title), element.getTitleId());

            getLatch().countDown();
        });

        getLatch().await(1000, TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("all")
    @Test
    public void test_isDismiss() throws InterruptedException {

        countSetup(1);

        rule.getScenario().onActivity(activity -> {

            IDialogButtonElement element;

            final Integer nullId = null;
            element = new DialogButtonElement(nullId);
            assertTrue(element.isDismiss());    // isDismiss のデフォルトは true（ダイアログを閉じる）設定

            element = new DialogButtonElement("Hoge");
            assertTrue(element.isDismiss());

            element = new DialogButtonElement("Hoge", false, activity);
            assertFalse(element.isDismiss());

            element = new DialogButtonElement("Hoge", true, activity);
            assertTrue(element.isDismiss());

            getLatch().countDown();
        });

        getLatch().await(1000, TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("all") // Value is always null
    @Test
    public void test_getListener() throws InterruptedException {

        countSetup(1);

        try {
            new DialogButtonElement("Hoge", true, this);
            fail("listener is not Activity or Fragment");
        } catch (Exception e) {
            assertTrue(true);
        }

        rule.getScenario().onActivity(activity -> {

            IDialogButtonElement element;

            final Integer nullId = null;
            element = new DialogButtonElement(nullId);
            assertNull(element.getListener());    // 設定なしでは null

            element = new DialogButtonElement("Hoge", false, null);
            assertNull(element.getListener());

            element = new DialogButtonElement("Hoge", true, activity);
            assertEquals(activity, element.getListener());

            getLatch().countDown();
        });

        getLatch().await(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onClick(BaseDialogFragment.OnClickListener self, @NonNull String identifier, BaseDialogFragment.ListenerType listenerType, @NonNull DialogInterface dialog) {

    }
}