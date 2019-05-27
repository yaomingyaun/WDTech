package com.wd.tech.utils.netWork;

public class Api {
    //用户相关
    //注册
    public static final String REGISTER = "user/v1/register";
    //登录
    public static final String LOGIN = "user/v1/login";
    //完善用户信息
    public static final String PERFECTUSERINFO = "verify/v1/perfectUserInfo";
    //根据用户ID查询用户信息
    public static final String INFOBYUSERID = "user/verify/v1/getUserInfoByUserId";
    //修改用户昵称
    public static final String NICKNAME = "user/verify/v1/modifyNickName";
    //修改用户昵称
    public static final String SIGNATURE = "user/verify/v1/modifySignature";
    //用户上传头像
    public static final String HEADPIC = "user/verify/v1/modifyHeadPic";
    //修改邮箱
    public static final String EMAIL = "user/verify/v1/modifyEmail";
    //修改用户密码
    public static final String PWD = "user/verify/v1/modifyUserPwd";
    //根据极光userNames批量查询会话列表需要的用户信息
    public static final String FINDCONVERSATIONLIST = "user/verify/v1/findConversationList";
    //查询用户积分
    public static final String FINDUSERINTEGRAL = "user/verify/v1/findUserIntegral";
    //查询用户积分明细
    public static final String FINDUSERINTEGRALRCORD = "user/verify/v1/findUserIntegralRecord";
    //用户收藏列表
    public static final String FINDALLINFOCOLLECTION = "user/verify/v1/findAllInfoCollection?page=1&count=5";
    //添加收藏
    public static final String ADDCOLLECTION = "user/verify/v1/addCollection?";
    //取消收藏（支持批量操作）
    public static final String CANCELCOLLECTION = "user/verify/v1/cancelCollection?infoId=%d";
    //用户关注列表
    public static final String FINDFOLLOWUSERLIST = "user/verify/v1/findFollowUserList";
    //关注用户
    public static final String ADDFOLLOW = "user/verify/v1/addFollow";
    //取消关注
    public static final String CANCELFOLLOW = "user/verify/v1/cancelFollow?focusId=%s";
    //校验手机号是否可用
    public static final String CHECKPHONE = "user/v1/checkPhone";
    //微信登录
    public static final String WECHATLOGIN = "user/v1/weChatLogin";
    //绑定微信帐号
    public static final String BINDWECHAT = "user/verify/v1/bindWeChat";
    //判断是否绑定微信
    public static final String WHETHERTOBINDWECHAT = "user/verify/v1/whetherToBindWeChat";
    //.查询用户任务列表
    public static final String FINDUSERTASKLIST = "user/verify/v1/findUserTaskList";
    //做任务
    public static final String DOTHETASK = "user/verify/v1/doTheTask";
    //签到
    public static final String USERSIGN = "user/verify/v1/userSign";
    //查询当天签到状态
    public static final String FINDUSERSIGNSTATUS = "user/verify/v1/findUserSignStatus";
    //查询用户连续签到天数
    public static final String FINDCONTINUOUSSIGNDAYS = "user/verify/v1/findContinuousSignDays";
    //查询用户当月所有签到的日期
    public static final String FINDUSERSIGNRECORDING = "user/verify/v1/findUserSignRecording";
    //查询好友信息
    public static final String QUERUFRIENDINFORMATION = "user/verify/v1/queryFriendInformation";
    //增量查询脸部特征库
    public static final String INCREMENTFINDFACEFEATURE = "user/v1/incrementFindFaceFeature";
    //绑定faceId
    public static final String BINDINGFACEID = "user/verify/v1/bindingFaceId";
    //刷脸登陆
    public static final String FACEOGIN = "user/v1/faceLogin";
    //根据手机号查询用户信息
    public static final String FINDUSERBYPHONE = "user/verify/v1/findUserByPhone";
    //解绑faceId
    public static final String UNTIEDFACEID = "user/verify/v1/untiedFaceId";

    //好友聊天相关
    //添加好友
    public static final String ADDFRIEND = "chat/verify/v1/addFriend";
    //删除好友
    public static final String DELETEFRIENDRELATION = "chat/verify/v1/deleteFriendRelation";
    //修改好友备注
    public static final String MODIFYFRIENDREMARK = "chat/verify/v1/modifyFriendRemark";
    //检测是否为我的好友
    public static final String CHECKMYFRIEND = "chat/verify/v1/checkMyFriend";
    //创建自定义好友分组
    public static final String ADDFRIENDGROUP = "chat/verify/v1/addFriendGroup";
    //查询用户所有分组
    public static final String FINDFRIENDGOURPLIST = "chat/verify/v1/findFriendGroupList";
    //修改好友分组名称
    public static final String MODIFYGROUPNAME = "chat/verify/v1/modifyGroupName";
    //转移好友到其他分组
    public static final String TRANSFERFRIENDGROUP = "chat/verify/v1/transferFriendGroup";
    //删除用户好友分组
    public static final String DELETEFRIENDGROUP = "chat/verify/v1/deleteFriendGroup";
    //查询分组下的好友列表信息
    public static final String FINDFRIENDLISTBYGROUPID = "chat/verify/v1/findFriendListByGroupId";
    //查询用户的好友通知记录
    public static final String FINDFRIENDNOTICEPAGELIST = "chat/verify/v1/findFriendNoticePageList";
    //审核好友申请
    public static final String REVIEWFRIENDAPPLY = "chat/verify/v1/reviewFriendApply";
    //发送消息
    public static final String SENDMESSAGE = "chat/verify/v1/sendMessage";
    //查询好友聊天记录
    public static final String FINDCHATRECORDPAGELIST = "chat/verify/v1/findChatRecordPageList";
    //查询好友对话记录
    public static final String FINDDIALOGUERECORDPAGELIST = "chat/verify/v1/findDialogueRecordPageList";
    //删除好友聊天记录
    public static final String DELETECHATRECORD = "chat/verify/v1/deleteChatRecord";
    //查询我的好友列表
    public static final String SEARCHFRIEND = "chat/verify/v1/searchFriend";
    //初始化我的好友列表全量信息
    public static final String INIEFRIENDLIST = "chat/verify/v1/initFriendList";

    //群组相关
    //创建群
    public static final String CREATEGROUP = "group/verify/v1/createGroup";
    //修改群组名
    public static final String GROUPMODIFYGROUPNAME = "group/verify/v1/modifyGroupName";
    //修改群简介
    public static final String MODIFYGROUPDESCRIPTION = "group/verify/v1/modifyGroupDescription";
    //解散群组
    public static final String SISBANDGROUP = "group/verify/v1/disbandGroup";
    //查询我创建的群组
    public static final String FINDGROUPSBYUSERID = "group/verify/v1/findGroupsByUserId";
    //查询我所有加入的群组
    public static final String FINDUSERHOINEDGROUP = "group/verify/v1/findUserJoinedGroup";
    //查询群组内所有用户信息
    public static final String FINDGROUPMEMBERLIST = "group/verify/v1/findGroupMemberList";
    // 查询群组详细信息
    public static final String FINDGROUPINFO = "group/verify/v1/findGroupInfo";
    //发送群信息
    public static final String SENDGROUPMESSAGE = "group/verify/v1/sendGroupMessage";
    //查询群聊天内容
    public static final String FINDGROUPCHATRECORDPAGE = "group/verify/v1/findGroupChatRecordPage";
    //移出群成员(管理员与群主才有的权限)
    public static final String REMOVEGROUPMEMBER = "group/verify/v1/removeGroupMember";
    //调整群成员角色(群主才有的权限)
    public static final String MODIFYPERMISSION = "group/verify/v1/modifyPermission";
    //判断用户是否已在群内
    public static final String WHETHERINGROUP = "group/verify/v1/whetherInGroup";
    //申请进群
    public static final String APPLYADDGROUP = "group/verify/v1/applyAddGroup";
    //邀请加群
    public static final String INVITEADDGROUP = "group/verify/v1/inviteAddGroup";
    //批量邀请加群
    public static final String BATCHINVITEADDGROUP = "group/verify/v1/batchInviteAddGroup";
    //查询群通知记录
    public static final String FINDGROUPNOTICEPAGELIST = "group/verify/v1/findGroupNoticePageList";
    //审核群申请
    public static final String REVIEWGROUPAPPLY = "group/verify/v1/reviewGroupApply";
    //上传群头像
    public static final String UPLOADGROUPHEADPIC = "group/verify/v1/uploadGroupHeadPic";
    //退群
    public static final String RETREAT = "group/verify/v1/retreat";

    //咨询相关
    //banner展示列表
    public static final String BANNERSHOW = "information/v1/bannerShow";
    //资讯推荐展示列表(包含单独板块列表展示)
    public static final String INFORECOMMENDLIST = "information/v1/infoRecommendList?page=%d&count=%d";
    public static final String MINFORECOMMENDLIST = "information/v1/infoRecommendList?plateId=%d&page=%d&count=%d";
    //资讯详情展示
    public static final String FINDINFORMATIONDETAILS = "information/v1/findInformationDetails?id=%d";
    //所有板块查询
    public static final String FINDALLINFOPLATE = "information/v1/findAllInfoPlate?page=%d&count=%d";
    //修改资讯分享数
    public static final String UPDATEINFOSHARENUM = "information/v1/updateInfoShareNum";
    //资讯点赞
    public static final String ADDFREATRECORD = "information/verify/v1/addGreatRecord";
    //取消点赞
    public static final String CANCELGREAT = "information/verify/v1/cancelGreat?infoId=%d";
    //资讯用户评论
    public static final String ADDINFOCOMMENT = "information/verify/v1/addInfoComment?infoId=%d&content=%s";
    //查询资讯评论列表
    public static final String FINDALLINFOCOMMENTLIST = "information/v1/findAllInfoCommentList?page=%d&count=%d&infoId=%d";
    //根据标题模糊查询
    public static final String FINDINFORMATIONBYTIRLE = "information/v1/findInformationByTitle?title=%s&page=%d&count=%d";
    //根据作者名模糊查询
    public static final String FINDINFORMATIONBYSOURCE = "information/v1/findInformationBySource";
    //资讯广告
    public static final String FINDINFOADVERTISING = "information/v1/findInfoAdvertising";
    //资讯积分兑换
    public static final String INFOPAYBYINTEGRAL = "information/verify/v1/infoPayByIntegral";

    //社区相关
    //社区列表
    public static final String FINDCOUMMUNITYLIST = "community/v1/findCommunityList?page=%d&count=5";
    //发布帖子
    public static final String RELEASEPOST = "community/verify/v1/releasePost";
    //删除帖子(支持批量删除)
    public static final String DELETEPOST = "community/verify/v1/deletePost";
    //点赞
    public static final String ADDCOMMUNITYGREAT = "community/verify/v1/addCommunityGreat";
    //取消点赞
    public static final String CANCELCOMMUNTYGREAT = "community/verify/v1/cancelCommunityGreat?communityId=%d";
    //社区评论列表（标签方式返参）
    public static final String FINDCOMMUNITYCOMMENTLIST = "community/v1/findCommunityCommentList";
    //社区用户评论列表（bean方式返参）
    public static final String FINDCOMMUNITYUSERCOMMENTLIST ="community/v1/findCommunityUserCommentList?communityId=%s&page=1&count=50";
    //社区评论
    public static final String ADDCOMMUNITYCOMMENT = "community/verify/v1/addCommunityComment";
    //我的帖子
    public static final String FINDMYPOSTBYID = "community/verify/v1/findMyPostById";
    // 查询用户发布的帖子
    public static final String FINDUSERPOSTBYID = "community/verify/v1/findUserPostById?fromUid=%s&page=%d&count=5";

    //综合业务相关
    //查询所有会员商品
    public static final String FINDVIPCOMMODITYLIST = "tool/v1/findVipCommodityList";
    //用户购买VIP
    public static final String BUYVIP = "tool/verify/v1/buyVip";
    //支付
    public static final String PAY = "tool/verify/v1/pay";
    //微信分享前置接口，获取分享所需参数
    public static final String WXSHARE = "tool/v1/wxShare";
    //意见反馈
    public static final String RECORDFEEDBACK = "tool/verify/v1/recordFeedBack";
    //查询新版本
    public static final String FINDNEWVERSION = "tool/v1/findNewVersion";
    //查询所有奖品
    public static final String FINDALLPRIZE = "tool/verify/v1/findAllPrize";
    //抽奖
    public static final String LOTTERY = "tool/verify/v1/lottery";
    //查询用户抽奖记录
    public static final String FINDLOTTERYRECORDLIST = "tool/verify/v1/findLotteryRecordList";
    //领取奖品
    public static final String RECELVEPRIZE = "tool/verify/v1/receivePrize";
    //.查询用户系统通知
    public static final String FINDSYSNOTICELIST = "tool/verify/v1/findSysNoticeList";
}
