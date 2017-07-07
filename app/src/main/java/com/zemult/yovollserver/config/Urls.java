package com.zemult.yovollserver.config;

/**
 * Created by Wikison on 2016/3/31.
 */
public class Urls {
    //    public static String BASIC_URL = "http://server.54xiegang.com/dzyx/inter_json/";

    public static String MAIN_URL = "http://www.yovoll.com/";
    //正式
//    public static String URL = "http://www.yovoll.com/dzyx/";
//    public static String APP_KEY = "23630707";

    //测试
    public static String URL = "http://test1.54xiegang.com/dzyx_test/";
    public static String APP_KEY = "23521799";


    public static String BASIC_URL = URL + "inter_json/";

    //验证码
    public static String COMMON_GETCODE = "common_getcode.do";
    public static String COMMON_GETCODE_BANK = "common_getcode_bank";
    public static String COMMON_CHECKCODE = "common_checkcode.do";

    //是否实名认证
    public static String COMMON_ISREALNAME = "user_realname_show.do";
    //搜索热词
    public static String COMMON_HOT_SEARCHLIST = "common_hot_searchList.do";

    //OSS移动应用直传服务
    public static String OSS_OSSTOKEN = "oss_ossToken.do";


    //注册登录登出
    public static String USER_ISREGISTER = "user_isRegister.do";
    public static String USER_REGISTER = "user_register_1_1.do";
    public static String USER_LOGOUT = "user_logout.do";
    public static String USER_FINDPWD = "user_findpwd.do";

    //获取所有行业角色
    public static String COMMON_GETINDUSTRYROLES = "common_getallindustry.do";
    //获取所有行业
    public static String COMMON_GETINDUSTRYS = "common_getindustryfathers.do";
    //获取行业的所有角色
    public static String COMMON_GETROLESBYINDUSTRY = "common_getindustrychilds.do";

    //获取角色详情
    public static String COMMON_GETROLEINFO = "common_getindustryinfo.do";
    //获取所有省市区
    public static String COMMON_GETREGIONS = "common_getallregions.do";
    //获取单省下面的市
    public static String COMMON_GETCITYSBYPROVINCE = "common_getcitysbyprovince.do";
    //获取单市下面的区县
    public static String COMMON_GETAREASBYCITY = "common_getareasbycity.do";

    //搜索方案列表(角色/场景)
    public static String MANAGER_SEARCHNEWSLIST = "manager_searchnewsList.do";
    //搜索用户
    public static String USER_SEARCHUSERLIST = "user_searchuserList.do";
    //搜索场景--显示场景列表
    public static String MERCHANT_SEARCHMERCHANTLIST = "merchant_searchmerchantList.do";

    //获取商家的可申请角色列表
    public static String MERCHANT_GETRECRUITROLELIST = "merchant_getrecruitroleList.do";
    //用户申请商户的某个经营角色
    public static String MANAGER_JOINMERCHANT = "manager_joinmerchant.do";
    //用户成为某经营人角色(参与角色)
    public static String MANAGER_ADDMANAGER = "manager_addmanager.do";
    //经营人用户解绑商户
    public static String MANAGER_DELMANAGER = "manager_delmanager.do";
    //获取经营人详细信息
    public static String MANAGER_GETMANAGERINFO = "manager_getmanagerinfo.do";
    //经营人修改资料(完善资料)
    public static String MANAGER_EDITMANAGER = "manager_editmanager.do";

    //用户提交(建议)新角色
    public static String USER_FEEDINDUSTRY = "user_feedindustry.do";
    //获取 商家(场景)详情
    public static String MERCHANT_GETINFO = "merchant_getinfo.do";
    //查看用户(其它人)详情
    public static String USER_INFO = "user_other_info.do";
    //获取用户的经营角色列表
    public static String USER_INDUSTRYLIST = "user_industryList.do";
    //获取用户的单角色下的场景列表
    public static String USER_INDUSTRY_MERCHANTLIST = "user_industry_merchantList.do";
    //获取用户的场景列表
    public static String USER_MERCHANTLIST = "user_merchantList.do";
    //获取用户的单场景下的角色列表
    public static String USER_MERCHANT_INDUSTRYLIST = "user_merchant_industryList.do";
    //获取用户的方案列表
    public static String USER_NEWSLIST = "user_newsList.do";
    //查看方案详情
    public static String MANAGER_NEWS_INFO = "manager_news_info.do";
    //获取单个方案下的评论列表
    public static String MANAGER_NEWS_COMMENT_LIST = "manager_news_comment_list.do";
    //用户对方案评论
    public static String MANAGER_NEWS_COMMENT_ADD = "manager_news_comment_add.do";
    //用户删除自己的评论
    public static String MANAGER_NEWS_COMMENT_DEL = "manager_news_comment_del.do";
    //用户对方案点赞
    public static String MANAGER_NEWS_GOODADD = "manager_news_goodadd.do";
    //用户对方案取消点赞
    public static String MANAGER_NEWS_GOODDEL = "manager_news_gooddel.do";
    //实体商家入驻--选择已经存在的商户时
    public static String MERCHANT_ADDENTITY_EXIST = "merchant_addentity_exist.do";
    //实体商家入驻--添加全新的商户时
    public static String MERCHANT_ADDENTITY_NEW = "merchant_addentity_new.do";
    //实体商户入驻--修改信息重新提交审核
    public static String MERCHANT_EDITENTITY_NEW = "merchant_editentity_new";
    //搜索好友列表
    public static String USER_FRIENDLIST = "user_friendList.do";
    //搜索用户(根据手机号或者斜杠号)--精确搜索
    public static String USER_SEARCHUSER = "user_searchuser.do";
    //新的朋友（接受列表)
    public static String USER_REQUEST_FRIENDLIST = "user_request_friendList.do";
    //接受好友请求
    public static String USER_FRIEND_ACCEPT = "user_friend_accept.do";
    //发送好友请求
    public static String USER_FRIEND_ADD = "user_friend_add.do";
    //删除好友
    public static String USER_FRIEND_DEL = "user_friend_delete.do";
    //设置好友资料
    public static String USER_FRIENDINFO_SET = "user_friendinfo_set.do";

    //用户（经营人）发布方案
    public static String COMMON_GETADVERLIST = "common_getadvertList.do";
    //用户（经营人）发布方案
    public static String MANAGER_NEWS_ADD = "manager_news_add.do";
    //删除方案
    public static String MANAGER_NEWS_DEL = "manager_news_del.do";
    //获取用户自身的资料
    public static String USER_INFO_OWNER = "user_info_owner.do";
    //修改用户资料信息
    public static String USER_EDITINFO = "user_editinfo.do";
    //获取 用户的 可能熟悉的人(推荐服务管家)
    public static String USER_SYS_SALEUSERLIST = "user_sys_saleUserList.do";
    //我的关注
    public static String USER_ATTRACTLIST = "user_attractList.do";
    //TA的粉丝
    public static String USER_FANSLIST_OTHER_1_2 = "user_fansList_other_1_2.do";
    //TA的关注
    public static String USER_ATTRACTLIST_OTHER_1_2 = "user_attractList_other_1_2.do";


    //用户添加关注
    public static String USER_ATTRACT_ADD = "user_attract_add.do";
    //用户取消关注
    public static String USER_ATTRACT_DEL = "user_attract_del.do";
    //删除角色
    public static String USER_INDUSTRY_DEL = "user_industry_del.do";
    //我的收藏--搜索用户收藏列表
    public static String USER_FAVORITE_LIST = "user_favorite_list.do";
    //获取个人系统消息
    public static String COMMON_SYS_MESSAGELIST = "common_sys_messageList.do";
    //用户添加收藏
    public static String USER_FAVORITE_ADD = "user_favorite_add.do";
    //    //用户取消收藏
//    public static   String USER_FAVORITE_DELETE =  "user_favorite_delete.do";
    //我是商家--用户获取商家列表
    public static String MERCHANT_USER_MERCHANTLIST = "merchant_user_merchantList.do";
    //修改商家详情
    public static String MERCHANT_EDITINFO = "merchant_editinfo.do";

    // 获取首页的行业列表
    public static String COMMON_FIRSTPAGE_INDUSTRYLIST = "common_firstpage_industryList.do";
    // 获取首页的所有行业分类(包含行业)
    public static String COMMON_FIRSTPAGE_ALL_INDUSTRYLIST = "common_firstpage_allindustryList.do";
    // 获取广播消息
    public static String COMMON_BROADCAST_INFOLIST = "common_broadcast_infoList.do";
    // 修改登录密码
    public static String USER_EDITPWD = "user_editpwd.do";

    //1.1新增
    //添加收藏--心情小记
    public static String USER_FAVORITE_ADD_NEWS = "user_favorite_news_add.do";
    //取消收藏--心情小记
    public static String USER_FAVORITE_DELETE_NEWS = "user_favorite_news_delete.do";
    //用户获取账号绑定信息
    public static String USER_BANDCARDINFO = "user_bandcard_info.do";
    //获取银行卡绑定信息
    public static String USER_BANDCARD_INFO_1_2_1 = "user_bandcard_info_1_2_1.do";
    //用户进行实名认证
    public static String USER_REALNAME_ATTESTATION = "user_realname_attestation.do";
    //获取用户实名认证信息
    public static String USER_REALNAME_INFO = "user_realname_info.do";
    //提现
    public static String USER_CASH_WITHDRAW = "user_cash_withdraw.do";
    //获取用户的当天可取余额信息
    public static final String USER_CASH_INFO = "user_cash_info";
    //筛选 通讯录中的手机号 是否为未关注的服务管家
    public static String USER_CHECK_BOOKLIST = "user_check_bookList.do";
    //获取通讯录中不是用户好友的手机号(是平台账号,发过好友申请的也不显示)
    public static String USER_CHECK_BOOKLIST_FRIEND = "user_check_bookList_friend.do";
    //搜索 任务记录 列表(用户已经完成的)
    public static String TASK_SEARCH_INDUSTRY_RECORDLIST = "task_search_industry_recordList.do";
    //首页 任务记录列表
    public static String TASK_INDUSTRY_RECORDLIST_FIRSTPAGE = "task_industry_recordList_firstpage.do";
    //查看他人的已完成任务记录列表
    public static String TASK_INDUSTRYLIST_OTHRT = "task_industryList_other.do";
    //用户获取可领取的角色任务列表(新任务)             task_industry取,用户有该角色的任务,且没有领取过的
    public static String TASK_INDUSTRYLIST_NEW = "task_industryList_new.do";
    //用户获取已经领取的任务列表(待完成,已完成,已过期)      task_industry_record取,其中已过期的--状态为已过期或者当前时间超过截止时间未完成,  已过期状态定时器修正
    public static String TASK_INDUSTRYLIST = "task_industryList.do";
    //用户获取自己发布的任务(全部,进行中,已结束)
    public static String TASK_INDUSTRYLISTPUSH = "task_industryList_push.do";
    //用户发布新的任务
    public static String TASK_INDUSTRY_PUSH = "task_industry_push.do";
    //商家发布新的角色任务
    public static String TASK_INDUSTRY_PUSH_MERCHANT = "task_industry_push_merchant.do";
    //查看他人的已完成任务记录列表
    public static String USER_INDUSTRYFRIENDLIST = "user_industryfriendList.do";
    //查看角色任务详情(新任务)
    public static String TASK_INDUSTRY_INFO = "task_industry_info.do";
    //查看自己的角色任务记录详情(用户未完成的任务)
    public static String TASK_INDUSTRY_RECORD_INFO_TODO = "task_industry_record_info_todo.do";
    //查看角色任务记录详情(已经完成的)  如果是投票类型的，需要获取投票相关信息
    public static String TASK_INDUSTRY_RECORD_INFO = "task_industry_record_info.do";
    //手动分配红包给已经完成该任务的用户
    public static String TASK_INDUSTRY_SENDBONUSE = "task_industry_sendBonuse.do";
    //查看角色任务的其它用户完成记录列表--用于详情页
    public static String TASK_INDUSTRY_RECORDLIST_OTHER = "task_industry_recordList_other.do";
    //获取角色任务的评论列表
    public static String TASK_INDUSTRY_COMMENTLIST = "task_industry_commentList.do";
    //用户对角色任务评价
    public static String TASK_INDUSTRY_COMMENT_ADD = "task_industry_comment_add.do";
    //用户删除自己的角色任务评论
    public static String TASK_INDUSTRY_COMMENT_DEL = "task_industry_comment_del.do";
    //用户对角色任务点赞
    public static String TASK_INDUSTRY_GOODADD = "task_industry_goodadd.do";
    //用户对角色任务取消点赞
    public static String TASK_INDUSTRY_GOODDEL = "task_industry_gooddel.do";
    //用户领取角色任务  添加到task_industry_record
    public static String TASK_INDUSTRY_ADD = "task_industry_add.do";
    //用户完成角色任务--图文
    public static String TASK_INDUSTRY_COMPLATE_IMG = "task_industry_complete_img.do";
    //用户完成角色任务--语音
    public static String TASK_INDUSTRY_COMPLATE_AUDIO = "task_industry_complete_audio.do";
    //用户完成角色任务--投票
    public static String TASK_INDUSRTY_COMPLETE_VOTE = "task_industry_complete_vote.do";
    //用户角色任务--生成买单
    public static String TASK_INDUSTRY_COMPLETE_PAY = "task_industry_complete_pay.do";
    //用户删除领取的角色任务(未完成/已过期)
    public static String TASK_INDUSTRY_DEL = "task_industry_del.do";
    //用户添加收藏--角色任务记录
    public static String USER_FAVORITE_TASK_ADD = "user_favorite_task_add.do";
    //用户取消收藏--角色任务记录
    public static String USER_FAVORITE_TASK_DELETE = "user_favorite_task_delete.do";
    //用户添加收藏--探索
    public static String USER_FAVORITE_TASK_INDUSTRY_ADD = "user_favorite_taskIndustry_add.do";
    //用户取消收藏--探索
    public static String USER_FAVORITE_TASK_INDUSTRY_DELETE = "user_favorite_taskIndustry_delete.do";
    //用户的等级排行榜
    public static String USER_LEVELLIST = "user_levelList.do";

    //用户的等级信息
    public static String USER_LEVELInfo = "user_level_info.do";

    //用户的等级任务列表
    public static String USER_TASKLIST = "user_taskList.do";


    //用户的代金券列表
    public static String USER_VOUCHERLIST = "user_voucherList.do";
    //用户的代金券详情信息
    public static String USER_VOUCHER_INFO = "user_voucher_info.do";
    //商户代金券列表
    public static String MERCHANT_VOUCHERLIST = "merchant_voucherList.do";
    //商家输码验单
    public static String MERCHANT_VOUCHER_CHECK = "merchant_voucher_check.do";
    //商家的代金券使用记录列表
    public static String MERCHANT_VOUCHERLIST_RECORD = "merchant_voucherList_record.do";
    //首页(搜索) 心情小记（时间倒排序）
    public static String MANAGER_NEWSLIST_SEARCH = "manager_newsList_search.do";
    //查看单任务已经完成的记录列表
    public static String TASK_INDUSTRY_RECORDLIST_TASK = "task_industry_recordList_task.do";
    //查看用户的已完成任务记录列表
    public static String TASK_INDUSTRY_RECORDLIST = "task_industry_recordList.do";
    //用户查看和他人的共同好友列表
    public static String USER_FRIENDLIST_OTHER = "user_friendList_other.do";

    //设置安全密码
    public static String USER_SETPAYPWD = "user_setpaypwd.do";
    //验证用户原安全密码
    public static String USER_CHECKPAYPWD = "user_checkpaypwd.do";
    //修改安全密码-忘记密码(验证实名认证)
    public static String USER_CHECKIDNUM = "user_checkIDNum.do";
    //修改安全密码
    public static String USER_EDITPAYPWD = "user_editpaypwd.do";
    //添加举报
    public static String COMMON_REPORT_ADD = "common_report_add.do";
    //更换绑定手机号
    public static String USER_EDITPHONE_BAND = "user_editphone_band.do";
    //用户的账户明细
    public static String USER_BILLLIST = "user_billList.do";

    //获取用户挂靠的商家列表(营销经理)
    public static String USER_SALE_MERCHANTLIST = "user_sale_merchantList.do";


    //用户的账户明细详情
    public static String USER_BILL_INFO = "user_bill_info.do";
    //用户的账户明细详情-交易类型 type=0
    public static String USER_BILL_INFO_PAY = "user_bill_info_pay.do";
    //用户的账户明细详情-绑定类型 type=2
    public static String USER_BILL_INFO_BAND = "user_bill_info_band.do";
    //用户的账户明细详情-提现类型 type=3
    public static String USER_BILL_INFO_WITHDRAW = "user_bill_info_withdraw.do";
    //用户的账户明细详情-佣金类型 type=6
    public static String USER_BILL_INFO_COMMISSION = "user_bill_info_commission.do";
    //用户的账户明细详情-佣金类型 type=7
    public static String USER_BILL_INFO_PRESENT = "user_bill_info_present";
    //用户的账户明细详情-佣金类型 type=8
    public static String USER_BILL_INFO_PRESENT_EXCHANGE = "user_bill_info_present_exchange";
    //用户的账单详情-打/赞赏类型的(type=9)
    public static String USER_BILL_INFO_REWARD = "user_bill_info_reward";
    //用户的账单详情-接收打/赞赏类型的(type=10)
    public static String USER_BILL_INFO_REWARD_GET = "user_bill_info_reward_get";

    //商家的账户明细详情
    public static String MERCHANT_BILL_INFO = "merchant_bill_info.do";
    //商家报表明细-交易类型的(type=0)
    public static String MERCHANT_BILL_INFO_PAY = "merchant_bill_info_pay.do";
    //商家报表明细-提现类型的(type=1)
    public static String MERCHANT_BILL_INFO_WITHDRAW = "merchant_bill_info_withdraw.do";
    //商家提现
    public static String MERCHANT_CASH_WITHDRAW = "merchant_cash_withdraw.do";

    //历史记录中商家的代金券明细

    public static String MERCHANT_VOUCHER_INFO = "merchant_voucher_info.do";

    //生成支付宝支付单
    public static String USER_BANDCARD_PAY = "user_bandcard_pay.do";
    //用户角色任务--生成商户买单
    public static String USER_TASK_PAY = "user_task_pay.do";

    //查看用户的(其它人)等级
    public static String USER_LEVEL = "user_level.do";
    //商家报表列表
    public static String MERCHANT_BILLLIST = "merchant_billList.do";

    //生成红包支付单
    public static String USER_BONUSE_PAY = "user_bonuse_pay.do";

    //取消收藏
    public static String USER_FAVORITE_DELETE = "user_favorite_delete";

    /**
     * 1.2
     */
    // 首页热门任务列表
    public static String TASK_INDUSTRY_LIST_FIRSTPAGE_HOT_1_2 = "task_industry_List_firstpage_hot_1_2.do";
    // 首页推荐任务列表
    public static String TASK_INDUSTRY_LIST_FIRSTPAGE_RECOMMEND_1_2 = "task_industry_List_firstpage_recommend_1_2.do";
    // 首页我的角色相关的任务列表
    public static String TASK_INDUSTRY_LIST_FIRSTPAGE_MINE_1_2 = "task_industry_List_firstpage_industry_1_2.do";
    // 首页邀请任务列表
    public static String TASK_INDUSTRY_LIST_FIRSTPAGE_INVITATION_1_2 = "task_industry_List_firstpage_invitation_1_2.do";
    // 首页活动列表
    public static String TASK_INDUSTRY_LIST_FIRSTPAGE_EVENT_1_3 = "task_industry_List_firstpage_event_1_3.do";
    //角色--猜你喜欢
    public static String TASK_INDUSTRY_RECORD_USERDO_1_2 = "task_industry_record_userdo_1_2.do";
    //角色--推荐角色
    public static String COMMON_USER_INDUSTRYLIST_1_2 = "common_user_industryList_1_2.do";
    //首页--探索任务搜索
    public static String TASK_INDUSTRY_LIST_SEARCH_1_2 = "task_industry_List_search_1_2.do";
    //角色-TA完成的探索列表
    public static String TASK_INDUSTRY_RECORDLIST_USER_1_2 = "task_industry_recordList_user_1_2.do";
    //查看单任务已经完成的记录列表
    public static String TASK_INDUSTRY_RECORDLIST_TASK_1_2 = "task_industry_recordList_task_1_2.do";
    //查看角色任务记录详情(已经完成的)
    public static String TASK_INDUSTRY_RECORD_INFO_1_2 = "task_industry_record_info_1_2.do";
    //查看探索完成详情的其它更多列表
    public static String TASK_INDUSTRY_RECORDLIST_OTHER_1_2 = "task_industry_recordList_other_1_2.do";
    //用户对探索记录评论点赞
    public static String TASK_INDUSTRY_COMMENT_GOODADD = "task_industry_comment_goodadd.do";
    //用户对探索记录评论取消点赞
    public static String TASK_INDUSTRY_COMMENT_GOODDEL = "task_industry_comment_gooddel.do";

    //我的标签包列表
    public static String USER_TAGLIST_1_2 = "user_tagList_1_2";
    //我给别人贴标签的记录列表
    public static String USER_TAGLIST_HOSTORY_1_2 = "user_tagList_history_1_2";
    //大家眼中的我的标签列表
    public static String USER_LABELLIST_1_2 = "user_labelList_1_2";
    //我的被别人贴的标签的记录列表
    public static String USER_LABELLIST_HISTORY_1_2 = "user_labelList_history_1_2";
    //贴标签
    public static String USER_LABEL_ADD_1_2 = "user_label_add_1_2";
    //删除标签--我的标签包
    public static String USER_TAG_DEL_1_2 = "user_tag_del_1_2";
    //删除标签--大家眼中的我
    public static String USER_LABEL_DEL_1_2 = "user_label_del_1_2";


    //获取 约服团队 消息未读的数量
    public static String USER_MESSAGE_SYSNUM_UNREAD_1_2_2 = "user_message_sysNum_unread_1_2_2";
    //获取 约服账单 消息未读的数量
    public static String USER_MESSAGE_BILLNUM_UNREAD_1_2_2 = "user_message_billNum_unread_1_2_2";
    //获取 我的消息列表
    public static String USER_MESSAGELIST_1_2 = "user_messageList_1_2";
    //获取 @我的 消息列表
    public static String USER_MESSAGELIST_ALERT_1_2 = "user_messageList_alert_1_2";
    //获取 评论 消息列表
    public static String USER_MESSAGELISTCOMMENT_1_2 = "user_messageList_comment_1_2";
    //获取 赞 消息列表
    public static String USER_MESSAGELIST_GOOD_1_2 = "user_messageList_good_1_2";
    //获取约服团队 消息列表
    public static String USER_MESSAGELIST_SYS_1_2_2 = "user_messageList_sys_1_2_2";
    //约服账单 消息列表
    public static String USER_MESSAGELIST_BILL_1_2_2 = "user_messageList_bill_1_2_2";

    //用户发布新的任务----非红包类状态为已发布,红包类由支付单操作变更任务状态
    public static String TASK_INDUSTRY_PUSH_1_2 = "task_industry_push_1_2";
    //用户参与探索(完成探索)
    public static String TASK_INDUSTRY_COMPLETE_1_2 = "task_industry_complete_1_2";
    //发现- 获取探索完成记录列表
    public static String TASK_SEARCH_INDUSTRY_RECORDLIST_1_2 = "task_search_industry_recordList_1_2";

    //用户在某商家下的可用的的优惠券列表
    public static String MERCHANT_VOUCHERLIST_USER = "merchant_voucherList_user";

    //通过营销经理分享的探索记录id 获取商户的信息(包含本次买单的折扣)
    public static String TASK_RECORD_INFO = "task_record_info";


    //获取 消费任务消息
    public static String USER_MESSAGELIST_CONSUMER_1_3 = "user_messageList_consumer_1_3";

    //获取 通知消息
    public static String USER_MESSAGELIST_NOTICE_1_3 = "user_messageList_notice_1_3";

    //营销经理分享消费任务--发送给好友
    public static String TASK_COMMISSION_SHARE_FRIEND_1_3 = "task_commission_share_friend_1_3";

    //用户参与活动探索--（消费类的）
    public static String TASK_INDUSTRY_COMMISSION_COMPLETE_1_3 = "task_industry_commission_complete_1_3";

    public static String COMMON_ALLINDUSTRYLIST_1_3 = "common_allindustryList_1_3";
    //用户拉黑其它人
    public static String USER_BLACK_ADD = "user_black_add.do";
    //获取商户发布的探索
    public static String TASK_INDUSTRYLIST_PUSH_MERCHANT_1_3 = "task_industryList_push_merchant_1_3.do";

    //消费报表-营销经理角色
    public static String MANAGER_CUSTOMER_BILLLIST_1_3 = "manager_customer_billList_1_3.do";

    //消费总额-营销经理角色
    public static String MANAGER_CUSTOMER_BILLINFO_1_3 = "manager_customer_billInfo_1_3.do";

    //首页商家列表          全是已上线的
    public static String MERCHANT_FIRSTPAGE_LIST_1_2_3 = "merchant_firstpage_List_1_2_3";
    //搜索商家列表          全是已上线的
    public static String MERCHANT_FIRSTPAGE_SEARCH_LIST = "merchant_firstpage_search_List.do";
    //推荐商家列表          签约商家
    public static String MERCHANT2_SEARCH_LIST_BAND = "merchant2_search_List_band.do";
    //商家详情
    public static String MERCHANT_INFO = "merchant_info";
    //获取自己的 商家(场景)详情(请求)
    public static String MERCHANT_INFO_REQUEST = "merchant_info_request";
    //商家下的营销经理列表-最近联系        有过交易的(时间倒排序)
    public static String MERCHANT_SALEUSER_LIST_LATEST_1_1 = "merchant_saleuserList_latest_1_1";
    //用户申请成为商家的营销经理
    public static String USER_ADD_SALEUSER_1_1 = "user_add_saleuser_1_1.do";
    //删除商家下的某个营销经理 删除某个营销经理下的某个商家
    public static String MERCHANT_SALEUSER_DEL = "merchant_saleuser_del";
    //删除某个约客下的某个商家
    public static String USER_SALE_MERCHANT_DEL = "user_sale_merchant_del";


    //Ta的商家列表(挂靠的)
    public static String MERCHANT_OTHER_LIST = "merchant_other_merchantList";
    //选择支付(找TA买单)
    public static String USER_MERCHANT_PAY_DO = "user_merchant_pay_do";
    //用户对单笔买单评价
    public static String USER_MERCHANT_PAY_COMMONT_1_1 = "user2_pay_commont_add.do";
    //生成支付单(找TA买单)
    public static String USER_MERCHANT_PAY_ADD = "user_merchant_pay_add";
    //生成支付宝签名字符串
    public static String COMMON_SIGN_NUMBER = "common_sign_number";
    //搜索用户(手机号或者昵称)--精确搜索
    public static String USER_SEARCHUSER_PHONE = "user_searchuser_phone";
    //微信支付
    public static String WXPAY_APPLYPAY = "wxpay_applyPay";
    //设置支付单为微信支付
    public static String USER_PAY_SETWX = "user_pay_setwx";
    //我的订单
    public static String USER_PAYLIST = "user2_payList.do";
    //订单详情
    public static String USER_PAY_INFO = "user2_pay_info.do";
    //取消订单
    public static String USER_PAY_DEL = "user_pay_del";
    //搜索商家-未挂靠
    public static String USER_UNSALE_MERCHANTLIST = "user_unsale_merchantList";
    //用户的黑名单列表
    public static String USER_BLACK_DEL = "user_black_del";
    //移除黑名单
    public static String USER_BLACK_LIST = "user_black_list";
    //获取商家相册的照片列表(非证件照)
    public static String MERCHANT_PIC_LIST = "merchant_picList";
    //商家添加相册照片
    public static String MERCHANT_PIC_ADD = "merchant_pic_add";
    //商家删除相册照片
    public static String MERCHANT_PIC_DEL = "merchant_pic_del";
    //获取用户相册的照片列表
    public static String USER_PIC_LIST = "user_picList";
    //用户添加相册照片
    public static String USER_PIC_ADD = "user_pic_add";
    //用户删除相册照片
    public static String USER_PIC_DEL = "user_pic_del";
    //商家入驻
    public static String MERCHANT_ADDENTITY_1_1 = "merchant_addentity_1_1";
    //获取服务人员标签
    public static String COMMON_SERVICETAGLIST_1_1 = "common_serviceTagList_1_1.do";
    //编辑服务标签
    public static String USER_SALE_MERCHANT_EDIT = "user_sale_merchant_edit.do";
    //获取商家申请列表(待签约)
    public static String MERCHANT_REQUEST_LIST = "merchant_requestList";
    //客户列表
    public static String USER_SALE_USERLIST = "user_sale_userList.do";
    //服务管家的交易单记录列表
    public static String USER_SALE_PAYLIST = "user2_saleUser_userPayList.do";

    //用户的预约单列表
    public static String USER_RESERVATION_LIST = "user2_reservation_list.do";
    //约客的预约记录列表     (不包含待确认的)
    public static String USER_SALE_RESERVATION_LIST = "user2_saleUser_reservationList.do";


    //用户预约单详情
    public static String USER_RESERVATION_INFO = "user2_reservation_info.do";


    //约服  1.2
    //用户预约申请
    public static String USER_RESERVATION_ADD = "user2_reservation_add.do";
    //获取用户在某个约客下的某家商户下的预约单列表
    public static String USER_RESERVATION_SALE_LIST = "user_reservation_sale_list.do";

    //约客修改预约单(答复)
    public static String USER_RESERVATION_EDIT = "user_reservation_edit.do";

    //用户的礼物列表
    public static String USER_PRESENT_LIST = "user_present_list";
    //用户兑换礼物
    public static String USER_PRESENT_EXCHANGE = "user_present_exchange";
    //用户赠送礼物-生成支付单
    public static String USER_PRESENT_PAY_ADD = "user_present_pay_add";

    //获取系统的礼物列表
    public static String COMMON_SYSPRESENTLIST = "common_syspresentList";
    //获取用户挂靠的商家列表(服务管家)
    public static String USER_SALE_MERCHANTLIST_1_2 = "user_sale_merchantList_1_2";
    //获取用户挂靠的商家列表(服务管家)
    public static String USER_SALE_MERCHANTLIST_1_2_2 = "user_sale_merchantList_1_2_2";

    //修改用户状态(服务管家状态)
    public static String USER_EDITSTATE = "user_editstate";

    // 通过预约单快捷生成支付买单(1)
    public static String USER_RESERVATION_PAY_ADD = "user_reservation_pay_add";
    // 用户打/赞赏-生成支付单（支付宝）
    public static String USER_REWARD_PAY_ADD = "user_reward_pay_add";
    // 打赏种类
    public static String COMMON_REWARD = "common_reward_1_2_2";
    // 根据金额算出用户提现手续费
    public static String COMMON_WITHCASH_COUNT = "common_withcash_count";
    //根据银行卡号获取银行卡名称
    public static String COMMON_FIND_BANKNAME = "common_findBankName";
    //绑定银行卡
    public static String USER_BAND_CARD_DO = "user_bandcard_do";
    //获取银行卡绑定信息
    public static String USER_BANDCARDINFO_1_2_1 = "user_bandcard_info_1_2_1";
    //设置备注名
    public static String USER_ATTRACT_EDIT = "user_attract_edit";
    //用户添加收藏商家
    public static String USER_FAVORITE_MERCHANT_ADD = "user_favorite_merchant_add";
    //用户的删除收藏商家
    public static String USER_FAVORITE_MERCHANT_DEL = "user_favorite_merchant_del";
    //获取 用户的 可能熟悉的人(推荐服务管家)的数量
    public static String USER_SYS_SALEUSERLIST_NUM = "user_sys_saleUserList_num";
    //获取预邀单主题
    public static String COMMON_GETALLTITLE = "common_getallTitle";
    //用户的预邀单列表
    public static String USER_PRE_INVITIONLIST = "user_pre_invitationList";
    //用户发起预邀单
    public static String USER_PRE_INVITATION_ADD = "user_pre_invitation_add";
    //用户的收藏商家
    public static String USER_FAVORITE_MERCHANTLIST = "user_favorite_merchantList.do";
    //获取商家详情的图片对应的描述列表
    public static String MERCHANT_PIC_NOTE_LIST = "merchant_pic_noteList";
    //判断用户是否可以申请商家的服务管家
    public static String USER_CHECK_SALEUSER_1_2_2 = "user_check_saleuser_1_2_2";
    //根据微信号获取绑定的用户信息
    public static String USER_WX_BAND_USER = "user_wx_band_user";
    //获取手机号是否绑定微信账号
    public static String USER_BAND_WX_INFO_PHONE = "user_band_wx_info_phone";
    //微信授权并绑定手机号登陆(注册)
    public static String USER_LOGIN_WX = "user_login_wx";
    //获取用户是否绑定微信账号
    public static String USER_BAND_WX_INFO = "user_band_wx_info";
    //绑定微信账号
    public static String USER_BAND_WX = "user_band_wx";
    //用户解绑微信账号
    public static String USER_BAND_WX_DEL = "user_band_wx_del";
    //根据用户id获取密码
    public static String USER_GET_PWD = "user_get_pwd";


    //用户对已经确认的预约单(服务单)评价
    public static String USER2_RESERVATION_COMMONT_ADD = "user2_reservation_commont_add";
    //用户对买单评价
    public static String USER2_PAY_COMMONT_ADD = "user2_pay_commont_add";
    //预约单申请
    public static String USER2_RESERVATION_ADD = "user2_reservation_add";
    //服务管家撤销预约单(未确认的)
    public static String USER2_RESERVATION_DEL = "user2_reservation_del";
    //用户确认预约单
    public static String USER2_RESERVATION_SURE = "user2_reservation_sure";
    // 生成定金支付单(用户确认预约单)
    public static String USER2_RESERVATION_PAY = "user2_reservation_pay";
    //生成支付单(找TA买单)--(关联预约单)
    public static String USER2_PAY_ADD = "user2_pay_add";
    // 用户的预约单列表
    public static String USER2_RESERVATION_LIST = "user2_reservation_list";
    // 获取用户在指定商家的服务管家下的预约单列表(确认未支付)
    public static String USER2_RESERVATION_SALE_LIST = "user2_reservation_sale_list";
    //服务管家的SCRM列表
    public static String USER2_SALEUSER_FANSLIST = "user2_saleUser_fansList";
    //服务管家用户的商家列表(所有)
    public static String MERCHANT2_SALEUSER_MERCHANTLIST = "merchant2_saleUser_merchantList";
    //用户发送语音预约消息
    public static String USER2_REMINDIM_ADD = "user2_remindIM_add";
    //用户一键注册服务管家
    public static String USER2_SALEUSER_LOGIN = "user2_saleUser_login";
    //修改服务管家信息(标签,状态,职位)
    public static String USER2_SALE_MERCHANT_EDIT = "user2_sale_merchant_edit";
    //用户更新服务管家通讯录
    public static String USER2_REFRESH_SALEUSER = "user2_reflash_saleuser";
    //获取职位
    public static String COMMON_POSITION = "common_position";
    //商家下的服务管家列表-我关注的
    public static String MERCHANT2_SALEUSER_LIST_FAN = "merchant2_saleuserList_fan";
    //商家下的服务管家列表-全部          1:去掉熟人和自己,2:排序规则：最近有交易的在前，等级高的在前
    public static String MERCHANT2_SALEUSER_LIST = "merchant2_saleuserList";
    //查看语音预约消息信息
    public static String USER2_REMINDIM_INFO = "user2_remindIM_info";
    //修改 语音预约消息为已读
    public static String USER2_REMINDIM_READ = "user2_remindIM_read";
    //首页 用户自己所在的服务指数最高的商家
    public static String USER2_FIRST_SALEUSER = "user2_first_saleUser";
    //服务管家的评价列表
    public static String USER2_SALEUSER_COMMENTLIST = "user2_saleUser_commentList.do";

    //服务方案库列表
    public static String USER2_PLAN_LIST = "user2_planList";
    //发布服务方案
    public static String USER2_PLAN_ADD = "user2_plan_add";
    //修改服务方案
    public static String USER2_PLAN_EDIT = "user2_plan_edit";
    //发布服务方案
    public static String USER2_PLAN_DEL = "user2_plan_del";
    //发布服务方案
    public static String USER2_PLAN_INFO = "user2_plan_info";
    //管家保存服务数据到临时表
    public static String MERCHANT2_SAVERESORDERTMP = "merchant2_saveResOrderTmp";

    //设置服务管家的评价列表为已读
    public static String USER2_SALEUSER_COMMENT_SETREAD = "user2_saleUser_comment_setRead";
    //手机号绑定微信并且登录
    public static String USER_WX_BAND_PHONE = "user_wx_band_phone";
    //发送短信(临时服务订单)
    public static String USER2_RES_ORDER_TMP_SEND = "user2_res_order_tmp_send";
    //我的邀请函列表
    public static String USER2_ORDERINVITATION_LIST = "user2_orderInvitation_list";
    //我的被邀请函列表
    public static String USER2_ORDERINVITATION_FEED_LIST = "user2_orderInvitation_feed_list";
    //生成邀请函(服务单)
    public static String USER2_RESERVATION_EDITINVITATION = "user2_reservation_editInvitation";

    //更新用户的的位置定位信息
    public static String COMMON_CHANGEUSERCITY = "common_changeUserCity";
    //用户验证码登录
    public static String USER_LOGIN_2_3 = "user_login_2_3";


//管家1.0接口

    //获取最新版本的管家app软件
    public static String COMMON_APP_VERSION_SERVICE = "common_app_version_service";
    //搜索 商家()
    public static String SALEUSER_MERCHANT_SEARCH_LIST = "saleuser_merchant_search_List";
    //推荐商家()
    public static String SALEUSER_MERCHANT_HOT_LIST = "saleuser_merchant_hot_list";
    //获取服务项列表(注册管家选择)
    public static String COMMON_MERCHANT_SERVICE_LIST = "common_merchant_service_list";
    //用户注册申请成为商家的服务管家(没有就新增)
    public static String SALEUSER_ADD_MERCHANT = "saleuser_add_merchant";
    //管家登陆
    public static String SALEUSER_LOGIN = "saleuser_login";
    //获取管家的首页信息
    public static String SALEUSER_INFO = "saleuser_info";
    //管家发预约单(临时)
    public static String SALEUSER_RESERVATION_ADD = "saleuser_reservation_add";
    //商家的服务方案列表(已上架的)
    public static String MERCHANT2_PLAN_LIST = "merchant2_plan_list";
    //商家的产品列表(已上架的)
    public static String MERCHANT2_PRODUCT_LIST = "merchant2_product_list";
    //获取服务管家的可用于生成买单的预约单列表
    public static String SALEUSER_RESERVATION_CHOOSE_LIST = "saleuser_reservation_choose_list";
    //服务管家修改预约单(未确认的)
    public static String USER2_RESERVATION_EDIT = "user2_reservation_edit";
    //查看预约单详情
    public static String USER2_RESERVATION_INFO = "user2_reservation_info";
    //买单收款
    public static String SALEUSER_PAY = "saleuser_pay";


}
