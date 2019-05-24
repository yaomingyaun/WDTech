package com.wd.tech.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.tech.R;

import butterknife.BindView;

public class MessagePop extends LinearLayout {
    @BindView(R.id.add_friend)
    TextView addFriend;
    @BindView(R.id.add_group)
    TextView addGroup;

    public MessagePop(Context context) {
        super(context);
    }

    public MessagePop(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.pop_message, this);
    }

    public MessagePop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

}
