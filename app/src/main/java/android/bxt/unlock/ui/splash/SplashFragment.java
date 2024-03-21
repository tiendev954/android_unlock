package android.bxt.unlock.ui.splash;

import android.bxt.unlock.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

public class SplashFragment extends Fragment {

    private static final long SPLASH_SCREEN_DELAY = 2000;

    public SplashFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupNavigateToLogin();
    }

    private void setupNavigateToLogin() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            NavDirections action = SplashFragmentDirections.actionSplashFragmentToLoginFragment();
            NavHostFragment.findNavController(this).navigate(action);
        }, SPLASH_SCREEN_DELAY);
    }
}
