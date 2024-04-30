package android.bxt.unlock.data.entity;


import android.bxt.unlock.R;

import androidx.annotation.ColorRes;

public enum Mood {
    Neutral(
            R.drawable.neutral,
            android.R.color.black,
            R.color.NeutralColor
    ),
    Happy(
            R.drawable.happy,
            android.R.color.black,
            R.color.HappyColor
    ),
    Angry(
            R.drawable.angry,
            android.R.color.white,
            R.color.AngryColor
    ),
    Bored(
            R.drawable.bored,
            android.R.color.black,
            R.color.BoredColor
    ),
    Calm(
            R.drawable.calm,
            android.R.color.black,
            R.color.CalmColor
    ),
    Depressed(
            R.drawable.depressed,
            android.R.color.black,
            R.color.DepressedColor
    ),
    Disappointed(
            R.drawable.disappointed,
            android.R.color.white,
            R.color.DisappointedColor
    ),
    Humorous(
            R.drawable.humorous,
            android.R.color.black,
            R.color.HumorousColor
    ),
    Lonely(
            R.drawable.lonely,
            android.R.color.white,
            R.color.LonelyColor
    ),
    Mysterious(
            R.drawable.mysterious,
            android.R.color.black,
            R.color.MysteriousColor
    ),
    Romantic(
            R.drawable.romantic,
            android.R.color.white,
            R.color.RomanticColor
    ),
    Shameful(
            R.drawable.shameful,
            android.R.color.white,
            R.color.ShamefulColor
    ),
    Awful(
            R.drawable.awful,
            android.R.color.black,
            R.color.AwfulColor
    ),
    Surprised(
            R.drawable.surprised,
            android.R.color.black,
            R.color.SurprisedColor
    ),
    Suspicious(
            R.drawable.suspicious,
            android.R.color.black,
            R.color.SuspiciousColor
    ),
    Tense(
            R.drawable.tense,
            android.R.color.black,
            R.color.TenseColor
    );

    private final int icon;
    @ColorRes
    private final int contentColor;
    @ColorRes
    private final int containerColor;

    Mood(int icon, int contentColor, int containerColor) {
        this.icon = icon;
        this.contentColor = contentColor;
        this.containerColor = containerColor;
    }

    public int getIcon() {
        return icon;
    }

    public int getContentColor() {
        return contentColor;
    }

    public int getContainerColor() {
        return containerColor;
    }
}

