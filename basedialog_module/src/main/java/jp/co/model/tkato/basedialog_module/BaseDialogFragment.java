package jp.co.model.tkato.basedialog_module;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unused")
public class BaseDialogFragment extends DialogFragment implements IBaseDialogFragment {

    // region enum / interface

    public enum ListenerType {
        Positive(0),
        Negative(1),
        Neutral (2),
        ;

        private int value;

        public int getValue() {
            return value;
        }

        ListenerType(int value) {
            this.value = value;
        }
    }

    enum ListenerContextType {
        Null,
        Activity,
        Fragment,
        Other,
    }

    public interface OnClickListener extends Serializable {
        void onClick(OnClickListener self, @NonNull final String identifier, ListenerType listenerType, @NonNull final DialogInterface dialog);
    }

    public interface OnRestoreListener<Parent extends FragmentActivity> {
        void onRestore(@NonNull Parent activity, @NonNull IBaseDialogFragment newDialogFragment);
    }

    // endregion enum / interface

    // region static

    // region static var

    private static final String IDENTIFIER             = "identifier";
    private static final String DIALOG_ELEMENT         = "dialogElement";

    private static final String LISTENER_TYPE_MAP      = "listenerTypeMap";
    private static final String LISTENER_TYPE_POSITIVE = "listenerTypePositive";
    private static final String LISTENER_TYPE_NEGATIVE = "listenerTypeNegative";
    private static final String LISTENER_TYPE_NEUTRAL  = "listenerTypeNeutral";


    // endregion static var

    // region static method / use

    public static <Parent extends FragmentActivity> IBaseDialogFragment use(@NonNull Parent activity, @NonNull OnRestoreListener<Parent> listener) {

        return use(activity, UUID.randomUUID().toString(), listener);
    }

    @SuppressWarnings("WeakerAccess")
    public static <Parent extends FragmentActivity> IBaseDialogFragment use(@NonNull Parent activity, @NonNull String identifier, @NonNull OnRestoreListener<Parent> listener) {

        final DialogFragment previousFragment = findDialogFragment(activity.getSupportFragmentManager(), identifier);

        if (null != previousFragment) {
            return (IBaseDialogFragment) previousFragment;
        }

        final IBaseDialogFragment newDialogFragment = new BaseDialogFragment(identifier);

        listener.onRestore(activity, newDialogFragment);

        return newDialogFragment;
    }

    // endregion static method / use

    // region static private method

    private static boolean isShowingDialogFragment(DialogFragment dialogFragment) {

        if (null != dialogFragment) {
            final Dialog dialog = dialogFragment.getDialog();
            return null != dialog && dialog.isShowing();
        }

        return false;
    }

    private static DialogFragment findDialogFragment(@NonNull FragmentManager manager, String tag) {

        final Fragment previousFragment = manager.findFragmentByTag(tag);

        if (previousFragment instanceof DialogFragment) {
            return (DialogFragment) previousFragment;
        }

        return null;
    }

    // endregion static private method

    // endregion static

    // region member

    @SuppressWarnings("all")
    @NonNull
    private String identifier;

    @Nullable
    private IDialogElement dialogElement;

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") // contents of collection are updated
    @NonNull
    private final Map<String, OnClickListener> listenerMap = new HashMap<>();

    // endregion member

    // region constructor

    private BaseDialogFragment(@NonNull String identifier) {
        this.identifier = identifier;
    }

    public BaseDialogFragment() {
        // 対策：Unable to instantiate fragment
        // Fragment には public の空コンストラクタがないと、画面回転時にエラー落ちする
    }

    // endregion constructor

    // region property

    @SuppressWarnings("WeakerAccess")
    @NonNull
    public String getIdentifier() {
        return identifier;
    }

    @Nullable
    @Override
    public IDialogElement getElement() {
        return dialogElement;
    }

    // region set

    @Override
    public IBaseDialogFragment setElement(@NonNull IDialogElement element) {
        this.dialogElement = element;
        return applyListener();
    }

    @Override
    public IBaseDialogFragment setTitle(@Nullable String value) {
        prepareElement().setTitle(value);
        return this;
    }

    @Override
    public IBaseDialogFragment setMessage(@Nullable String value) {
        prepareElement().setMessage(value);
        return this;
    }

    @Override
    public IBaseDialogFragment setTitleId(@Nullable Integer value) {
        prepareElement().setTitleId(value);
        return this;
    }

    @Override
    public IBaseDialogFragment setMessageId(@Nullable Integer value) {
        prepareElement().setMessageId(value);
        return this;
    }

    @Override
    public IBaseDialogFragment setPositiveButton(@Nullable IDialogButtonElement value) {
        prepareElement().setPositiveButton(value);
        return this;
    }

    @Override
    public IBaseDialogFragment setPositiveButton(@Nullable String title) {
        prepareButton(ListenerType.Positive)
            .setTitle(title)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setPositiveButton(@Nullable Integer titleResId) {
        prepareButton(ListenerType.Positive)
            .setTitleId(titleResId)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setPositiveButton(@Nullable String title, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Positive)
            .setTitle(title)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setPositiveButton(@Nullable Integer titleResId, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Positive)
            .setTitleId(titleResId)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setPositiveButton(@Nullable String title, boolean isDismiss, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Positive)
            .setTitle(title)
            .setIsDismiss(isDismiss)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setPositiveButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Positive)
            .setTitleId(titleResId)
            .setIsDismiss(isDismiss)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNegativeButton(@Nullable IDialogButtonElement value) {
        prepareElement().setNegativeButton(value);
        return this;
    }

    @Override
    public IBaseDialogFragment setNegativeButton(@Nullable String title) {
        prepareButton(ListenerType.Negative)
            .setTitle(title)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNegativeButton(@Nullable Integer titleResId) {
        prepareButton(ListenerType.Negative)
            .setTitleId(titleResId)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNegativeButton(@Nullable String title, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Negative)
            .setTitle(title)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNegativeButton(@Nullable Integer titleResId, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Negative)
            .setTitleId(titleResId)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNegativeButton(@Nullable String title, boolean isDismiss, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Negative)
            .setTitle(title)
            .setIsDismiss(isDismiss)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNegativeButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Negative)
            .setTitleId(titleResId)
            .setIsDismiss(isDismiss)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNeutralButton(@Nullable IDialogButtonElement value) {
        prepareElement().setNeutralButton(value);
        return this;
    }

    @Override
    public IBaseDialogFragment setNeutralButton(@Nullable String title) {
        prepareButton(ListenerType.Neutral)
            .setTitle(title)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNeutralButton(@Nullable Integer titleResId) {
        prepareButton(ListenerType.Neutral)
            .setTitleId(titleResId)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNeutralButton(@Nullable String title, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Neutral)
            .setTitle(title)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNeutralButton(@Nullable Integer titleResId, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Neutral)
            .setTitleId(titleResId)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNeutralButton(@Nullable String title, boolean isDismiss, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Neutral)
            .setTitle(title)
            .setIsDismiss(isDismiss)
            .setListener(listener)
        ;
        return this;
    }

    @Override
    public IBaseDialogFragment setNeutralButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable OnClickListener listener) {
        prepareButton(ListenerType.Neutral)
            .setTitleId(titleResId)
            .setIsDismiss(isDismiss)
            .setListener(listener)
        ;
        return this;
    }

    // endregion set

    // region listener

    @Override
    public IBaseDialogFragment applyListener() {

        if (null != dialogElement) {
            applyListener(ListenerType.Positive, dialogElement.getPositiveButton());
            applyListener(ListenerType.Negative, dialogElement.getNegativeButton());
            applyListener(ListenerType.Neutral,  dialogElement.getNeutralButton());
        }

        return this;
    }

    private void applyListener(@NonNull ListenerType listenerType, IDialogButtonElement buttonElement) {
        if (null == buttonElement) {
            return;
        }
        final OnClickListener listener = buttonElement.getListener();
        if (null == listener) {
            return;
        }

        // listener は Activity か Fragment に紐づいてない場合、画面回転時にリスナーが復活できないので、例外を先に出しておく
        final ListenerContextType listenerContextType = Util.generateListenerContextType(listener);
        Util.validationListenerContextType(listenerContextType);

        final String listenerTypeString;
        switch (listenerType) {
        case Positive: listenerTypeString = LISTENER_TYPE_POSITIVE; break;
        case Negative: listenerTypeString = LISTENER_TYPE_NEGATIVE; break;
        case Neutral:  listenerTypeString = LISTENER_TYPE_NEUTRAL;  break;
        default:
            throw new IllegalStateException("Unexpected value: " + listenerType);
        }

        // リスナーとなる Activity もしくは Fragment をメンバに保持
        this.listenerMap.put(listenerTypeString, listener);
        if (listener instanceof Fragment) {
            setTargetFragment((Fragment) listener, 0);
        } else {
            setTargetFragment(null, 0);
        }

        //
        final Bundle bundle = prepareArguments();
        bundle.putSerializable(listenerTypeString, listenerContextType);

        final HashMap<Integer, String> listenerTypeMap = prepareListenerTypes(bundle);
        listenerTypeMap.put(listenerType.getValue(), listenerTypeString);
        bundle.putSerializable(LISTENER_TYPE_MAP, listenerTypeMap);

        setArguments(bundle);
    }

    private void resetListener(Context context) {

        final Bundle bundle = prepareArguments();

        final HashMap<Integer, String> listenerTypeMap = prepareListenerTypes(bundle);

        for (Map.Entry<Integer, String> entry : listenerTypeMap.entrySet()) {

            final String listenerTypeString = entry.getValue();

            final ListenerContextType listenerContextType = (ListenerContextType) bundle.getSerializable(listenerTypeString);
            if (null == listenerContextType) {
                continue;
            }

            switch (listenerContextType) {
            case Activity:
                listenerMap.put(listenerTypeString, (OnClickListener) context);
                break;
            case Fragment:
                listenerMap.put(listenerTypeString, (OnClickListener) getTargetFragment());
                break;
            case Other:
                throw new IllegalArgumentException("The type of the listener must inherit Activity or Fragment");
            case Null:
            default:
                break;
            }
        }
    }

    // endregion listener

    // region prepare
    // get で値が取得できない場合、new で用意する

    private IDialogElement prepareElement() {

        if (null == dialogElement) {
            dialogElement = new DialogElement();
        }

        return dialogElement;
    }

    private IDialogButtonElement prepareButton(ListenerType listenerType) {

        final IDialogElement dialogElement = prepareElement();

        IDialogButtonElement buttonElement;
        switch (listenerType) {
        case Positive: {
            buttonElement = dialogElement.getPositiveButton();
            if (null == buttonElement) {
                buttonElement = dialogElement.setPositiveButton(new DialogButtonElement()).getPositiveButton();
            }
            break;
        }
        case Negative: {
            buttonElement = dialogElement.getNegativeButton();
            if (null == buttonElement) {
                buttonElement = dialogElement.setNegativeButton(new DialogButtonElement()).getNegativeButton();
            }
            break;
        }
        case Neutral: {
            buttonElement = dialogElement.getNeutralButton();
            if (null == buttonElement) {
                buttonElement = dialogElement.setNeutralButton(new DialogButtonElement()).getNeutralButton();
            }
            break;
        }
        default:
            throw new IllegalStateException("Unexpected value: " + listenerType);
        }
        return buttonElement;
    }

    @NonNull
    private Bundle prepareArguments() {
        final Bundle bundle = getArguments();
        return null != bundle ? bundle : new Bundle();
    }

    @SuppressWarnings({"unchecked", "UseSparseArrays"})
    @NonNull
    private HashMap<Integer, String> prepareListenerTypes(@NonNull Bundle bundle) {
        // SparseArray が Serializable ではないので HashMap を使用
        final HashMap<Integer, String> map = (HashMap<Integer, String>) bundle.getSerializable(LISTENER_TYPE_MAP);
        return null != map ? map : new HashMap<>();
    }

    // endregion prepare

    // endregion property

    // region show / dismiss

    @Override
    public IBaseDialogFragment show(@NonNull final FragmentActivity activity) {

        final FragmentManager fragmentManager = activity.getSupportFragmentManager();

        this.showNow(fragmentManager, identifier);

        return (IBaseDialogFragment) getCurrentDialogFragment(fragmentManager, identifier);
    }

    @Override
    public void showNow(@NonNull FragmentManager manager, @Nullable String tag) {

        final DialogFragment previousFragment = findDialogFragment(manager, tag);

        if (isShowingDialogFragment(previousFragment)) {
            return;
        }

        super.showNow(manager, identifier);
    }

    private DialogFragment getCurrentDialogFragment(@NonNull FragmentManager manager, String tag) {

        final DialogFragment previousFragment = findDialogFragment(manager, tag);

        if (null != previousFragment) {
            return previousFragment;
        }
        return this;
    }


    @SuppressWarnings("all")
    @Override
    public void dismiss() {
        // まれにあるエラー落ちの回避
        if (getShowsDialog()) {
            onDismiss(getDialog());
        }
    }

    // endregion show / dismiss

    // region customize dialog

    private Dialog createDialog(@Nullable final Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final IDialogElement element = getElement();
        if (null == element) {
            return createDialogSetup(builder.create());
        }

        final String title   = generateString(element.getTitleId(),   element.getTitle());
        final String message = generateString(element.getMessageId(), element.getMessage());
        builder
            .setTitle(title)
            .setMessage(message)
        ;

        setupDialogButtonInCreateDialog(builder, ListenerType.Positive, element.getPositiveButton());
        setupDialogButtonInCreateDialog(builder, ListenerType.Negative, element.getNegativeButton());
        setupDialogButtonInCreateDialog(builder, ListenerType.Neutral,  element.getNeutralButton());

        return createDialogSetup(builder.create());
    }

    private Dialog createDialogSetup(@NonNull final Dialog dialog) {

        // DialogFragment 使用の場合、Dialog での setCancelable は効果なし
//        dialog.setCancelable(false);
        this.setCancelable(false);

        return dialog;
    }

    private void setupDialogButtonInCreateDialog(final AlertDialog.Builder builder, final ListenerType listenerType, final IDialogButtonElement buttonElement) {

        if (null == buttonElement) {
            return;
        }

        final DialogInterface.OnClickListener dialogOnClickListener;

        if (buttonElement.isDismiss()) {

            final String dialogId = getIdentifier();

            dialogOnClickListener = (dialog, which) -> {
                final OnClickListener listener = buttonElement.getListener();
                if (null != listener) {
                    listener.onClick(listener, dialogId, listenerType, dialog);
                }
            };

        } else {
            // isDismiss = false にしている場合、ボタンのクリックリスナーの設定は、onStart 時に行うので
            // （Builder 段階でリスナーを設定すると、ボタンクリック時に必ず Dismiss するため）
            // ここでは null を設定
            dialogOnClickListener = null;
        }

        final String title = generateString(buttonElement.getTitleId(), buttonElement.getTitle());
        switch (listenerType) {
        case Positive: builder.setPositiveButton(title, dialogOnClickListener); break;
        case Negative: builder.setNegativeButton(title, dialogOnClickListener); break;
        case Neutral:  builder.setNeutralButton (title, dialogOnClickListener); break;
        }
    }

    private void didShowDialogSetup(@NonNull final Dialog dialog) {

        // show 後でないと、getDialog().getButton(...) が null を返す

        // if (null == dialog) {
        //     return;
        // }

        final IDialogElement element = getElement();
        if (null == element) {
            return;
        }

        final AlertDialog dialog_ = (AlertDialog) dialog;

        setupDialogButtonInDidShowDialogSetup(dialog_, ListenerType.Positive, element.getPositiveButton());
        setupDialogButtonInDidShowDialogSetup(dialog_, ListenerType.Negative, element.getNegativeButton());
        setupDialogButtonInDidShowDialogSetup(dialog_, ListenerType.Neutral,  element.getNeutralButton());
    }

    private void setupDialogButtonInDidShowDialogSetup(@Nullable final AlertDialog dialog, @NonNull final ListenerType listenerType, @Nullable final IDialogButtonElement buttonElement) {

        if (   (null == dialog || null == buttonElement)                                // Null チェック
            || (null == buttonElement.getTitleId() && null == buttonElement.getTitle()) // ボタンがない場合
            || buttonElement.isDismiss()                                                // リスナーが設定済
        ) {
            return;
        }

        final int buttonType;
        switch (listenerType) {
        case Positive: buttonType = DialogInterface.BUTTON_POSITIVE; break;
        case Negative: buttonType = DialogInterface.BUTTON_NEGATIVE; break;
        case Neutral:  buttonType = DialogInterface.BUTTON_NEUTRAL;  break;
        default:
            throw new IllegalStateException("Unexpected value: " + listenerType);
        }

        final Button button = dialog.getButton(buttonType);
        if (null == button) {
            return;
        }

        // ボタンタイトルが設定されている場合 ＆ isDismiss = false の場合
        // onStart 後にクリックリスナーを設定する
        // （ここでリスナーを設定すれば、ボタンクリック時に Dismiss しない）

        final String dialogId = getIdentifier();
        button.setOnClickListener(v -> {
            final OnClickListener listener = buttonElement.getListener();
            if (null != listener) {
                listener.onClick(listener, dialogId, listenerType, dialog);
            }
        });
    }

    @Nullable
    private String generateString(@Nullable Integer resId, @Nullable String title) {
        return (null != resId) ? getString(resId) : title;
    }

    // endregion customize dialog

    // region lifecycle

    // onAttach
    // onCreate
    // onCreateDialog
    // onCreateView

    // MainActivity: onStop
    // onDestroyView
    // onDestroy
    // onDetach
    // MainActivity: onDestroy

    @Override
    public void onAttach(@NonNull Context context) {
        // Timber.v("onAttach");
        super.onAttach(context);
        resetListener(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Timber.v("onCreateDialog bundle: " + savedInstanceState);
        return createDialog(savedInstanceState);
    }

    // @Nullable
    // @Override
    // public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    //     // Timber.v("onCreateView bundle: " + savedInstanceState);
    //     final View view = super.onCreateView(inflater, container, savedInstanceState);
    //     return view;
    // }

    // @Override
    // public void onActivityCreated(Bundle savedInstanceState) {
    //     // Timber.v("onActivityCreated bundle: " + savedInstanceState);
    //     super.onActivityCreated(savedInstanceState);
    // }

    @SuppressWarnings("all")
    @Override
    public void onStart() {
        // Timber.v("onStart");
        super.onStart();

        // show の後にダイアログ内の要素を設定する場合はここから取得できる
        didShowDialogSetup(getDialog());
    }

    // @Override
    // public void onDestroyView() {
    //     // Timber.v("onDestroyView");
    //     super.onDestroyView();
    // }

    // @Override
    // public void onDestroy() {
    //     // Timber.v("onDestroy");
    //     super.onDestroy();
    // }

    // @Override
    // public void onDetach() {
    //     // Timber.v("onDetach");
    //     super.onDetach();
    // }

    // region save instance state

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Timber.v("onCreate bundle = " + savedInstanceState);
        super.onCreate(savedInstanceState);

        // cling 使用時に icepick 他、annotationProcessor が使用不可のため
        // Icepick.restoreInstanceState(this, savedInstanceState);

        if (null == savedInstanceState) {
            return;
        }

        identifier        = savedInstanceState.getString (IDENTIFIER); // ConstantConditions
        dialogElement     = (IDialogElement) savedInstanceState.getSerializable(DIALOG_ELEMENT);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // Timber.v("onSaveInstanceState bundle = " + outState);
        super.onSaveInstanceState(outState);
        // cling 使用時に icepick 他、annotationProcessor が使用不可のため
        // Icepick.saveInstanceState(this, outState);

        outState.putString      (IDENTIFIER,     identifier);
        outState.putSerializable(DIALOG_ELEMENT, dialogElement);
    }

    // endregion save instance state

    // endregion lifecycle

    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + " = {"
            + "\n    element = " + dialogElement
            + "\n}"
            ;
    }
}
