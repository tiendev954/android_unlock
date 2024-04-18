package android.bxt.unlock.ui.login;

import android.bxt.unlock.R;
import android.bxt.unlock.base.BaseBindingFragment;
import android.bxt.unlock.databinding.FragmentLoginBinding;
import android.bxt.unlock.main.MainActivity;
import android.bxt.unlock.utils.FirebaseAuthManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends BaseBindingFragment<FragmentLoginBinding> implements FirebaseAuthManager.Callback {

    FirebaseAuthManager firebaseAuthManager;

    public LoginFragment() {
    }

    @Override
    protected FragmentLoginBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViews() {
        firebaseAuthManager = ((MainActivity) requireActivity()).firebaseAuthManager;
        firebaseAuthManager.addCallback(this);
        setupButtonLogin();
    }

    private void setupButtonLogin() {
        binding.btnLogin.setOnClickListener(view -> {
            firebaseAuthManager.signInGoogle();
        });
    }

    @Override
    public void onLoginSuccess() {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_homeFragment);
    }

    @Override
    public void onLoginFail(Exception e) {

    }
}
