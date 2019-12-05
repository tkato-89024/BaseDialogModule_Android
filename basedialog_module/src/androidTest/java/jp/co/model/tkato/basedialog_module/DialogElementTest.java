package jp.co.model.tkato.basedialog_module;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.co.model.tkato.basedialog_module.base.BaseInstrumentedTest;
import jp.co.model.tkato.basedialog_module.test.R;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class DialogElementTest extends BaseInstrumentedTest {

    @Rule
    public ActivityScenarioRule<BaseFragmentDialogTestActivity> rule = new ActivityScenarioRule<>(BaseFragmentDialogTestActivity.class);

    @SuppressWarnings("ConstantConditions")
    @Test
    public void test_getTitle_getTitleId() {

        IDialogElement element;

        final String nullTitle = null;
        element = new DialogElement(nullTitle, "Fuga");
        assertNull(element.getTitle());
        assertNull(element.getTitleId());

        element = new DialogElement("Hoge", "Fuga");
        assertEquals("Hoge", element.getTitle());
        assertNull(element.getTitleId());

        element = new DialogElement("Hoge", "Fuga", null, null, null);
        assertEquals("Hoge", element.getTitle());
        assertNull(element.getTitleId());

        element = new DialogElement(-1, -1);
        assertNull(element.getTitle());
        assertEquals(Integer.valueOf(-1), element.getTitleId());

        element = new DialogElement(R.string.android_test_title, -1);
        assertNull(element.getTitle());
        assertEquals(Integer.valueOf(R.string.android_test_title), element.getTitleId());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void test_getMessage_getMessageId() {

        IDialogElement element;

        final String nullMessage = null;
        element = new DialogElement("Hoge", nullMessage);
        assertNull(element.getMessage());
        assertNull(element.getMessageId());

        element = new DialogElement("Hoge", "Fuga");
        assertEquals("Fuga", element.getMessage());
        assertNull(element.getMessageId());

        element = new DialogElement("Hoge", "Fuga", null, null, null);
        assertEquals("Fuga", element.getMessage());
        assertNull(element.getMessageId());

        element = new DialogElement(-1, -1);
        assertNull(element.getMessage());
        assertEquals(Integer.valueOf(-1), element.getMessageId());

        element = new DialogElement(R.string.android_test_message, -1);
        assertNull(element.getMessage());
        assertEquals(Integer.valueOf(R.string.android_test_message), element.getTitleId());
    }

    @SuppressWarnings("all")
    @Test
    public void test_getPositiveButton_getNegativeButton_getNeutralButton_case1_Nullチェック() {

        IDialogElement element;

        element = new DialogElement("Hoge", "Fuga"
            , null
            , null
            , null
        );

        assertNull(element.getPositiveButton());
        assertNull(element.getNegativeButton());
        assertNull(element.getNeutralButton());


        element = new DialogElement("Hoge", "Fuga"
            , new DialogButtonElement("PositiveButton")
            , null
            , null
        );

        assertNotNull(element.getPositiveButton());
        assertNull   (element.getNegativeButton());
        assertNull   (element.getNeutralButton());

        assertEquals("PositiveButton", element.getPositiveButton().getTitle());


        element = new DialogElement("Hoge", "Fuga"
            , null
            , new DialogButtonElement("NegativeButton")
            , null
        );

        assertNull   (element.getPositiveButton());
        assertNotNull(element.getNegativeButton());
        assertNull   (element.getNeutralButton());

        assertEquals("NegativeButton", element.getNegativeButton().getTitle());


        element = new DialogElement("Hoge", "Fuga"
            , null
            , null
            , new DialogButtonElement("NeutralButton")
        );

        assertNull   (element.getPositiveButton());
        assertNull   (element.getNegativeButton());
        assertNotNull(element.getNeutralButton());

        assertEquals("NeutralButton",  element.getNeutralButton() .getTitle());


        element = new DialogElement("Hoge", "Fuga"
            , new DialogButtonElement("PositiveButton")
            , new DialogButtonElement("NegativeButton")
            , null
        );

        assertNotNull(element.getPositiveButton());
        assertNotNull(element.getNegativeButton());
        assertNull   (element.getNeutralButton());

        assertEquals("PositiveButton", element.getPositiveButton().getTitle());
        assertEquals("NegativeButton", element.getNegativeButton().getTitle());


        element = new DialogElement("Hoge", "Fuga"
            , new DialogButtonElement("PositiveButton")
            , null
            , new DialogButtonElement("NeutralButton")
        );

        assertNotNull(element.getPositiveButton());
        assertNull   (element.getNegativeButton());
        assertNotNull(element.getNeutralButton());

        assertEquals("PositiveButton", element.getPositiveButton().getTitle());
        assertEquals("NeutralButton",  element.getNeutralButton() .getTitle());


        element = new DialogElement("Hoge", "Fuga"
            , null
            , new DialogButtonElement("NegativeButton")
            , new DialogButtonElement("NeutralButton")
        );

        assertNull   (element.getPositiveButton());
        assertNotNull(element.getNegativeButton());
        assertNotNull(element.getNeutralButton());

        assertEquals("NegativeButton", element.getNegativeButton().getTitle());
        assertEquals("NeutralButton",  element.getNeutralButton() .getTitle());


        element = new DialogElement("Hoge", "Fuga"
            , new DialogButtonElement("PositiveButton")
            , new DialogButtonElement("NegativeButton")
            , new DialogButtonElement("NeutralButton")
        );

        assertNotNull(element.getPositiveButton());
        assertNotNull(element.getNegativeButton());
        assertNotNull(element.getNeutralButton());

        assertEquals("PositiveButton", element.getPositiveButton().getTitle());
        assertEquals("NegativeButton", element.getNegativeButton().getTitle());
        assertEquals("NeutralButton",  element.getNeutralButton() .getTitle());
    }


    @SuppressWarnings("all")
    @Test
    public void test_getPositiveButton_getNegativeButton_getNeutralButton_case2_Nullチェック() {

        rule.getScenario().onActivity(activity -> {

            IDialogElement element;

            element = new DialogElement("Hoge", "Fuga"
                , new DialogButtonElement("PositiveButton", true, activity)
                , null
                , null
            );
            assertNull(element.getPositiveButton().getTitleId());
            assertEquals("PositiveButton", element.getPositiveButton().getTitle());

            element = new DialogElement("Hoge", "Fuga"
                , new DialogButtonElement(R.string.android_test_button_ok, true, activity)
                , null
                , null
            );
            assertEquals(Integer.valueOf(R.string.android_test_button_ok), element.getPositiveButton().getTitleId());
            assertNull(element.getPositiveButton().getTitle());


            element = new DialogElement("Hoge", "Fuga"
                , null
                , new DialogButtonElement(R.string.android_test_button_ng, true, activity)
                , null
            );
            assertEquals(Integer.valueOf(R.string.android_test_button_ng), element.getNegativeButton().getTitleId());
            assertNull(element.getNegativeButton().getTitle());

            element = new DialogElement("Hoge", "Fuga"
                , null
                , new DialogButtonElement("NegativeButton", true, activity)
                , null
            );
            assertNull(element.getNegativeButton().getTitleId());
            assertEquals("NegativeButton", element.getNegativeButton().getTitle());


            element = new DialogElement("Hoge", "Fuga"
                , null
                , null
                , new DialogButtonElement(R.string.android_test_button_other, true, activity)
            );
            assertEquals(Integer.valueOf(R.string.android_test_button_other), element.getNeutralButton().getTitleId());
            assertNull(element.getNeutralButton().getTitle());

            element = new DialogElement("Hoge", "Fuga"
                , null
                , null
                , new DialogButtonElement("NeutralButton", true, activity)
            );
            assertNull(element.getNeutralButton().getTitleId());
            assertEquals("NeutralButton", element.getNeutralButton().getTitle());
        });
    }

    @SuppressWarnings("all")
    @Test
    public void test_setPositiveButton_setNegativeButton_setNeutralButton_優先度チェック() {

        rule.getScenario().onActivity(activity -> {

            DialogElement dialogElement;

            dialogElement = new DialogElement("Hoge", "Fuga"
                , new DialogButtonElement("PositiveButton")
                , new DialogButtonElement("NegativeButton")
                , new DialogButtonElement("NeutralButton")
            );

            dialogElement.setPositiveButton("Hoge", false, activity);
            dialogElement.setNegativeButton("Hoge", false, activity);
            dialogElement.setNeutralButton ("Hoge", false, activity);

            assertEquals("Hoge", dialogElement.getPositiveButton().getTitle());
            assertEquals("Hoge", dialogElement.getNegativeButton().getTitle());
            assertEquals("Hoge", dialogElement.getNeutralButton() .getTitle());
            assertFalse (dialogElement.getPositiveButton().isDismiss());
            assertFalse (dialogElement.getNegativeButton().isDismiss());
            assertFalse (dialogElement.getNeutralButton() .isDismiss());
            assertEquals(activity, dialogElement.getPositiveButton().getListener());
            assertEquals(activity, dialogElement.getNegativeButton().getListener());
            assertEquals(activity, dialogElement.getNeutralButton() .getListener());
        });
    }
}