package jp.co.model.tkato.basedialog_module;

import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.io.Serializable;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface IDialogElement extends Parcelable, Serializable {

    @Nullable String  getTitle();
    @Nullable String  getMessage();
    @Nullable Integer getTitleId();
    @Nullable Integer getMessageId();
    @Nullable IDialogButtonElement getPositiveButton();
    @Nullable IDialogButtonElement getNegativeButton();
    @Nullable IDialogButtonElement getNeutralButton();


    IDialogElement setTitle(@Nullable String value);
    IDialogElement setMessage(@Nullable String value);
    IDialogElement setTitleId(@Nullable Integer value);
    IDialogElement setMessageId(@Nullable Integer value);

    IDialogElement setPositiveButton(@Nullable IDialogButtonElement value);
    IDialogElement setPositiveButton(@Nullable String title);
    IDialogElement setPositiveButton(@Nullable Integer titleResId);
    IDialogElement setPositiveButton(@Nullable String title, @Nullable BaseDialogFragment.OnClickListener listener);
    IDialogElement setPositiveButton(@Nullable Integer titleResId, @Nullable BaseDialogFragment.OnClickListener listener);
    IDialogElement setPositiveButton(@Nullable String title, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);
    IDialogElement setPositiveButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);

    IDialogElement setNegativeButton(@Nullable IDialogButtonElement value);
    IDialogElement setNegativeButton(@Nullable String title);
    IDialogElement setNegativeButton(@Nullable Integer titleResId);
    IDialogElement setNegativeButton(@Nullable String title, @Nullable BaseDialogFragment.OnClickListener listener);
    IDialogElement setNegativeButton(@Nullable Integer titleResId, @Nullable BaseDialogFragment.OnClickListener listener);
    IDialogElement setNegativeButton(@Nullable String title, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);
    IDialogElement setNegativeButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);

    IDialogElement setNeutralButton(@Nullable IDialogButtonElement value);
    IDialogElement setNeutralButton(@Nullable String title);
    IDialogElement setNeutralButton(@Nullable Integer titleResId);
    IDialogElement setNeutralButton(@Nullable String title, @Nullable BaseDialogFragment.OnClickListener listener);
    IDialogElement setNeutralButton(@Nullable Integer titleResId, @Nullable BaseDialogFragment.OnClickListener listener);
    IDialogElement setNeutralButton(@Nullable String title, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);
    IDialogElement setNeutralButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);
}
