package com.samuel.gokapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.samuel.gokapp.R;

public class ActionBarCustom extends ConstraintLayout {

    public ActionBarCustom(Context context) {
        super(context);
        initView();
    }

    public ActionBarCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ActionBarCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.actionbar_custom, this);
        setLayoutParams(new
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

}
