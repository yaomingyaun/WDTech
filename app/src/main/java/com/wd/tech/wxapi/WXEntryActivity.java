package com.wd.tech.wxapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.R;
import com.wd.tech.ShowActivity;
import com.wd.tech.bean.WXLoginBean;
import com.wd.tech.home.activity.PayActivity;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.Map;

;

public class WXEntryActivity  extends AppCompatActivity implements IWXAPIEventHandler,IView {
private static String APP_ID = "wx4c96b6b8da494224";
   private IPresentermpl iPresentermpl;

    private static PayReq req;
    private static IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        iPresentermpl=new IPresentermpl(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        api=WXAPIFactory.createWXAPI(this,APP_ID,false);
        api.handleIntent(getIntent(),this);
        WXUtils.reg(this).handleIntent(getIntent(),this);
        Intent intent = getIntent();
        req.appId = intent.getStringExtra("appId");
        req.nonceStr = intent.getStringExtra("nonceStr");
        req.partnerId = intent.getStringExtra("partnerId");
        req.prepayId = intent.getStringExtra("prepayId");
        req.sign = intent.getStringExtra("sign");
        req.timeStamp = intent.getStringExtra("timeStamp");
        req.packageValue = intent.getStringExtra("packageValue");
        api.sendReq(req);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
    @Override
    public void onReq(BaseReq baseReq) {
        Log.e("onReq", "onError");
    }


@Override
public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
        case BaseResp.ErrCode.ERR_OK:
         String code = ((SendAuth.Resp) baseResp).code;
            Map<String, String> map = new HashMap<>();
              map.put("code",code);
              Log.i("adasdasdasdasdsa",code);
            Toast.makeText(this, code+"", Toast.LENGTH_SHORT).show();
              //微信登录接口
            iPresentermpl.postRequest(Api.WECHATLOGIN,map,WXLoginBean.class);
           // finish();
        break;
        case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
        finish();
        break;
        case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
        finish();
        break;
        default:
        finish();
        break;
        }
    if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
        if (baseResp.errCode == 0) {
            Toast.makeText(this, "付款成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PayActivity.class);
            startActivity(intent);
        } else if (baseResp.errCode == -2) {
            Toast.makeText(this, "您已取消付款", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PayActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "参数错误", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PayActivity.class);
            startActivity(intent);
        }
    }
}
    @Override
    public void success(Object data) {
        if(data instanceof WXLoginBean)
        {

            WXLoginBean wxBean= (WXLoginBean) data;
            if(wxBean.getStatus().equals("0000"))
            {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
                sharedPreferences.edit().putString("userId",wxBean.getResult().getUserId()).putString("sessionId",wxBean.getResult().getSessionId()).commit();
                startActivity(new Intent(WXEntryActivity.this,ShowActivity.class));
                finish();
            }else
            {
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failure(String error) {

    }
}