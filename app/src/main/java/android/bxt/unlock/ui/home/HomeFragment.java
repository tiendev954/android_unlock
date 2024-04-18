package android.bxt.unlock.ui.home;

import android.bxt.unlock.base.BaseBindingFragment;
import android.bxt.unlock.databinding.FragmentHomeBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class HomeFragment extends BaseBindingFragment<FragmentHomeBinding> {

    public HomeFragment() {}

    @Override
    protected FragmentHomeBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViews() {

    }
}
