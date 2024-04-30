package android.bxt.unlock.main;

import android.bxt.unlock.R;
import android.bxt.unlock.databinding.ActivityMainBinding;
import android.bxt.unlock.utils.ViewExt;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.FirebaseApp;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity{

    MainViewModel viewModel;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupWindow();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        ViewExt.setLightNavStatusBar(this, false);
        FirebaseApp.initializeApp(this);

    }

    private void setupWindow() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nav_host_fragment), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
