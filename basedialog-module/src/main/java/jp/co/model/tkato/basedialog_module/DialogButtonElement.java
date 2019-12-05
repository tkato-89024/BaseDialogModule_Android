package jp.co.model.tkato.basedialog_module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

import static jp.co.model.tkato.basedialog_module.BaseDialogFragment.OnClickListener;

public class DialogButtonElement implements IDialogButtonElement {

    // region Parcelable / CREATOR

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public DialogButtonElement createFromParcel(Parcel in) {
            return new DialogButtonElement(in);
        }

        public DialogButtonElement[] newArray(int size) {
            return new DialogButtonElement[size];
        }
    };

    // endregion Parcelable / CREATOR

    // region property

    private String  title;
    private Integer titleId;
    private Boolean isDismiss;
    private OnClickListener listener;

    @Nullable
    @Override
    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public Integer getTitleId() {
        return titleId;
    }

    @NonNull
    @Override
    public Boolean isDismiss() {
        return isDismiss;
    }

    @Nullable
    @Override
    public OnClickListener getListener() {
        return listener;
    }

    @Override
    public IDialogButtonElement setTitle(@Nullable String value) {
        this.title = value;
        return this;
    }

    @Override
    public IDialogButtonElement setTitleId(@Nullable Integer value) {
        this.titleId = value;
        return this;
    }

    @Override
    public IDialogButtonElement setIsDismiss(@Nullable Boolean value) {
        this.isDismiss = value;
        return this;
    }

    @Override
    public IDialogButtonElement setListener(@Nullable OnClickListener value) {
        this.listener = value;

        return this;
    }

    // endregion property

    // region constructor

    DialogButtonElement() {
        this.isDismiss = true;
    }

    @SuppressWarnings("WeakerAccess")
    public DialogButtonElement(String title) {
        this.title = title;
        this.isDismiss = true;
        this.listener = null;
    }

    @SuppressWarnings("WeakerAccess")
    public DialogButtonElement(Integer titleResId) {
        this.titleId = titleResId;
        this.isDismiss = true;
        this.listener = null;
    }

    @SuppressWarnings("WeakerAccess")
    public DialogButtonElement(String title, Boolean isDismiss, OnClickListener listener) {
        this.title = title;
        this.isDismiss = (null != isDismiss) ? isDismiss : true;
        Util.validationListener(listener);
        this.listener = listener;
    }

    @SuppressWarnings("WeakerAccess")
    public DialogButtonElement(Integer titleResId, Boolean isDismiss, OnClickListener listener) {
        this.titleId = titleResId;
        this.isDismiss = (null != isDismiss) ? isDismiss : true;
        Util.validationListener(listener);
        this.listener = listener;
    }

    // region constructor / Parcelable

    @SuppressWarnings("WeakerAccess")
    public DialogButtonElement(Parcel in) {

        this.title = in.readString();

        final String titleId = in.readString();
        if (Util.isDigit(titleId)) { this.titleId = Integer.valueOf(titleId); }

        final Serializable listener = in.readSerializable();
        if (null != listener) {
            Util.validationListener(listener);
            this.listener = (OnClickListener) listener;
        }
    }

    // endregion constructor / Parcelable

    // endregion constructor

    // region implements Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeString(title);
        out.writeString(null != titleId ? String.valueOf(titleId)   : null);

        out.writeSerializable(listener);
    }

    // endregion implements Parcelable

    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + " = {"
            + "\n    title     = " + title
            + "\n    titleId   = " + titleId
            + "\n    isDismiss = " + isDismiss
            + "\n    listener  = " + listener
            + "\n}"
            ;
    }
}
