package com.wd.tech.home.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.R;
import com.wd.tech.home.adapter.CommentAdapter;
import com.wd.tech.home.adapter.RecommendAdapter;
import com.wd.tech.home.bean.CommentBean;
import com.wd.tech.home.bean.GreatBean;
import com.wd.tech.home.bean.InforDetailsBean;
import com.wd.tech.home.bean.ShareBean;
import com.wd.tech.home.bean.TalkBean;
import com.wd.tech.utils.AlertDialogUtils;
import com.wd.tech.utils.base.RsaCoder;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationDetailsActivity extends AppCompatActivity implements IView, View.OnClickListener {

    @BindView(R.id.title_information)
    TextView titleInformation;
    @BindView(R.id.time_information)
    TextView timeInformation;
    @BindView(R.id.introduce_information)
    TextView introduceInformation;
    @BindView(R.id.img_information)
    SimpleDraweeView imgInformation;
    @BindView(R.id.introduce_summary)
    TextView introducesummary;
    @BindView(R.id.show_web)
    WebView show_web;
    IPresentermpl iPresentermpl;
    @BindView(R.id.img_nobuy)
    ImageView imgNobuy;
    @BindView(R.id.comebuy)
    Button comebuy;
    @BindView(R.id.re_buy)
    RelativeLayout reBuy;
    @BindView(R.id.smwy)
    RelativeLayout smwy;
    @BindView(R.id.tuijian)
    TextView tuijian;
    @BindView(R.id.recommendedlist)
    RecyclerView recommendedlist;
    @BindView(R.id.ping)
    TextView ping;
    @BindView(R.id.commentlist)
    XRecyclerView commentlist;
    @BindView(R.id.wd)
    RelativeLayout wd;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.editcomment)
    TextView editcomment;
    @BindView(R.id.pinglun)
    ImageView pinglun;
    @BindView(R.id.pinglunshu)
    TextView pinglunshu;
    @BindView(R.id.great)
    ImageView great;
    @BindView(R.id.greatshu)
    TextView greatshu;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.shareshu)
    TextView shareshu;
    @BindView(R.id.recyc)
    RelativeLayout recyc;
    @BindView(R.id.share_friend)
    ImageView shareFriend;
    @BindView(R.id.share_cirle)
    ImageView shareCirle;
    @BindView(R.id.shared_view)
    View sharedView;
    @BindView(R.id.diss_share)
    TextView dissShare;
    @BindView(R.id.wx_relate)
    RelativeLayout wxRelate;
    private int mid;
    @BindView(R.id.pinglun_edit)
    EditText pinglun_edit;
    @BindView(R.id.submit_text)
    TextView submit_text;
    @BindView(R.id.pinlun_re)
    RelativeLayout pinlun_re;
    int PAGE = 1;
    RecommendAdapter recommendAdapter;
    CommentAdapter commentAdapter;
    private InforDetailsBean.ResultBean result;
    private PopupWindow popupWindow;
    private IWXAPI api;
    int FLAG=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_details);
        ButterKnife.bind(this);
        iPresentermpl = new IPresentermpl(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        mid = Integer.valueOf(id);
        iPresentermpl.getRequest(String.format(Api.FINDINFORMATIONDETAILS, mid), InforDetailsBean.class);
        pinglun.setOnClickListener(this);
        great.setOnClickListener(this);
        collect.setOnClickListener(this);
        share.setOnClickListener(this);
        editcomment.setOnClickListener(this);
        comebuy.setOnClickListener(this);
        shareFriend.setOnClickListener(this);
        shareCirle.setOnClickListener(this);
        wxRelate.setVisibility(View.INVISIBLE);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iPresentermpl.getRequest(String.format(Api.FINDINFORMATIONDETAILS, mid), InforDetailsBean.class);
    }

    public void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        commentlist.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                iPresentermpl.getRequest(String.format(Api.FINDALLINFOCOMMENTLIST, PAGE, 8, mid), CommentBean.class);
            }

            @Override
            public void onLoadMore() {
                iPresentermpl.getRequest(String.format(Api.FINDALLINFOCOMMENTLIST, PAGE, 8, mid), CommentBean.class);
            }
        });
        iPresentermpl.getRequest(String.format(Api.FINDALLINFOCOMMENTLIST, PAGE, 8, mid), CommentBean.class);
        commentlist.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(this);
        commentlist.setAdapter(commentAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pinglun:
                pinlun_re.setVisibility(View.VISIBLE);
                recyc.setVisibility(View.INVISIBLE);
                wxRelate.setVisibility(View.INVISIBLE);
                submit_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String string = pinglun_edit.getText().toString();
                        HashMap<String, String> map1 = new HashMap<>();
                        map1.put("content", string);
                        map1.put("infoId", mid + "");
                        iPresentermpl.postRequest(Api.ADDINFOCOMMENT, map1, GreatBean.class);
                        pinlun_re.setVisibility(View.INVISIBLE);
                        recyc.setVisibility(View.VISIBLE);
                        wxRelate.setVisibility(View.INVISIBLE);
                    }
                });
                //评论
                break;
            case R.id.editcomment:
                pinlun_re.setVisibility(View.VISIBLE);
                recyc.setVisibility(View.INVISIBLE);
                wxRelate.setVisibility(View.INVISIBLE);
                submit_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String string = pinglun_edit.getText().toString();
                        HashMap<String, String> map1 = new HashMap<>();
                        map1.put("content", string);
                        map1.put("infoId", mid + "");
                        iPresentermpl.postRequest(Api.ADDINFOCOMMENT, map1, GreatBean.class);
                        pinlun_re.setVisibility(View.INVISIBLE);
                        recyc.setVisibility(View.VISIBLE);
                        wxRelate.setVisibility(View.INVISIBLE);
                    }
                });
                //评论
                break;
            case R.id.great:
                if (result.getWhetherGreat() == 1) {
                    Glide.with(this).load(R.mipmap.common_icon_prise_n).into(great);
                    iPresentermpl.deleteRequest(String.format(Api.CANCELGREAT, mid), GreatBean.class);
                    result.setPraise(result.getPraise() - 1);
                    greatshu.setText(result.getPraise() + "");
                    result.setWhetherGreat(2);
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("infoId", mid + "");
                    iPresentermpl.postRequest(Api.ADDFREATRECORD, map, GreatBean.class);
                    Glide.with(this).load(R.mipmap.common_icon_praise_s).into(great);
                    result.setPraise(result.getPraise() + 1);
                    greatshu.setText(result.getPraise() + "");
                    result.setWhetherGreat(1);

                }
                //点赞
                break;
            case R.id.collect:
                if (result.getWhetherCollection() == 1) {
                    Glide.with(this).load(R.mipmap.common_icon_collect_n).into(collect);
                    iPresentermpl.deleteRequest(String.format(Api.CANCELCOLLECTION, mid), GreatBean.class);
                    result.setWhetherCollection(result.getWhetherCollection() - 1);
                    result.setWhetherGreat(2);
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("infoId", mid + "");
                    iPresentermpl.postRequest(Api.ADDCOLLECTION, map, GreatBean.class);
                    Glide.with(this).load(R.mipmap.common_icon_collect_s).into(collect);
                    result.setWhetherCollection(result.getWhetherCollection() + 1);
                    result.setWhetherGreat(1);
                }
                //收藏
                break;
            case R.id.share:
                pinlun_re.setVisibility(View.INVISIBLE);
                recyc.setVisibility(View.INVISIBLE);
                wxRelate.setVisibility(View.VISIBLE);
                dissShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pinlun_re.setVisibility(View.INVISIBLE);
                        recyc.setVisibility(View.VISIBLE);
                        wxRelate.setVisibility(View.INVISIBLE);
                    }
                });
                //分享
                break;
            case R.id.comebuy:
                setpay();
                popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.share_friend:
                long millis = System.currentTimeMillis();
                Log.i("aaaaa",millis+"");
                String encypt = RsaCoder.MD5(millis + "wxShare" + "tech");
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("sign",encypt);
                hashMap.put("time",millis+"");
                iPresentermpl.postData(Api.WXSHARE,hashMap,ShareBean.class);
                break;
            case R.id.share_cirle:
                Toast.makeText(this,"暂不支持发送到朋友圈",Toast.LENGTH_SHORT).show();
        }
    }
    private String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    //设置支付弹框
    public void setpay() {
        final View view = getLayoutInflater().inflate(R.layout.pop_pay, null, false);
        ImageView downpop_image = view.findViewById(R.id.downpop_image);
        TextView vipopen = view.findViewById(R.id.vipopen);
        TextView jifenopen = view.findViewById(R.id.jifenopen);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
        popupWindow.setFocusable(true);
        // 实例化一个ColorDrawable颜色
        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.coloroo));
        popupWindow.setBackgroundDrawable(dw);
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        //点击兑换
        jifenopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //点击开启vip
        vipopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationDetailsActivity.this, PayActivity.class);
                intent.putExtra("mid", mid + "");
                startActivity(intent);
            }
        });
        downpop_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void success(Object data) {
        if (data instanceof InforDetailsBean) {
            InforDetailsBean bean = (InforDetailsBean) data;
            result = bean.getResult();
            titleInformation.setText(result.getTitle());
            introduceInformation.setText(result.getSource());
            Uri parse = Uri.parse(result.getThumbnail());
            introducesummary.setText(result.getSummary());
            imgInformation.setImageURI(parse);
            pinglunshu.setText(result.getComment() + "");
            shareshu.setText(result.getShare() + "");
            greatshu.setText(result.getPraise() + "");
            if (result.getReadPower() == 1) {
                reBuy.setVisibility(View.INVISIBLE);
                show_web.setVisibility(View.VISIBLE);
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(result.getReleaseTime()));
                timeInformation.setText(date);
                WebSettings settings = show_web.getSettings();
                settings.setJavaScriptEnabled(true);//支持JS
                String js = "<script type=\"text/javascript\">" +
                        "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                        "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                        "imgs[i].style.width = '100%';" +  // 宽度改为100%
                        "imgs[i].style.height = 'auto';" +
                        "}" +
                        "</script>";
                show_web.loadDataWithBaseURL(null, result.getContent() + js, "text/html", "utf-8", null);
                //推荐展示
                recommendAdapter = new RecommendAdapter(this);
                recommendAdapter.setList(result.getInformationList());
                recommendedlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                recommendedlist.setAdapter(recommendAdapter);
            } else {
                reBuy.setVisibility(View.VISIBLE);
                show_web.setVisibility(View.INVISIBLE);
            }
            if (result.getWhetherGreat() == 1) {
                Glide.with(this).load(R.mipmap.common_icon_praise_s).into(great);
            } else if (result.getWhetherGreat() == 2) {
                Glide.with(this).load(R.mipmap.common_icon_prise_n).into(great);
            }
            if (result.getWhetherCollection() == 1) {
                Glide.with(this).load(R.mipmap.common_icon_collect_s).into(collect);
            } else if (result.getWhetherCollection() == 2) {
                Glide.with(this).load(R.mipmap.common_icon_collect_n).into(collect);
            }
            if(bean.getStatus().equals("9999")) {
                AlertDialogUtils alertDialogUtils = new AlertDialogUtils(this);
                alertDialogUtils.show();
            }

        }
        if (data instanceof CommentBean) {
            CommentBean bean = (CommentBean) data;
            List<CommentBean.ResultBean> result = bean.getResult();
            if (PAGE == 1) {
                commentAdapter.setList(result);
            } else {
                commentAdapter.addList(result);
            }
            PAGE++;
            commentlist.loadMoreComplete();
            commentlist.refreshComplete();
        }
        if (data instanceof GreatBean) {
            if (data instanceof GreatBean) {
                GreatBean bean = (GreatBean) data;
                if (bean.getStatus().equals("0000")) {
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    pinglun_edit.setText("");
                } else {
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
                if(bean.getStatus().equals("9999")) {
                    AlertDialogUtils alertDialogUtils = new AlertDialogUtils(this);
                    alertDialogUtils.show();
                }
            }
        }
        if (data instanceof ShareBean){
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl ="http://www.hooxiao.com";
            //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
            WXMediaMessage msg = new WXMediaMessage(webpage);
            api = WXAPIFactory.createWXAPI(this, "wx4c96b6b8da494224", true);
            // 将应用的appId注册到微信
            api.registerApp("wx4c96b6b8da494224");
            msg.title =result.getTitle();
            msg.description =result.getSummary();
            msg.mediaObject = webpage;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            //transaction用于唯一标识一个请求
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            req.scene = SendMessageToWX.Req.WXSceneSession;    //设置发送到朋友
            api.sendReq(req);
            finish();
        }
        if(data instanceof TalkBean){
            TalkBean bean = (TalkBean) data;
            if (bean.getStatus().equals("0000")) {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                result.setComment(result.getComment()+1);
                pinglunshu.setText(result.getComment() + "");
                pinglun_edit.setText("");
            } else {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void failure(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresentermpl.deatch();
    }
}
