package com.wd.tech.message.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.app.Apis;
import com.wd.tech.message.bean.FindUserBeans;
import com.wd.tech.message.view.HeadView;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.serchutils;
import com.wd.tech.utils.view.IView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 根据手机号查询好友
 */
public class AddFriendActivity extends AppCompatActivity implements IView {

    @BindView(R.id.addfriend_head_view)
    HeadView addfriendHeadView;
    @BindView(R.id.activity_addfrient_search_iv)
    ImageView activityAddfrientSearchIv;
    @BindView(R.id.activity_addfrient_search_ed)
    EditText activityAddfrientSearchEd;
    @BindView(R.id.activity_addfrient_delete_iv)
    ImageView activityAddfrientDeleteIv;
    @BindView(R.id.activity_addfrient_img)
    ImageView activityAddfrientImg;
    @BindView(R.id.activity_addfrient_name)
    TextView activityAddfrientName;
    @BindView(R.id.addfrent_remark)
    EditText addfrentRemark;
    @BindView(R.id.user_data)
    LinearLayout userData;
    private ImageView head_back;
    private TextView head_name;
    private String s;
    private IPresentermpl iPresentermpl;
    private int a = 1;
    private FindUserBeans findUserBeans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        ButterKnife.bind(this);
        serchutils.clearButtonListener(activityAddfrientSearchEd, activityAddfrientDeleteIv, activityAddfrientSearchIv);
        head_back = addfriendHeadView.findViewById(R.id.head_back);
        head_name = addfriendHeadView.findViewById(R.id.head_name);
        head_name.setText("添加");
        iPresentermpl = new IPresentermpl(this);

    }

    @OnClick({R.id.head_back, R.id.addfriend_head_view, R.id.activity_addfrient_search_iv, R.id.activity_addfrient_search_ed, R.id.activity_addfrient_img, R.id.activity_addfrient_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_back:
                this.finish();
                break;
            case R.id.activity_addfrient_search_iv:
                s = activityAddfrientSearchEd.getText().toString();
                if (s.isEmpty()) {
                    Toast.makeText(AddFriendActivity.this, "请输入手机号或群id", Toast.LENGTH_LONG).show();
                }

                iPresentermpl.getRequest(Apis.FINDUSERBYPHONE + s, FindUserBeans.class);

                break;
            case R.id.activity_addfrient_img:

                break;
            case R.id.activity_addfrient_name:
                Intent intent = new Intent(AddFriendActivity.this, UserInfoActivity.class);
                //添加传送数据
                intent.putExtra("findUserBeans", findUserBeans);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void success(Object data) {
        findUserBeans = (FindUserBeans) data;
        if (findUserBeans.getStatus().equals("0000")) {
            Toast.makeText(this, findUserBeans.getMessage() + "", Toast.LENGTH_LONG).show();
            //展示用户信息
            FindUserBeans.ResultBean result = findUserBeans.getResult();
            userShow(result);
        } else {
            Toast.makeText(this, findUserBeans.getMessage() + "", Toast.LENGTH_LONG).show();
        }

    }

    private void userShow(FindUserBeans.ResultBean result) {
        activityAddfrientName.setText(result.getNickName());
        String headPic = result.getHeadPic();
        Glide.with(this).load(headPic).into(activityAddfrientImg);
    }

    @Override
    public void failure(String error) {

    }
}
