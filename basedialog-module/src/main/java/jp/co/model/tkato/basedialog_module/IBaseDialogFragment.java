package jp.co.model.tkato.basedialog_module;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface IBaseDialogFragment {

    @NonNull String getIdentifier();

    @Nullable IDialogElement getElement();
    IBaseDialogFragment setElement(@NonNull IDialogElement element);

    IBaseDialogFragment setTitle(@Nullable String value);
    IBaseDialogFragment setMessage(@Nullable String value);
    IBaseDialogFragment setTitleId(@Nullable Integer value);
    IBaseDialogFragment setMessageId(@Nullable Integer value);

    IBaseDialogFragment setPositiveButton(@Nullable IDialogButtonElement value);
    IBaseDialogFragment setPositiveButton(@Nullable String title);
    IBaseDialogFragment setPositiveButton(@Nullable Integer titleResId);
    IBaseDialogFragment setPositiveButton(@Nullable String title, @Nullable BaseDialogFragment.OnClickListener listener);
    IBaseDialogFragment setPositiveButton(@Nullable Integer titleResId, @Nullable BaseDialogFragment.OnClickListener listener);
    IBaseDialogFragment setPositiveButton(@Nullable String title, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);
    IBaseDialogFragment setPositiveButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);

    IBaseDialogFragment setNegativeButton(@Nullable IDialogButtonElement value);
    IBaseDialogFragment setNegativeButton(@Nullable String title);
    IBaseDialogFragment setNegativeButton(@Nullable Integer titleResId);
    IBaseDialogFragment setNegativeButton(@Nullable String title, @Nullable BaseDialogFragment.OnClickListener listener);
    IBaseDialogFragment setNegativeButton(@Nullable Integer titleResId, @Nullable BaseDialogFragment.OnClickListener listener);
    IBaseDialogFragment setNegativeButton(@Nullable String title, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);
    IBaseDialogFragment setNegativeButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);

    IBaseDialogFragment setNeutralButton(@Nullable IDialogButtonElement value);
    IBaseDialogFragment setNeutralButton(@Nullable String title);
    IBaseDialogFragment setNeutralButton(@Nullable Integer titleResId);
    IBaseDialogFragment setNeutralButton(@Nullable String title, @Nullable BaseDialogFragment.OnClickListener listener);
    IBaseDialogFragment setNeutralButton(@Nullable Integer titleResId, @Nullable BaseDialogFragment.OnClickListener listener);
    IBaseDialogFragment setNeutralButton(@Nullable String title, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);
    IBaseDialogFragment setNeutralButton(@Nullable Integer titleResId, boolean isDismiss, @Nullable BaseDialogFragment.OnClickListener listener);

    IBaseDialogFragment applyListener();

    IBaseDialogFragment show(@NonNull final FragmentActivity activity);
    void dismiss();
}
