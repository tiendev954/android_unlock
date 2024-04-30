package android.bxt.unlock.data.database;

import android.bxt.unlock.data.entity.Diary;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DiaryDatabaseService {

    private static final String DB_URL = "https://unlock-4fb5d-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private static final String NAME_DIARIES_DB = "Diaries";
    DatabaseReference reference = FirebaseDatabase.getInstance(DB_URL).getReference(NAME_DIARIES_DB);

    public void addDiary(Diary diary, ResultListener listener) {
        reference.child(diary.getDateCreate().toString()).setValue(diary).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (listener != null) {
                    listener.onSuccess();
                }
            } else {
                if (listener != null) {
                    listener.onFailed();
                }
            }
        });
    }

    public void removeDiary(Diary diary, ResultListener listener) {
        reference.child(diary.getDateCreate().toString()).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (listener != null) {
                    listener.onSuccess();
                }
            } else {
                if (listener != null) {
                    listener.onFailed();
                }
            }
        });
    }

    public void addEventListener(ValueEventListener listener) {
        reference.addValueEventListener(listener);
    }

    @Inject
    public DiaryDatabaseService() {
    }
}

