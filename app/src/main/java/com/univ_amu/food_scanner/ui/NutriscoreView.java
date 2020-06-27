package com.univ_amu.food_scanner.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.AppCompatTextView;
import com.univ_amu.food_scanner.R;

public class NutriscoreView extends AppCompatTextView {
    public NutriscoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextSize(24);
        setGravity(Gravity.CENTER);
        setTextColor(Color.WHITE);
    }

    public void setNutriscore(String nutriscore) {
        if (nutriscore == null){return;}
        setBackgroundColor(getContext().getResources().getColor(colorId(nutriscore), null));
        setText(nutriscore);
    }

    private int colorId(String nutriscore) {
        switch (nutriscore){
            case "A" :
                return R.color.nutriscore_A;
            case "B" :
                return R.color.nutriscore_B;
            case "C" :
                return R.color.nutriscore_C;
            case "D" :
                return R.color.nutriscore_D;
            case "E" :
                return R.color.nutriscore_E;
            default:
                return R.color.unknown_nutriscore;
        }
    }
}