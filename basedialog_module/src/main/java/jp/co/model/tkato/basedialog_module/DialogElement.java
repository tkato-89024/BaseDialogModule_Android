package jp.co.model.tkato.basedialog_module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings("unused")
public class DialogElement implements IDialogElement {

    // region Parcelable / CREATOR

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public DialogElement createFromParcel(Parcel in) {
            return new DialogElement(in);
        }

        public DialogElement[] newArray(int size) {
            return new DialogElement[size];
        }
    };

    // endregion Parcelable / CREATOR

    // region property

    private String  title;
    private String  message;
    private Integer titleId;
    private Integer messageId;
    private IDialogButtonElement positiveButton;
    private IDialogButtonElement negativeButton;
    private IDialogButtonElement neutralButton;

    @Nullable
    @Override
    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    @Nullable
    @Override
    public Integer getTitleId() {
        return titleId;
    }

    @Nullable
    @Override
    public Integer getMessageId() {
        return messageId;
    }

    @Nullable
    @Override
    public IDialogButtonElement getPositiveButton() {
        return positiveButton;
    }

    @Nullable
    @Override
    public IDialogButtonElement getNegativeButton() {
        return negativeButton;
    }

    @Nullable
    @Override
    public IDialogButtonElement getNeutralButton() {
        return neutralButton;
    }

    @Override
    public IDialogElement setTitle(@Nullable String value) {
        this.title = value;
        return this;
    }

    @Override
    public IDialogElement setMessage(@Nullable String value) {
        this.message = value;
        return this;
    }

    @Override
    public IDialogElement setTitleId(@Nullable Integer value) {
        this.titleId = value;
        return this;
    }

    @Override
    public IDialogElement setMessageId(@Nullable Integer value) {
        this.messageId = value;
        return this;
    }

    // region set / positive button

    @Override
    public IDialogElement setPositiveButton(@Nullable IDialogButtonElement value) {
        this.positiveButton = value;
        return this;
    }


    @Override
    public IDialogElement setPositiveButton(@Nullable String title) {
        this.positiveButton = new DialogButtonElement(title);
        return this;
    }

    @Override
    public IDialogElement setPositiveButton(@Nullable Integer titleResId) {
        this.positiveButton = new DialogButtonElement(titleResId);
        return this;
    }

    @Override
    public IDialogElement setPositiveButton(@Nullable String title, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.positiveButton = new DialogButtonElement(title, true, listener);
        return this;
    }

    @Override
    public IDialogElement setPositiveButton(@Nullable Integer titleResId, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.positiveButton = new DialogButtonElement(titleResId, true, listener);
        return this;
    }

    @Override
    public IDialogElement setPositiveButton(@Nullable String title, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.positiveButton = new DialogButtonElement(title, isDismiss, listener);
        return this;
    }

    @Override
    public IDialogElement setPositiveButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.positiveButton = new DialogButtonElement(titleResId, isDismiss, listener);
        return this;
    }

    // endregion set / positive button

    // region set / negative button

    @Override
    public IDialogElement setNegativeButton(@Nullable IDialogButtonElement value) {
        this.negativeButton = value;
        return this;
    }

    @Override
    public IDialogElement setNegativeButton(@Nullable String title) {
        this.negativeButton = new DialogButtonElement(title);
        return this;
    }

    @Override
    public IDialogElement setNegativeButton(@Nullable Integer titleResId) {
        this.negativeButton = new DialogButtonElement(titleResId);
        return this;
    }

    @Override
    public IDialogElement setNegativeButton(@Nullable String title, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.negativeButton = new DialogButtonElement(title, true, listener);
        return this;
    }

    @Override
    public IDialogElement setNegativeButton(@Nullable Integer titleResId, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.negativeButton = new DialogButtonElement(titleResId, true, listener);
        return this;
    }

    @Override
    public IDialogElement setNegativeButton(@Nullable String title, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.negativeButton = new DialogButtonElement(title, isDismiss, listener);
        return this;
    }

    @Override
    public IDialogElement setNegativeButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.negativeButton = new DialogButtonElement(titleResId, isDismiss, listener);
        return this;
    }

    // endregion set / negative button

    // region set / neutral button

    @Override
    public IDialogElement setNeutralButton(@Nullable IDialogButtonElement value) {
        this.neutralButton = value;
        return this;
    }

    @Override
    public IDialogElement setNeutralButton(@Nullable String title) {
        this.neutralButton = new DialogButtonElement(title);
        return this;
    }

    @Override
    public IDialogElement setNeutralButton(@Nullable Integer titleResId) {
        this.neutralButton = new DialogButtonElement(titleResId);
        return this;
    }

    @Override
    public IDialogElement setNeutralButton(@Nullable String title, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.neutralButton = new DialogButtonElement(title, true, listener);
        return this;
    }

    @Override
    public IDialogElement setNeutralButton(@Nullable Integer titleResId, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.neutralButton = new DialogButtonElement(titleResId, true, listener);
        return this;
    }

    @Override
    public IDialogElement setNeutralButton(@Nullable String title, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.neutralButton = new DialogButtonElement(title, isDismiss, listener);
        return this;
    }

    @Override
    public IDialogElement setNeutralButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener) {
        this.neutralButton = new DialogButtonElement(titleResId, isDismiss, listener);
        return this;
    }

    // endregion set / neutral button

    // endregion property

    // region constructor

    DialogElement() {
    }

    @SuppressWarnings("WeakerAccess")
    public DialogElement(String title, String message) {
        this.title = title;
        this.message = message;
        this.positiveButton = null;
        this.negativeButton = null;
        this.neutralButton = null;
    }

    @SuppressWarnings("WeakerAccess")
    public DialogElement(Integer titleResId, Integer messageResId) {
        this.titleId = titleResId;
        this.messageId = messageResId;
        this.positiveButton = null;
        this.negativeButton = null;
        this.neutralButton = null;
    }

    @SuppressWarnings("WeakerAccess")
    public DialogElement(String title, String message, IDialogButtonElement positiveButton, IDialogButtonElement negativeButton, IDialogButtonElement neutralButton) {
        this.title = title;
        this.message = message;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.neutralButton = neutralButton;
    }

    @SuppressWarnings("WeakerAccess")
    public DialogElement(Integer titleResId, Integer messageResId, IDialogButtonElement positiveButton, IDialogButtonElement negativeButton, IDialogButtonElement neutralButton) {
        this.titleId = titleResId;
        this.messageId = messageResId;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.neutralButton = neutralButton;
    }

    @SuppressWarnings("WeakerAccess")
    public DialogElement(Parcel in) {

        this.title   = in.readString();
        this.message = in.readString();

        final String titleId   = in.readString();
        if (Util.isDigit(titleId))   { this.titleId = Integer.valueOf(titleId);   }
        final String messageId = in.readString();
        if (Util.isDigit(messageId)) { this.titleId = Integer.valueOf(messageId); }

        final ClassLoader classLoader = IDialogButtonElement.class.getClassLoader();
        this.positiveButton = in.readParcelable(classLoader);
        this.negativeButton = in.readParcelable(classLoader);
        this.neutralButton  = in.readParcelable(classLoader);
    }

    // endregion constructor

    // region implements Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeString(title);
        out.writeString(message);
        out.writeString(null != titleId   ? String.valueOf(titleId)   : null);
        out.writeString(null != messageId ? String.valueOf(messageId) : null);

        out.writeParcelable(positiveButton, 0);
        out.writeParcelable(negativeButton, 0);
        out.writeParcelable(neutralButton,  0);
    }

    // endregion implements Parcelable

    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + " = {"
            + "\n    title     = " + title
            + "\n    messaage  = " + message
            + "\n    titleId   = " + titleId
            + "\n    messageId = " + messageId
            + "\n    positive  = " + positiveButton
            + "\n    negative  = " + negativeButton
            + "\n    neutral   = " + neutralButton
            + "\n}"
            ;
    }
}
