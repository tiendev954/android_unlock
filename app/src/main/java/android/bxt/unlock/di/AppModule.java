package android.bxt.unlock.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class AppModule {

    @Provides
   public static String provideString() {
        return "inject test";
    }
}
