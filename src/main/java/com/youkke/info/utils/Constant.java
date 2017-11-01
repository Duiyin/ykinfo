package com.youkke.info.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constant {
	
	public final static String USER_UPLOAD_PATH = "/u/upload";
	
	public final static Set<String> USER_CONFIGS = new HashSet<String>(Arrays.asList(new String[]{
			"notice_email",//邮件消息通知
			"notice_mobile",//手机消息通知
			"security_befriends",//允许被加为好友：normal允许,deny拒绝加入,verify需要验证
			"security_becommunity",//允许被邀请到群组：normal允许,deny拒绝加入,verify需要验证
			"security_beevent",//允许被邀请到活动：normal允许,deny拒绝加入,verify需要验证
			"security_becourse",//允许被邀请到课程：normal允许,deny拒绝加入,verify需要验证
			"security_betask",//允许被邀请到任务：normal允许,deny拒绝加入,verify需要验证
			"security_profile",//是否公开自己的资料：public,private,friend
			"security_record",//是否公开自己的简历：public,private,friend
			"security_contact"//是否公开自己的联系方式：public,private,friend
	}));
	public final static String USER_CONFIG_BEFRIENDS = "befriends";
	public final static String USER_CONFIG_VAL_NORMAL = "normal";
	public final static String USER_CONFIG_VAL_DENY = "deny";
	public final static String USER_CONFIG_VAL_VERIFY = "verify";
	public final static String USER_CONFIG_VAL_PUBLIC = "public";
	public final static String USER_CONFIG_VAL_PRIVATE = "private";
	public final static String USER_CONFIG_VAL_FRIEND = "friend";
	
	//shop
	public final static String WAIT_PAY = "wait_pay";	//交易创建
	public final static String TRADE_FINISHED = "trade_finished";	//交易成功结束
	public final static String TRADE_CLOSED = "trade_closed";		//交易关闭
	//shop
	public final static String USER_TYPE_PERSONAL = "p";
	public final static String USER_TYPE_SHOP = "s";
	public final static String USER_TYPE_COMPANY = "c";
	
	public final static String USER_SEX_MALE = "male";
	public final static String USER_SEX_FEMALE = "female";
	
	public final static String YES = "yes";			//是
	public final static String NO = "no";			//否
	public final static String CANCEL = "cancel";	//取消
	public final static String DEFAULT = "default";	//正常
	public final static String AUDIT = "audit";//审核中，用户认证用到
	
	//权限
	public final static String PERMISSION_OWNER = "owner";	//拥有者权限
	public final static String PERMISSION_ADMIN = "admin";  //管理者权限
	public final static String PERMISSION_NORMAL = "normal";//普通权限
	public final static String PERMISSION_BLACKLIST = "blacklist";		//黑名单
	public final static String PERMISSION_NOSPEAK = "nospeak";		//禁言
	public final static String PERMISSION_EXCLUDE = "exclude";		//排除
	public final static String PERMISSION_VERIFY = "verify";		//等待验证
	public final static String PERMISSION_FINISHED = "finished";		//等待验证
	public final static String PERMISSION_FAILED = "failed";		//等待验证
	//权限
	public final static String STATUS_NOSPEAK = "nospeak";
	
	//public final static Set<String> set = new HashSet<String>(Arrays.asList(new String[]{"pass","deny","delete"}));
		
	public final static String UNIT_YEAR = "year";
	public final static String UNIT_MONTH = "month";
	public final static String UNIT_FREE = "free";
	
	public final static String EXPIRED = "expired";//合同已过期
	public final static String LATEST = "latest";//最新合同
	
	//语言
	//public final static Set<String> LOCALE = new HashSet<String>(Arrays.asList(new String[]{"zh_CN","zh_TW","en_US"}));
	//public final static Set<String> LOCALE = LocaleUtil.getLocales();
	public final static String zh_CN = "zh_CN";
	
	//分享范围：公开，私有，所有好友，指定好友，指定社群
	public final static Set<String> SCOPE = new HashSet<String>(Arrays.asList(new String[]{"public","private","friend","friends","communities"}));
	public final static String PUBLIC = "public";
	public final static String PRIVATE = "private";//OK
	public final static String FRIEND = "friend";
	public final static String FRIENDS = "friends";
	public final static String COMMUNITIES = "communities";
	
	//安全设置
	public final static Set<String> SECURITY = new HashSet<String>(Arrays.asList(new String[]{"comment_deny","join_verify","public","deny"}));
	public final static String JOIN_VERIFY = "join_verify";//加入需要验证
	public final static String DENY = "deny";//拒绝任何人加入
	public final static String COMMENT_DENY = "comment_deny";//OK
	public final static String FOLLOW_VERIFY = "follow_verify";
	/*public final static String ACCESS_DENY = "access_deny";*/
	
	//信息的状态:首页，置顶，推荐，锁定＝不允许删除修改，不会显示，但作为污点记录。
	public final static Set<String> STATUS = new HashSet<String>(Arrays.asList(new String[]{"normal","locked","home","top","recommend","delete"}));
	public final static String NORMAL = "normal";	//正常
	public final static String LOCKED = "locked";
	public final static String HOME = "home";
	public final static String TOP = "top";
	public final static String RECOMMEND = "recommend";
	public final static String DELETE = "delete";//社群被标记为删除
	//TODO 状态要根据不同的对象分开
	
	//社群状态
	public final static Set<String> COMMUNITY_STATUS = new HashSet<String>(Arrays.asList(new String[]{"normal","trash"}));
	public final static String COMMUNITY_NORMAL = "normal";	//正常
	public final static String COMMUNITY_TRASH = "trash";
	//社群安全设置
	//public final static String COMMUNITY_JOINVERIFY = "joinverify";
	//public final static String COMMUNITY_JOINDENY = "joindeny";
	//public final static String COMMUNITY_PUBLIC = "public";
	
	//课程状态
	public final static Set<String> COURSE_STATUS = new HashSet<String>(Arrays.asList(new String[]{"normal","process","finish","trash"}));
	public final static String COURSE_NORMAL = "normal";	//正常
	public final static String COURSE_PROCESS = "process";
	public final static String COURSE_FINISH = "finish";
	public final static String COURSE_TRASH = "trash";
	
	//活动状态
	public final static Set<String> EVENT_STATUS = new HashSet<String>(Arrays.asList(new String[]{"normal","process","finish","trash"}));
	public final static String EVENT_NORMAL = "normal";	//正常
	public final static String EVENT_PROCESS = "process";
	public final static String EVENT_FINISH = "finish";
	public final static String EVENT_TRASH = "trash";
	
	//任务状态
	public final static Set<String> TASK_STATUS = new HashSet<String>(Arrays.asList(new String[]{"normal","process","finish","trash"}));
	public final static String TASK_NORMAL = "normal";	//正常
	public final static String TASK_PROCESS = "process";
	public final static String TASK_FINISH = "finish";
	public final static String TASK_TRASH = "trash";
	
	//收货地址状态
	public final static Set<String> STADDRESS_STATUS = new HashSet<String>(Arrays.asList(new String[]{"normal","default"}));
	public final static String STADDRESS_NORMAL = "normal";	//正常
	public final static String STADDRESS_DEFAULT = "default";//默认地址
	
	//用户状态
	public final static Set<String> USERSTATUS = new HashSet<String>(Arrays.asList(new String[]{"normal","audit","home","top","recommend"}));
	
	//********信息**********//
	public final static Set<String> INFO_TYPE = new HashSet<String>(Arrays.asList(new String[]{"a"}));
	public final static String INFO_A = "a";
	//********信息**********//
	
	//********相册**********//
	public final static Set<String> ALBUM_TYPE = new HashSet<String>(Arrays.asList(new String[]{"a"}));
	public final static String ALBUM_A = "a";
	//********相册**********//
	
	//********活动**********//
	public final static Set<String> EVENT_TYPE = new HashSet<String>(Arrays.asList(new String[]{"a"}));
	public final static String EVENT_A = "a";
	//********活动**********//
	
	//********商品**********//
	public final static Set<String> GOODS_TYPE = new HashSet<String>(Arrays.asList(new String[]{"goods","theme"}));
	//********商品**********//
	
	//********活动**********//
	public final static Set<String> JOB_TYPE = new HashSet<String>(Arrays.asList(new String[]{"a"}));
	public final static String JOB_A = "a";
	//********活动**********//
	
	//********社群**********//
	//类型
	public final static Set<String> COMMUNITY_TYPE = new HashSet<String>(Arrays.asList(new String[]{"ct_personal","ct_company","ct_school"}));
	public final static String COMMUNITY_PERSONAL = "ct_personal";
	public final static String COMMUNITY_COMPANY = "ct_company";
	public final static String COMMUNITY_SCHOOL = "ct_school";
	//********社群**********//
	
	//********课程**********//
	//类型
	public final static Set<String> COURSE_TYPE = new HashSet<String>(Arrays.asList(new String[]{"default","ct_b","ct_c"}));
	public final static String COURSE_DEFAULT = "default";
	public final static String COURSE_B = "ct_b";
	public final static String COURSE_C = "ct_c";
	//********课程**********//
	
	//********任务**********//
	//类型
	public final static Set<String> TASK_TYPE = new HashSet<String>(Arrays.asList(new String[]{"a","b","c"}));
	public final static String TASK_A = "a";
	//public final static String TASK_COMPANY = "ct_company";
	//public final static String TASK_SCHOOL = "ct_school";
	//********任务**********//
	public final static Set<String> TASK_EMERLEVEL = new HashSet<String>(Arrays.asList(new String[]{"normal","important","urgent"}));
	public final static String TASK_EMERLEVEL_NORMAL = "normal";
	public final static String TASK_EMERLEVEL_IMPORTANT = "important";
	public final static String TASK_EMERLEVEL_URGENT = "urgent";
	
	public final static Set<String> ALLOWCMSSET = new HashSet<String>(Arrays.asList(new String[]{"public","verify","deny"}));
	//public final static Set<String> INFOTYPESET = new HashSet<String>(Arrays.asList(new String[]{ }));
	//public final static Set<String> GOODSTYPESET = new HashSet<String>(Arrays.asList(new String[]{ "goods","theme","service" }));
	public final static Set<String> COMMUNITYTYPESET = new HashSet<String>(Arrays.asList(new String[]{ "a","b","c" }));
	
	public final static Set<String> IMAGE_FORMAT = new HashSet<String>(Arrays.asList(new String[]{"jpeg","jpg","png","bmp","wbmp","gif"}));
	//public final static Set<String> INFOSTATUSSET = new HashSet<String>(Arrays.asList(new String[]{"normal","home","audit","top","recommend"}));
	//public final static Set<String> GOODSSTATUSSET = new HashSet<String>(Arrays.asList(new String[]{"normal","home","audit","top","recommend"}));
	
	public final static Set<String> PHOTO_TYPE = new HashSet<String>(Arrays.asList(new String[]{"publish", "album"}));
	public final static String PUBLISH = "publish";
	
	public final static Set<String> VIDEO_TYPE = new HashSet<String>(Arrays.asList(new String[]{"external", "normal"}));
	public final static String EXTERNAL = "external";
	
	public final static Set<String> USERCATE_TYPE = new HashSet<String>(Arrays.asList(new String[]{"info","goods","job","event","album","video","theme"}));
	public final static String INFO = "info";
	public final static String ARTICLE = "article";
	public final static String GOODS = "goods";
	public final static String EVENT = "event";
	public final static String ALBUM = "album";
	public final static String VIDEO = "video";
	public final static String THEME = "theme";
	public final static String JOB = "job";
	
	public final static Set<String> CMCATE_TYPE = new HashSet<String>(Arrays.asList(new String[]{"info","goods","job","event","album","video","theme"}));
	public final static String CMCATE_TYPE_INFO = "info";
	public final static String CMCATE_TYPE_GOODS = "goods";
	public final static String CMCATE_TYPE_EVENT = "event";
	public final static String CMCATE_TYPE_ALBUM = "album";
	public final static String CMCATE_TYPE_VIDEO = "video";
	public final static String CMCATE_TYPE_THEME = "theme";
	
	public final static Set<String> USERTYPESET = new HashSet<String>(Arrays.asList(new String[]{"c","s","p"}));
	
	//public final static Set<String> JOBUSERSTATUS = new HashSet<String>(Arrays.asList(new String[]{"normal","draft"}));
	
	
	public final static Set<String> FUNCTIONS = new HashSet<String>(Arrays.asList(new String[]{
			"info_create",
			"article_create",
			"goods_create"
	}));
	
	//签到状态：正常，迟到，旷课，请假
	public final static Set<String> SIGNSTATUS = new HashSet<String>(Arrays.asList(new String[]{
			"normal",
			"late",
			"truancy",
			"leave"
	}));
	public final static String SIGNSTATUS_NORMAL = "normal";
	public final static String SIGNSTATUS_LATE = "late";
	public final static String SIGNSTATUS_NOT = "not";//未签到，仅用于查询时输入t
	public final static String SIGNSTATUS_LEAVE = "leave";
	
	//评论类型：1默认评论comment，2喜欢，仅一次like；3打分，仅一次rating
	public final static Set<String> COMMENTTYPE = new HashSet<String>(Arrays.asList(new String[]{
			"comment",
			"like",
			"rating"
	}));
	public final static String COMMENTTYPE_COMMENT = "comment";
	public final static String COMMENTTYPE_LIKE = "like";
	public final static String COMMENTTYPE_RATING = "rating";
	
	//public final static Set<String> COMMUNITY_STATUS_SET = new HashSet<String>(Arrays.asList(new String[]{"normal"}));
	//public final static Set<String> COMMUNITY_USERSTATUS_SET = new HashSet<String>(Arrays.asList(new String[]{"normal"}));
	//public final static String COMMUNITY_USERSTATUS_NORMAL = "normal";
	//public final static Set<String> COMMUNITY_SECURITY_SET = new HashSet<String>(Arrays.asList(new String[]{"public", "access_deny", "join_verify"}));
	
	
	
	//加招聘福利数组
	
	public final static String HOST = "http://wxine.com";
	public final static String URL_INFO = HOST+"/info.htm?i=";
	public final static String URL_PHOTO = HOST+"/photo.htm?p=";
	public final static String URL_VIDEO = HOST+"/video.htm?v=";
	public final static String URL_EVENT = HOST+"/event.htm?e=";
	public final static String URL_GOODS = HOST+"/goods.htm?g=";
	public final static String URL_USER = HOST+"/user.htm?u=";
	public final static String URL_JOB = HOST+"/job.htm?j=";
	public final static String URL_THEME = HOST+"/theme.htm?t=";
	public final static String URL_COMMUNITY = HOST+"/community.htm?c=";
	public final static String URL_COURSE = HOST+"/course.htm?cs=";
	public final static String URL_TASK = HOST+"/task.htm?t=";
	
}
