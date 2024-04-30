package android.bxt.unlock.ui.login;

import static android.app.Activity.RESULT_OK;

import android.bxt.unlock.R;
import android.bxt.unlock.base.BaseBindingFragment;
import android.bxt.unlock.databinding.FragmentLoginBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends BaseBindingFragment<FragmentLoginBinding> {

    FirebaseAuth firebaseAuth;

    SignInClient oneTapClient;

    BeginSignInRequest signInRequest;

    ActivityResultLauncher<IntentSenderRequest> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            try {
                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                String token = credential.getGoogleIdToken();
                AuthCredential authCredential = GoogleAuthProvider.getCredential(token, null);
                firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        firebaseAuth = FirebaseAuth.getInstance();
                        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment);
                    } else {
                        Snackbar.make(binding.getRoot(), "Login Failed", Snackbar.LENGTH_SHORT).show();
                    }
                });
            } catch (ApiException e) {
                Snackbar.make(binding.getRoot(), "Login Fail: " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        }
    });
    public LoginFragment() {
    }

    @Override
    protected FragmentLoginBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViews() {
        firebaseAuth = FirebaseAuth.getInstance();
        oneTapClient = Identity.getSignInClient(requireContext());
        signInRequest = buildSignInRequest();
        setupButtonLogin();
    }

    private void setupButtonLogin() {
        binding.btnLogin.setOnClickListener(view -> {
            oneTapClient.beginSignIn(signInRequest).addOnSuccessListener(requireActivity(), beginSignInResult -> {
                activityResultLauncher.launch(new IntentSenderRequest.Builder(beginSignInResult.getPendingIntent().getIntentSender()).build());
            });
        });
    }

    private BeginSignInRequest buildSignInRequest() {
        return BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(
                        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                .setSupported(true)
                                .setFilterByAuthorizedAccounts(false)
                                .setServerClientId(getString(R.string.default_web_client_id))
                                .build()
                )
                .setAutoSelectEnabled(true)
                .build();
    }

}
