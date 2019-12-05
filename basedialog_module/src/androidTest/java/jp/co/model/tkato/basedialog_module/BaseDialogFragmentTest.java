package jp.co.model.tkato.basedialog_module;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import jp.co.model.tkato.basedialog_module.base.BaseInstrumentedTest;
import jp.co.model.tkato.basedialog_module.test.R;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BaseDialogFragmentTest extends BaseInstrumentedTest {

    private static final String DIALOG_ID_1 = "dialogId_test_getTitleId_getMessageId_getTitle_getMessage";
    private static final String DIALOG_ID_2 = "dialogId_test_show";

    @SuppressWarnings("all")
    private static final BaseDialogFragment.OnRestoreListener dialogSetup_リソースIDで設定 = (activity, newDialogFragment) -> {
        final BaseDialogFragment.OnClickListener listener = (BaseDialogFragment.OnClickListener) activity;
        newDialogFragment.setElement(new DialogElement(
            R.string.android_test_title,
            R.string.android_test_message,
            new DialogButtonElement(R.string.android_test_button_ok,    true,  listener),
            new DialogButtonElement(R.string.android_test_button_ng,    true,  listener),
            new DialogButtonElement(R.string.android_test_button_other, false, listener)
        ));
    };

    @SuppressWarnings("all")
    private static final BaseDialogFragment.OnRestoreListener dialogSetup_文字列で設定 = (activity, newDialogFragment) -> {
        final BaseDialogFragment.OnClickListener listener = (BaseDialogFragment.OnClickListener) activity;
        newDialogFragment.setElement(new DialogElement(
            "HogeTestTitle",
            "HogeTestMessage",
            new DialogButtonElement("HogeOk",    true,  listener),
            new DialogButtonElement("HogeNg",    true,  listener),
            new DialogButtonElement("HogeOther", false, listener)
        ));
    };

    @SuppressWarnings("all")
    private static final BaseDialogFragment.OnRestoreListener dialogSetup_idの上書き = (activity, newDialogFragment) -> {
        final BaseDialogFragment.OnClickListener listener = (BaseDialogFragment.OnClickListener) activity;
        newDialogFragment
            .setTitleId  (R.string.android_test_tmp)
            .setMessageId(R.string.android_test_tmp)
            .setPositiveButton(R.string.android_test_tmp, false, null)
            .setNegativeButton(R.string.android_test_tmp, false, null)
            .setNeutralButton (R.string.android_test_tmp, true,  null)

            .setTitleId  (R.string.android_test_title)
            .setMessageId(R.string.android_test_message)
            .setPositiveButton(R.string.android_test_button_ok,    true,  listener)
            .setNegativeButton(R.string.android_test_button_ng,    true,  listener)
            .setNeutralButton (R.string.android_test_button_other, false, listener)

            .setTitle  ("set 系は後から set されたものが優先されるが、Title よりも TitleId の方が優先されて表示される")
            .setMessage("set 系は後から set されたものが優先されるが、Message よりも Message の方が優先されて表示される")
        ;
    };


    @Rule
    public ActivityScenarioRule<BaseFragmentDialogTestActivity> rule = new ActivityScenarioRule<>(BaseFragmentDialogTestActivity.class);

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @SuppressWarnings("all")
    @Test
    public void test_toString() {

        rule.getScenario().onActivity(activity -> {

            final BaseDialogFragment dialogFragment = new BaseDialogFragment();

            dialogFragment.setElement(new DialogElement(
                "HogeTestTitle",
                "HogeTestMessage",
                new DialogButtonElement("HogeOk",    true,  activity),
                new DialogButtonElement("HogeNg",    false,  null),
                null
            ));
            dialogFragment.setTitleId(R.string.android_test_title);

            Log.v(getClass().getSimpleName(), "Element のログ:\n" + dialogFragment);
        });
    }

    @SuppressWarnings("all")
    @Test
    public void test_getTitleId_getMessageId_getTitle_getMessage() throws InterruptedException {

        countSetup(1);

        rule.getScenario().onActivity(activity -> {
            IBaseDialogFragment fragment;
            IDialogElement      element;

            fragment = BaseDialogFragment
                .use(activity, "90r2ehf", dialogSetup_リソースIDで設定)
            ;
            element = fragment.getElement();
            assertNotNull(element);
            assertEquals(Integer.valueOf(R.string.android_test_title),        element.getTitleId());
            assertEquals(Integer.valueOf(R.string.android_test_message),      element.getMessageId());
            assertEquals(Integer.valueOf(R.string.android_test_button_ok),    element.getPositiveButton().getTitleId());
            assertEquals(Integer.valueOf(R.string.android_test_button_ng),    element.getNegativeButton().getTitleId());
            assertEquals(Integer.valueOf(R.string.android_test_button_other), element.getNeutralButton().getTitleId());
            assertNull  (element.getTitle());
            assertNull  (element.getMessage());
            assertNull  (element.getPositiveButton().getTitle());
            assertNull  (element.getNegativeButton().getTitle());
            assertNull  (element.getNeutralButton().getTitle());


            fragment = BaseDialogFragment
                .use(activity, "09-ewjvewv", dialogSetup_文字列で設定)
            ;
            element = fragment.getElement();
            assertNotNull(element);
            assertEquals("HogeTestTitle",   element.getTitle());
            assertEquals("HogeTestMessage", element.getMessage());
            assertEquals("HogeOk",          element.getPositiveButton().getTitle());
            assertEquals("HogeNg",          element.getNegativeButton().getTitle());
            assertEquals("HogeOther",       element.getNeutralButton().getTitle());
            assertNull  (element.getTitleId());
            assertNull  (element.getMessageId());
            assertNull  (element.getPositiveButton().getTitleId());
            assertNull  (element.getNegativeButton().getTitleId());
            assertNull  (element.getNeutralButton().getTitleId());


            fragment = BaseDialogFragment
                .use(activity, "['';90e", dialogSetup_idの上書き)
            ;
            element = fragment.getElement();
            assertNotNull(element);
            assertEquals (Integer.valueOf(R.string.android_test_title),        element.getTitleId());
            assertEquals (Integer.valueOf(R.string.android_test_message),      element.getMessageId());
            assertEquals (Integer.valueOf(R.string.android_test_button_ok),    element.getPositiveButton().getTitleId());
            assertEquals (Integer.valueOf(R.string.android_test_button_ng),    element.getNegativeButton().getTitleId());
            assertEquals (Integer.valueOf(R.string.android_test_button_other), element.getNeutralButton().getTitleId());
            assertNotNull(element.getTitle());
            assertNotNull(element.getMessage());

            getLatch().countDown();
        });

        getLatch().await(1000, TimeUnit.MILLISECONDS);
    }

    private boolean test_show_isPositived = false;
    private boolean test_show_isNegatived = false;

    @SuppressWarnings("all")
    @Test
    public void test_show() throws InterruptedException {

        countSetup(2);

        rule.getScenario().onActivity(activity -> {

            Log.v(getClass().getSimpleName(), "test_show");

            IBaseDialogFragment fragment;

            activity.setRotationListener(this.rotationListener);
            activity.setDelegateListener(this.testListener);

            fragment = BaseDialogFragment
                .use(activity, DIALOG_ID_2, (activity_, newDialogFragment) -> {
                    newDialogFragment.setElement(new DialogElement(
                        "Test show",
                        "端末を回転させてた後、ボタン１・ボタン２をタップすることでテスト成功です。",
                        new DialogButtonElement("ボタン１",   false, activity_),
                        new DialogButtonElement("ボタン２",   false, activity_),
                        new DialogButtonElement("テストスキップ", false, activity_)
                    ));
                })
            ;
            fragment.show(activity);
        });

        getLatch().await();

        if (null != test_show_isSucceeded && false == test_show_isSucceeded) {
            fail("test skip");
        }
    }

    private Boolean test_show_isSucceeded = null;
    private Boolean test_show_isPortrait  = null;

    private BaseFragmentDialogTestActivity.OnRotationListener rotationListener = isPortrait -> test_show_isPortrait = isPortrait;

    @SuppressWarnings("all")
    private BaseDialogFragment.OnClickListener testListener = (self, identifier, listenerType, dialog) -> {

        Log.i(getClass().getSimpleName(), "onClick: " + listenerType);
        Log.i(getClass().getSimpleName(), "onClick: test_show_isPortrait = " + test_show_isPortrait);

        if (!DIALOG_ID_2.equals(identifier)) {
            return;
        }

        switch (listenerType) {
        case Neutral:
            test_show_isSucceeded = false;
            countFinish();
            return;
        }

        // 端末が回転していない限り、ボタンタップしても無反応
        if (null == test_show_isPortrait) {
            return;
        }

        switch (listenerType) {
        case Positive:
            if (test_show_isPositived) {
                return;
            }
            test_show_isPositived = true;
            getLatch().countDown();
            break;
        case Negative:
            if (test_show_isNegatived) {
                return;
            }
            test_show_isNegatived = true;
            getLatch().countDown();
            break;
        }

        if (0 >= getLatch().getCount()) {
            test_show_isSucceeded = true;
        }
    };
}
