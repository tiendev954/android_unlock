package android.bxt.unlock.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseBindingFragment<T extends ViewBinding> extends Fragment {

    protected T binding;

    @NonNull
    @Override
    final public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getBinding(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    protected abstract T getBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void setupViews();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
