package com.wd.tech.message.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.app.Apis;
import com.wd.tech.message.bean.CreateGroupBeans;
import com.wd.tech.message.view.HeadView;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddGroupActivity extends AppCompatActivity implements IView {

    @BindView(R.id.activity_addgroup_head)
    HeadView activityAddgroupHead;
    @BindView(R.id.activity_greatgroup_name)
    EditText activityGreatgroupName;
    @BindView(R.id.activity_greatgroup_jieshao)
    EditText activityGreatgroupJieshao;
    @BindView(R.id.activity_greatgroup_great)
    Button activityGreatgroupGreat;
    private ImageView head_back;
    private TextView head_name;
    private IPresentermpl iPresentermpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        ButterKnife.bind(this);
        head_back = activityAddgroupHead.findViewById(R.id.head_back);
        head_name = activityAddgroupHead.findViewById(R.id.head_name);
        head_name.setText("创建群聊");
        iPresentermpl = new IPresentermpl(this);

    }

    @OnClick(R.id.activity_greatgroup_great)
    public void onViewClicked() {
        String name = activityGreatgroupName.getText().toString();
        String jianjie = activityGreatgroupJieshao.getText().toString();
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("name",name);
        hashMap.put("description",jianjie);
        iPresentermpl.postRequest(Apis.CREATEGROUP,hashMap,CreateGroupBeans.class);

    }

    @Override
    public void success(Object data) {
        CreateGroupBeans createGroupBeans= (CreateGroupBeans) data;
        if (createGroupBeans.getStatus().equals("0000")){
            Toast.makeText(AddGroupActivity.this,createGroupBeans.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void failure(String error) {

    }
}
