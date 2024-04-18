package android.bxt.unlock.utils;

import static android.app.Activity.RESULT_OK;

import android.bxt.unlock.R;
import android.bxt.unlock.main.MainActivity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

public class FirebaseAuthManager {

    private final SignInClient onTapClient;
    private final BeginSignInRequest signInRequest;
    MainActivity activity;
    ActivityResultLauncher<IntentSenderRequest> intentSender;

    ArrayList<Callback> callbacks = new ArrayList<>();

    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    public FirebaseAuthManager(MainActivity activity) {
        this.activity = activity;
        addCallback(activity);
        onTapClient = Identity.getSignInClient(activity);
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(activity.getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(false).build()
                )
                .build();

        intentSender = activity.registerForActivityResult(
                new ActivityResultContracts.StartIntentSenderForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        try {
                            SignInCredential credential = onTapClient.getSignInCredentialFromIntent(result.getData());
                            String idToken = credential.getGoogleIdToken();
                            if (idToken != null) {
                                if (!callbacks.isEmpty()) {
                                   callbacks.forEach(Callback::onLoginSuccess);
                                }
                            } else {
                                if (!callbacks.isEmpty()) {
                                    callbacks.forEach(callback -> callback.onLoginFail(new Exception("ID TOKEN IS NULL")));
                                }
                            }
                        } catch (ApiException e) {
                            if (!callbacks.isEmpty()) {
                                callbacks.forEach(callback -> {callback.onLoginFail(e);});
                            }
                        }
                    }
                }
        );
    }

    public void signInGoogle() {
        onTapClient.beginSignIn(signInRequest).addOnSuccessListener(result -> {
            IntentSenderRequest intentSenderRequest = new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
            intentSender.launch(intentSenderRequest);
        }).addOnFailureListener(ex -> {
            if (!callbacks.isEmpty()) {
                callbacks.forEach(callback -> {callback.onLoginFail(ex);});
            }
        });
    }

    public FirebaseUser getUserInfo() {
       return auth.getCurrentUser();
    }

    public void signOut() {
        auth.signOut();
    }

    public void addCallback(Callback callback) {
        callbacks.add(callback);
    }

    public interface Callback {
        void onLoginSuccess();
        void onLoginFail(Exception e);
    }
}

