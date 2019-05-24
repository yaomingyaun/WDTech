package com.wd.tech.message.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.app.Apis;
import com.wd.tech.message.bean.AddFrientBeans;
import com.wd.tech.message.view.HeadView;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFriendsActivity extends AppCompatActivity implements IView {

    @BindView(R.id.addfriends_head_view)
    HeadView addfriendsHeadView;
    @BindView(R.id.activity_addfrients_img)
    ImageView activityAddfrientsImg;
    @BindView(R.id.activity_addfrients_name)
    TextView activityAddfrientsName;
    @BindView(R.id.user_data)
    LinearLayout userData;
    @BindView(R.id.activity_addfrients_apply_remark)
    EditText activityAddfrientsApplyRemark;
    @BindView(R.id.activity_addfrients_add_remark)
    EditText activityAddfrientsAddRemark;
    @BindView(R.id.userActivitys_sendapply)
    Button userActivitysSendapply;
    private IPresentermpl iPresentermpl;
    private ImageView head_back;
    private TextView head_name;
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        ButterKnife.bind(this);
        head_back = addfriendsHeadView.findViewById(R.id.head_back);
        head_name = addfriendsHeadView.findViewById(R.id.head_name);
        head_name.setText("添加");
        Intent intent = getIntent();
        userid = intent.getIntExtra("userid", 0);
        String img = intent.getStringExtra("img");
        String name = intent.getStringExtra("name");
        Glide.with(AddFriendsActivity.this).load(img).into(activityAddfrientsImg);
        activityAddfrientsName.setText(name);
        iPresentermpl = new IPresentermpl(this);

    }

    @Override
    public void success(Object data) {
        AddFrientBeans addFrientBeans = (AddFrientBeans) data;
        if (addFrientBeans.getStatus().equals("1001")){
            Toast.makeText(AddFriendsActivity.this,addFrientBeans.getMessage(),Toast.LENGTH_LONG).show();
        }
        if (addFrientBeans.getStatus().equals("0000")){
            Toast.makeText(AddFriendsActivity.this,addFrientBeans.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void failure(String error) {
    }

    @OnClick({R.id.userActivitys_sendapply,R.id.head_back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.userActivitys_sendapply:
                String s = activityAddfrientsApplyRemark.getText().toString();
                iPresentermpl.postaddfrient(Apis.ADDFRIEND2, userid, s, AddFrientBeans.class);
                break;
            case R.id.head_back:
                this.finish();
                break;
        }
    }
}
