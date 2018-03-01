package views_and_presenters;

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
}
