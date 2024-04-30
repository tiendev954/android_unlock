package android.bxt.unlock.ui.home;

import android.annotation.SuppressLint;
import android.bxt.unlock.R;
import android.bxt.unlock.base.BaseBindingFragment;
import android.bxt.unlock.data.database.DiaryDatabaseService;
import android.bxt.unlock.data.entity.Diary;
import android.bxt.unlock.databinding.FragmentHomeBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseBindingFragment<FragmentHomeBinding> {

    FirebaseAuth auth;

    @Inject
    DiaryDatabaseService databaseService;

    DiariesAdapter diariesAdapter;

    public HomeFragment() {
    }

    @Override
    protected FragmentHomeBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViews() {
        auth = FirebaseAuth.getInstance();

        setupUserInfo();
        setupButtonSignOut();
        setupListDiaries();
        setupButtonCreate();

        databaseService.addEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Diary> diaries = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    diaries.add(Objects.requireNonNull(data.getValue(Diary.class)));
                }

                diaries.sort((d1, d2) -> Long.compare(d2.getDateCreate(), d1.getDateCreate()));
                diariesAdapter.submitList(diaries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupButtonCreate() {
        binding.fabCreate.setOnClickListener(view -> NavHostFragment.findNavController(this).navigate(
                HomeFragmentDirections.actionHomeFragmentToWriteFragment(null)
        ));
    }

    private void setupListDiaries() {
        diariesAdapter = new DiariesAdapter();
        binding.rvListDiaries.setAdapter(diariesAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void setupUserInfo() {
        if (auth.getCurrentUser() != null) {
            FirebaseUser user = auth.getCurrentUser();
            binding.tvName.setText("Hi, " + user.getDisplayName());
            binding.tvEmail.setText("Email: " + user.getEmail());
            Glide.with(binding.imvAvatar).load(user.getPhotoUrl()).circleCrop().into(binding.imvAvatar);
        } else {
            binding.tvName.setText("Hi, null");
        }
    }

    private void setupButtonSignOut() {
        binding.btnSignOut.setOnClickListener(view -> {
            auth.signOut();
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_splashFragment);
        });
    }

}
