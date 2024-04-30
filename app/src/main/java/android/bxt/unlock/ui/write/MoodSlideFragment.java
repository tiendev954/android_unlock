package android.bxt.unlock.ui.write;

import android.bxt.unlock.base.BaseBindingFragment;
import android.bxt.unlock.databinding.FragmentMoodSlideBinding;
import android.bxt.unlock.utils.Constants;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

public class MoodSlideFragment extends BaseBindingFragment<FragmentMoodSlideBinding> {

    int moodIndex;

    public static MoodSlideFragment createSlide(int index) {
        return new MoodSlideFragment(index);
    }

    public MoodSlideFragment() {
    }

    public MoodSlideFragment(int moodIndex) {
        this.moodIndex = moodIndex;
    }

    @Override
    protected FragmentMoodSlideBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMoodSlideBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViews() {
        Glide.with(binding.imvMood).load(Constants.getMood(moodIndex).getIcon()).into(binding.imvMood);
    }
}
