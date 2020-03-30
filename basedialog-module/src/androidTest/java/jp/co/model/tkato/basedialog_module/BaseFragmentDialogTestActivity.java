package jp.co.model.tkato.basedialog_module;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

public class BaseFragmentDialogTestActivity extends FragmentActivity implements BaseDialogFragment.OnClickListener, BaseDialogFragment.OnKeyEventListener {

    public interface OnRotationListener {
        void notifyRotation(boolean isPortrait);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // テスト用として、bundle があれば端末回転後とみなす

        Log.v(getClass().getSimpleName(), "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // fail で強制終了した場合も false だった
        Log.v(getClass().getSimpleName(), "onDestroy: isFinishing = " + this.isFinishing());
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.v(getClass().getSimpleName(), "onConfigurationChanged: newConfig = " + newConfig.orientation);
        if (null != rotationListener.get()) {
            rotationListener.get().notifyRotation(Configuration.ORIENTATION_LANDSCAPE != newConfig.orientation);
        }
    }

    private WeakReference<OnRotationListener>                 rotationListener = new WeakReference<>(null);
    private WeakReference<BaseDialogFragment.OnClickListener> delegateListener = new WeakReference<>(null);

    public void setRotationListener(OnRotationListener rotationListener) {
        this.rotationListener = new WeakReference<>(rotationListener);
    }

    public void setDelegateListener(BaseDialogFragment.OnClickListener delegateListener) {
        this.delegateListener = new WeakReference<>(delegateListener);
    }

    @Override
    public void onClick(BaseDialogFragment.OnClickListener self, @NonNull String identifier, BaseDialogFragment.ListenerType listenerType, @NonNull DialogInterface dialog) {
        if (null != this.delegateListener.get()) {
            this.delegateListener.get().onClick(this, identifier, listenerType, dialog);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v(getClass().getSimpleName(), "onKeyDown = " + keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onKey(@NonNull IBaseDialogFragment dialogFragment, int keyCode, @NonNull KeyEvent keyEvent) {
        Log.v(getClass().getSimpleName(), "onKey = " + keyCode + ", dialog id = " + dialogFragment.getIdentifier());
        dialogFragment.dismiss();
    }
}
