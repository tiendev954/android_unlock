package android.bxt.unlock.ui.login;

import android.bxt.unlock.base.BaseBindingFragment;
import android.bxt.unlock.databinding.FragmentLoginBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class LoginFragment extends BaseBindingFragment<FragmentLoginBinding> {

    public LoginFragment() {
    }

    @Override
    protected FragmentLoginBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViews() {
    }
}
