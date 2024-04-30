package android.bxt.unlock.utils;

import android.bxt.unlock.data.entity.Mood;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Constants {
    public static final String CREATE = "create";
    public static final String READ = "read";
    public static final String EDIT = "edit";


    public static String formatDate(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMMM dd, yyyy", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public static String formatTime(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public static Mood getMood(int index) {
        return Mood.values()[index];
    }
}
