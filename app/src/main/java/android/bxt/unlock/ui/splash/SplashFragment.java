package android.bxt.unlock.ui.splash;

import android.bxt.unlock.R;
import android.bxt.unlock.base.BaseBindingFragment;
import android.bxt.unlock.databinding.FragmentSplashBinding;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashFragment extends BaseBindingFragment<FragmentSplashBinding> {

    private static final long SPLASH_SCREEN_DELAY = 2000;

    public SplashFragment() {
    }

    private void setupNavigateToLogin() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            NavController navController = NavHostFragment.findNavController(this);
            if (auth.getCurrentUser() != null) {
                navController.navigate(R.id.action_splashFragment_to_homeFragment);
            } else {
                navController.navigate(R.id.action_splashFragment_to_loginFragment);
            }
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
