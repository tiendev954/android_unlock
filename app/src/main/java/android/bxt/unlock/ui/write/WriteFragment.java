package android.bxt.unlock.ui.write;

import android.bxt.unlock.base.BaseBindingFragment;
import android.bxt.unlock.data.database.DiaryDatabaseService;
import android.bxt.unlock.data.database.ResultListener;
import android.bxt.unlock.data.entity.Diary;
import android.bxt.unlock.data.entity.Mood;
import android.bxt.unlock.databinding.FragmentWriteBinding;
import android.bxt.unlock.utils.Constants;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WriteFragment extends BaseBindingFragment<FragmentWriteBinding> {

    FirebaseAuth auth;

    @Inject
    DiaryDatabaseService databaseService;

    SlidePagerAdapter pagerAdapter;
    long timeInMilliseconds = System.currentTimeMillis();

    Diary diary;

    public WriteFragment() {
    }

    @Override
    protected FragmentWriteBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentWriteBinding.inflate(inflater, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backToHome();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    protected void setupViews() {
        auth = FirebaseAuth.getInstance();
        setupTextDate();
        setupPageMood();
        setupButtonOnSave();
        setupButtonBack();
        setupButtonRemove();
        setupJob();
        setupButtonEdit();
    }

    private void setupButtonEdit() {
        binding.btnEdit.setOnClickListener(view -> {
            binding.buttonSave.setVisibility(View.VISIBLE);
            binding.pagerMood.setUserInputEnabled(true);
            binding.edDescription.setEnabled(true);
            binding.edTitle.setEnabled(true);
            binding.btnDelete.setVisibility(View.VISIBLE);
            binding.btnEdit.setVisibility(View.GONE);
        });
    }

    private void backToHome() {
        NavHostFragment
                .findNavController(WriteFragment.this)
                .navigate(WriteFragmentDirections.actionWriteFragmentToHomeFragment());
    }

    private void setupJob() {
        diary = WriteFragmentArgs.fromBundle(getArguments()).getDiary();
        if (diary != null) {
            String text = Constants.formatTime(diary.getDateCreate()) + " " + Constants.formatDate(diary.getDateCreate());
            binding.tvDate.setText(text);
            binding.edTitle.setText(diary.getTitle());
            binding.edDescription.setText(diary.getDescription());
            binding.edDescription.setEnabled(false);
            binding.edTitle.setEnabled(false);
            binding.pagerMood.post(() -> binding.pagerMood.setCurrentItem(diary.getMood()));
            binding.pagerMood.setUserInputEnabled(false);
            binding.buttonSave.setVisibility(View.GONE);
            binding.btnDelete.setVisibility(View.GONE);
        } else {
            binding.containerEdit.setVisibility(View.GONE);
        }
    }

    private void setupButtonRemove() {
        binding.btnDelete.setOnClickListener(view -> {
            if (diary != null) {
                databaseService.removeDiary(diary, new ResultListener() {
                    @Override
                    public void onSuccess() {
                        backToHome();
                    }

                    @Override
                    public void onFailed() {
                        Snackbar.make(binding.getRoot(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setupButtonBack() {
        binding.btnBack.setOnClickListener(view -> backToHome());
    }

    private void setupButtonOnSave() {
        binding.buttonSave.setOnClickListener(view -> {
            long date;
            if (diary != null) {
                date = diary.getDateCreate();
            } else {
                date = timeInMilliseconds;
            }
            String title = Objects.requireNonNull(binding.edTitle.getText()).toString();
            String description = Objects.requireNonNull(binding.edDescription.getText()).toString();
            if (auth.getCurrentUser() != null && !title.isBlank() && !description.isBlank()) {
                databaseService.addDiary(
                        new Diary(
                                auth.getCurrentUser().getUid(),
                                title,
                                binding.pagerMood.getCurrentItem(),
                                description,
                                date
                        ), new ResultListener() {
                            @Override
                            public void onSuccess() {
                                backToHome();
                            }

                            @Override
                            public void onFailed() {
                                Snackbar.make(binding.getRoot(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Snackbar.make(binding.getRoot(), "Check your field", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupTextDate() {
        String text = Constants.formatTime(timeInMilliseconds) + " " + Constants.formatDate(timeInMilliseconds);
        binding.tvDate.setText(text);
    }

    private void setupPageMood() {
        pagerAdapter = new SlidePagerAdapter(this);
        binding.pagerMood.setAdapter(pagerAdapter);
        binding.pagerMood.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tvMood.setText(Constants.getMood(position).name());
            }
        });
    }

    private static class SlidePagerAdapter extends FragmentStateAdapter {

        public SlidePagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MoodSlideFragment.createSlide(position);
        }

        @Override
        public int getItemCount() {
            return Mood.values().length;
        }
    }
}
