package android.bxt.unlock.main;

import android.app.ActionBar;
import android.bxt.unlock.R;
import android.bxt.unlock.databinding.ActivityMainBinding;
import android.bxt.unlock.utils.FirebaseAuthManager;
import android.bxt.unlock.utils.ViewExt;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements FirebaseAuthManager.Callback {

    MainViewModel viewModel;

    ActivityMainBinding binding;

    public FirebaseAuthManager firebaseAuthManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupWindow();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        ViewExt.setLightNavStatusBar(this, false);
        firebaseAuthManager = new FirebaseAuthManager(this);
    }

    private void setupWindow() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nav_host_fragment), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onLoginSuccess() {
        Snackbar.make(binding.layoutNotify, "Login success", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFail(Exception e) {
        Snackbar.make(binding.layoutNotify, "Login fail " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
