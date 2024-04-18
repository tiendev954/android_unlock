package android.bxt.unlock.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    public final MutableLiveData<String> liveData = new MutableLiveData<>();

    @Inject
    public MainViewModel() {}

}

