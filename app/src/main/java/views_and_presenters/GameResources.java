package views_and_presenters;

import android.graphics.Color;

import com.example.hillcollegemac.tickettoride.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kurtis on 2/28/18.
 */

public class GameResources {

    private static Map<String, Integer> mTextColors;
    private static Map<String, Integer> mBackgroundColors;
    private static Map<String, Integer> mCardBackgrounds;
    private static Map<String, Integer> mChatBackgrounds;
    private static Map<String, Integer> mLineColors;

    static {
        mTextColors = new HashMap<>();
        mTextColors.put("red", R.color.red);
        mTextColors.put("blue", R.color.blue);
        mTextColors.put("yellow", R.color.yellow);
        mTextColors.put("green", R.color.green);
        mTextColors.put("black", R.color.black);

        mBackgroundColors = new HashMap<>();
        mBackgroundColors.put("red", R.color.trans_red);
        mBackgroundColors.put("blue", R.color.trans_blue);
        mBackgroundColors.put("yellow", R.color.trans_yellow);
        mBackgroundColors.put("green", R.color.trans_green);
        mBackgroundColors.put("black", R.color.trans_black);
        mBackgroundColors.put("gray",R.color.transparent_gray);

        mCardBackgrounds = new HashMap<>();
        mCardBackgrounds.put("red", R.drawable.red_card);
        mCardBackgrounds.put("blue", R.drawable.blue_card);
        mCardBackgrounds.put("yellow", R.drawable.yellow_card);
        mCardBackgrounds.put("green", R.drawable.green_card);
        mCardBackgrounds.put("black", R.drawable.black_card);
        mCardBackgrounds.put("orange", R.drawable.orange_card);
        mCardBackgrounds.put("purple", R.drawable.purple_card);
        mCardBackgrounds.put("white", R.drawable.white_card);
        mCardBackgrounds.put("wild", R.drawable.wild_card);

        mChatBackgrounds = new HashMap<>();
        mChatBackgrounds.put("red", R.drawable.rounded_rectangle_red);
        mChatBackgrounds.put("blue", R.drawable.rounded_rectangle_blue);
        mChatBackgrounds.put("yellow", R.drawable.rounded_rectangle_yellow);
        mChatBackgrounds.put("green", R.drawable.rounded_rectangle_green);
        mChatBackgrounds.put("black", R.drawable.rounded_rectangle_black);

        mLineColors = new HashMap<>();
        mLineColors.put("red", Color.rgb(232, 61, 12));
        mLineColors.put("blue", Color.rgb(7, 0, 191));
        mLineColors.put("yellow", Color.rgb(255, 255, 0));
        mLineColors.put("green", Color.rgb(0, 148, 3));
        mLineColors.put("black", Color.rgb(0, 0, 0));
    }

    public static Map<String, Integer> getTextColors() {
        return mTextColors;
    }

    public static Map<String, Integer> getBackgroundColors() {
        return mBackgroundColors;
    }

    public static Map<String, Integer> getCardBackground() {
        return mCardBackgrounds;
    }

    public static Map<String, Integer> getChatBackgrounds() {
        return mChatBackgrounds;
    }

    public static Map<String, Integer> getLineColors() {
        return mLineColors;
    }
}
