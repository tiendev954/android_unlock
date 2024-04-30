package android.bxt.unlock.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Diary implements Parcelable {
    private String ownerId;
    private String title;
    private Integer mood;
    private String description;
    private Long dateCreate;

    public Diary() {
    }

    public Diary(String ownerId, String title, Integer mood, String description, Long dateCreate) {
        this.ownerId = ownerId;
        this.title = title;
        this.mood = mood;
        this.description = description;
        this.dateCreate = dateCreate;
    }

    protected Diary(Parcel in) {
        ownerId = in.readString();
        title = in.readString();
        mood = in.readInt();
        description = in.readString();
        dateCreate = in.readLong();
    }

    public static final Creator<Diary> CREATOR = new Creator<Diary>() {
        @Override
        public Diary createFromParcel(Parcel in) {
            return new Diary(in);
        }

        @Override
        public Diary[] newArray(int size) {
            return new Diary[size];
        }
    };

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMood() {
        return mood;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Long dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ownerId);
        dest.writeString(title);
        dest.writeInt(mood);
        dest.writeString(description);
        dest.writeLong(dateCreate);
    }
}
