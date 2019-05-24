package com.wd.tech.utils;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class serchutils {
    public static void clearButtonListener(final EditText et, final View view, final View view2) {
        // 取得et中的文字
        String etInputString = et.getText().toString();
        // 根据et中是否有文字进行view可见或不可见的判断(刚进入程序的判断)
        if (TextUtils.isEmpty(etInputString)) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
        //点击view时使et中的内容为空
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
                et.requestFocusFromTouch();
            }
        });
        //这部分代码起作用 小×的显示隐藏
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    view.setVisibility(View.INVISIBLE);
                    /* button.setBackgroundColor(R.drawable.colors2);*/
                } else {
                    view.setVisibility(View.VISIBLE);
                }
                //判断号码个数改变颜色
                if(s.length()>0){
                    view2.setBackgroundColor(Color.TRANSPARENT);
                }else {
                    //这个颜色要和定义时背景颜色一样（这点是灰色，你在xml中也要定义成灰色,默认灰色是#808080）
                    view2.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }
}
