package android.bxt.unlock.ui.home;

import android.annotation.SuppressLint;
import android.bxt.unlock.data.entity.Diary;
import android.bxt.unlock.data.entity.Mood;
import android.bxt.unlock.databinding.ItemDiaryBinding;
import android.bxt.unlock.utils.Constants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DiariesAdapter extends ListAdapter<Diary, DiariesAdapter.DiaryViewHolder> {
    protected DiariesAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Diary> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Diary oldItem, @NonNull Diary newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Diary oldItem, @NonNull Diary newItem) {
            return oldItem == newItem;
        }
    };

    public static class DiaryViewHolder extends RecyclerView.ViewHolder {
        ItemDiaryBinding binding;

        public DiaryViewHolder(ItemDiaryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(boolean isShowDate, Diary diary) {
            Mood mood = Constants.getMood(diary.getMood());

            int containerColor = binding.getRoot().getContext().getColor(mood.getContainerColor());
            int contentColor = binding.getRoot().getContext().getColor(mood.getContentColor());

            if (isShowDate) {
                binding.tvDate.setText(Constants.formatDate(diary.getDateCreate()));
            } else {
                binding.tvDate.setVisibility(View.GONE);
            }

            // container
            binding.containerMood.setBackgroundColor(containerColor);

            // text time
            binding.tvTime.setText(Constants.formatTime(diary.getDateCreate()));
            binding.tvTime.setTextColor(contentColor);

            // icon mood
            Glide.with(binding.imvMood).load(mood.getIcon()).into(binding.imvMood);

            // text mood
            binding.tvMood.setText(mood.name());
            binding.tvMood.setTextColor(contentColor);

            binding.tvDescription.setText(diary.getDescription());
            binding.body.setOnClickListener(view -> Navigation.findNavController(view).navigate(
                    HomeFragmentDirections.actionHomeFragmentToWriteFragment(diary)
            ));
        }
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDiaryBinding binding = ItemDiaryBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new DiaryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        Diary diary = getItem(position);
        boolean isShowDate = true;
        if (position > 0) {
            int after = getDayOfDiary(getItem(position - 1));
            int current = getDayOfDiary(diary);
            if (current == after) isShowDate = false;
        }
        holder.onBind(isShowDate, getItem(position));
    }

    public int getDayOfDiary(Diary diary) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(diary.getDateCreate());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
