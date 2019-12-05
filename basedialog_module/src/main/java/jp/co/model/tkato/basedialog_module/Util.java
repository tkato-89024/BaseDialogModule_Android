package jp.co.model.tkato.basedialog_module;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import static jp.co.model.tkato.basedialog_module.BaseDialogFragment.ListenerContextType;


final class Util {

    static boolean isDigit(String value) {
        if (null == value || value.isEmpty()) {
            return false;
        }
        for (char aChar : value.toCharArray()) {
            if (!Character.isDigit(aChar)) {
                return false;
            }
        }
        return true;
    }

    @NonNull
    static ListenerContextType generateListenerContextType(@Nullable final BaseDialogFragment.OnClickListener listener) {

        if (null == listener) {
            return ListenerContextType.Null;

        } else if (listener instanceof FragmentActivity) {
            return ListenerContextType.Activity;

        } else if (listener instanceof Fragment) {
            return ListenerContextType.Fragment;
        }

        return ListenerContextType.Other;
    }

    static void validationListener(@Nullable Object listener) {

        if (null == listener) {
            return;
        }

        if (!(listener instanceof BaseDialogFragment.OnClickListener)) {
            throw new IllegalArgumentException("The type of the listener must implement BaseDialogFragment.OnClickListener");
        }

        validationListenerContextType(
            generateListenerContextType((BaseDialogFragment.OnClickListener) listener)
        );
    }

    static void validationListenerContextType(@NonNull final ListenerContextType listenerContextType) throws IllegalArgumentException {

        if (ListenerContextType.Other == listenerContextType) {
            throw new IllegalArgumentException("The type of the listener must inherit Activity or Fragment");
        }
    }
}
