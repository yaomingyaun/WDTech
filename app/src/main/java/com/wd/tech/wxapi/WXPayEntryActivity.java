package com.wd.tech.wxapi;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if(baseResp.getType()==ConstantsAPI.COMMAND_PAY_BY_WX){
            if (baseResp.errCode==0){
                Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
