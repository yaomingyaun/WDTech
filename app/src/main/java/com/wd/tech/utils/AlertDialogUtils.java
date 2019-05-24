package com.wd.tech.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.wd.tech.R;
import com.wd.tech.user.activity.LoginActivity;

public class AlertDialogUtils {
    Context context;
    Dialog dialog;
    private Button dialogcancel;
    private Button dialogsure;

    public AlertDialogUtils(Context mcontext) {
        context = mcontext;
        dialog = new Dialog(context, R.style.DialogTheme);
        dialog.setContentView(R.layout.dialog_layout);
        dialogcancel = dialog.findViewById(R.id.dialog_btn_cancel);
        dialogsure = dialog.findViewById(R.id.dialog_btn_sure);
        dialogsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        });
        dialogcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    public void show() {
        dialog.show();
    }
}
