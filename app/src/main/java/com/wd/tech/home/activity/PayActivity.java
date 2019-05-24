package com.wd.tech.home.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.R;
import com.wd.tech.home.bean.OrderBean;
import com.wd.tech.home.bean.VIPBean;
import com.wd.tech.home.bean.WXBean;
import com.wd.tech.home.bean.ZhifuBaoBean;
import com.wd.tech.utils.base.RsaCoder;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayActivity extends AppCompatActivity implements IView,View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.zi)
    RelativeLayout zi;
    @BindView(R.id.nianzuan)
    RadioButton nianzuan;
    @BindView(R.id.jizuan)
    RadioButton jizuan;
    @BindView(R.id.yuezuan)
    RadioButton yuezuan;
    @BindView(R.id.zhouzuan)
    RadioButton zhouzuan;
    @BindView(R.id.xian)
    View xian;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.chose)
    RelativeLayout chose;
    @BindView(R.id.asd)
    TextView asd;
    @BindView(R.id.wx)
    ImageView wx;
    @BindView(R.id.zfb)
    ImageView zfb;
    @BindView(R.id.wxzfbtn)
    RadioButton wxzfbtn;
    @BindView(R.id.zfbbtn)
    RadioButton zfbbtn;
    @BindView(R.id.xiadan)
    Button xiadan;
    private int PAYINFOR=1;
    private SharedPreferences sharedPreferences;
    private String userId,sessionId;
    IPresentermpl iPresentermpl;
    int commodityId;
    private List<VIPBean.ResultBean> result;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        //获取userid，sessionid
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", "");
        sessionId = sharedPreferences.getString("sessionId", "");
        iPresentermpl=new IPresentermpl(this);
        nianzuan.setOnClickListener(this);
        jizuan.setOnClickListener(this);
        yuezuan.setOnClickListener(this);
        zhouzuan.setOnClickListener(this);
        wxzfbtn.setOnClickListener(this);
        zfbbtn.setOnClickListener(this);
        xiadan.setOnClickListener(this);
        iPresentermpl.getRequest(Api.FINDVIPCOMMODITYLIST,VIPBean.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nianzuan:
                money.setText(result.get(3).getPrice()+"");
                commodityId=result.get(3).getCommodityId();
                nianzuan.setChecked(true);
                jizuan.setChecked(false);
                yuezuan.setChecked(false);
                zhouzuan.setChecked(false);

                break;
            case R.id.jizuan:
                money.setText(result.get(2).getPrice()+"");
                commodityId=result.get(2).getCommodityId();
                nianzuan.setChecked(false);
                jizuan.setChecked(true);
                yuezuan.setChecked(false);
                zhouzuan.setChecked(false);

                break;
            case R.id.yuezuan:
                money.setText(result.get(1).getPrice()+"");
                commodityId=result.get(1).getCommodityId();
                nianzuan.setChecked(false);
                jizuan.setChecked(false);
                yuezuan.setChecked(true);
                zhouzuan.setChecked(false);
                break;
            case R.id.zhouzuan:
                money.setText(result.get(0).getPrice()+"");
                commodityId=result.get(0).getCommodityId();
                nianzuan.setChecked(false);
                jizuan.setChecked(false);
                yuezuan.setChecked(false);
                zhouzuan.setChecked(true);
                break;
            case R.id.wxzfbtn:
                PAYINFOR=1;
                break;
            case R.id.zfbbtn:
                PAYINFOR=2;
                break;
            case R.id.xiadan:
                String encypt = RsaCoder.MD5(userId + commodityId + "tech");
                HashMap<String, String> map2 = new HashMap<>();
                map2.put("commodityId",commodityId+"");
                map2.put("sign",encypt);
                iPresentermpl.postRequest(Api.BUYVIP,map2,OrderBean.class);
                break;
                default:break;
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof VIPBean){
            VIPBean bean= (VIPBean) data;
            result = bean.getResult();
        }
        if(data instanceof OrderBean){
            OrderBean bean= (OrderBean) data;
            orderId = bean.getOrderId();
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this,bean.getMessage(),Toast.LENGTH_SHORT).show();
                if(PAYINFOR==1){
                    HashMap<String, String> map = new HashMap<>();
                    map.put("orderId",orderId);
                    map.put("payType",PAYINFOR+"");
                    iPresentermpl.postRequest(Api.PAY,map,WXBean.class);
                }else {
                    HashMap<String, String> map1 = new HashMap<>();
                    map1.put("orderId",orderId);
                    map1.put("payType",PAYINFOR+"");
                    iPresentermpl.postRequest(Api.PAY,map1,ZhifuBaoBean.class);
                }
            }else {
                Toast.makeText(this,bean.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        if (data instanceof WXBean){
            WXBean bean= (WXBean) data;
            IWXAPI api = WXAPIFactory.createWXAPI(this, null);
            api.registerApp(bean.getAppId());
            PayReq request = new PayReq();
            request.appId = bean.getAppId();
            request.partnerId = bean.getPartnerId();
            request.prepayId= bean.getPrepayId();
            request.packageValue = bean.getPackageValue();
            request.nonceStr= bean.getNonceStr();
            request.timeStamp= bean.getTimeStamp();
            request.sign= bean.getSign();
            api.sendReq(request);
        }
        if (data instanceof ZhifuBaoBean){
            final ZhifuBaoBean bean= (ZhifuBaoBean) data;
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    final PayTask payTask = new PayTask(PayActivity.this);
                    payTask.payV2(bean.getResult(), true);
                }
            }.start();
        }

    }

    @Override
    public void failure(String error) {

    }
}
