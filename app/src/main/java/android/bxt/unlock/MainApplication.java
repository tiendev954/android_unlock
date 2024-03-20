package android.bxt.unlock;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;
import io.realm.Realm;

@HiltAndroidApp
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
