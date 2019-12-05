package jp.co.model.tkato.basedialog_module;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

@SuppressWarnings("unused")
public interface IDialogButtonElement extends Parcelable, Serializable {

    @Nullable String  getTitle();
    @Nullable Integer getTitleId();
    @NonNull  Boolean isDismiss();
    @Nullable BaseDialogFragment.OnClickListener getListener();

    IDialogButtonElement setTitle(@Nullable String value);
    IDialogButtonElement setTitleId(@Nullable Integer value);
    IDialogButtonElement setIsDismiss(@Nullable Boolean value);
    IDialogButtonElement setListener(@Nullable BaseDialogFragment.OnClickListener value);
}
