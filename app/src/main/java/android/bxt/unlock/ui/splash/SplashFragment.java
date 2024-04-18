package android.bxt.unlock.ui.splash;

import android.bxt.unlock.base.BaseBindingFragment;
import android.bxt.unlock.databinding.FragmentSplashBinding;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

public class SplashFragment extends BaseBindingFragment<FragmentSplashBinding> {

    private static final long SPLASH_SCREEN_DELAY = 2000;

    public SplashFragment() {
    }

    private void setupNavigateToLogin() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            NavDirections action = SplashFragmentDirections.actionSplashFragmentToLoginFragment();
            NavHostFragment.findNavController(this).navigate(action);
        }, SPLASH_SCREEN_DELAY);
    }

    @Override
    protected FragmentSplashBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSplashBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViews() {
        setupNavigateToLogin();
    }
}
