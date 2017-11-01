package com.youkke.info.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import com.alibaba.fastjson.JSON;
import com.youkke.info.utils.Constant;
import com.youkke.utils.HtmlUtil;
import com.youkke.utils.ServiceException;

public class BaseServices {

	protected boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	protected String validScope(String scope) {
		return validScope(scope, null);
	}

	protected String validScope(String scope, String defValue) {
		if (!Constant.SCOPE.contains(scope)) {
			if (StringUtils.isNotBlank(defValue) && Constant.SCOPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.PUBLIC;
			}
		} else {
			return scope;
		}
	}

	protected String validFriend(String scope, Set<String> friends) {
		return validFriend(scope, friends, null);
	}

	protected String validFriend(String scope, Set<String> friends, Set<String> defValue) {
		try {
			if (friends.size() > 0
					&& (StringUtils.equals(scope, "friends") || StringUtils.equals(scope, "communities"))) {
				return JSON.toJSONString(friends);
			} else if (null != defValue && defValue.size() > 0) {
				return JSON.toJSONString(defValue);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	protected String validString(String str) {
		return validString(str, true, null);
	}

	protected String validString(String str, boolean nullable) {
		return validString(str, nullable, null);
	}

	protected String validString(String str, boolean nullable, String defValue) {
		String nohtml = HtmlUtil.cleanText(str);
		if (!StringUtils.isNotBlank(nohtml)) {
			String defnohtml = HtmlUtil.cleanText(defValue);
			if (StringUtils.isNotBlank(defnohtml)) {
				return defnohtml;
			} else if (nullable) {
				return null;
			} else {
				throw new ServiceException("is_null",  "error");
			}
		} else {
			return nohtml;
		}
	}

	protected Double validDouble(String str) {
		return validDouble(str, true, null);
	}

	protected Double validDouble(String str, boolean nullable) {
		return validDouble(str, nullable, null);
	}

	protected Double validDouble(String str, boolean nullable, Double defValue) {
		if (!StringUtils.isNotBlank(str)) {
			if (null != defValue) {
				return defValue;
			} else if (nullable) {
				return null;
			} else {
				throw new ServiceException("is_null",  "error");
			}
		} else {
			try {
				return Double.valueOf(str);
			} catch (NumberFormatException e) {
				throw new ServiceException("double_format_error",
						 "error");
			}
		}
	}

	protected Integer validInteger(String str) {
		return validInteger(str, true, null);
	}

	protected Integer validInteger(String str, boolean nullable) {
		return validInteger(str, nullable, null);
	}

	protected Integer validInteger(String str, boolean nullable, Integer defValue) {
		if (!StringUtils.isNotBlank(str)) {
			if (null != defValue) {
				return defValue;
			} else if (nullable) {
				return null;
			} else {
				throw new ServiceException("is_null", "error");
			}
		} else {
			try {
				return Integer.valueOf(str);
			} catch (NumberFormatException e) {
				throw new ServiceException("integer_format_error",
						 "error");
			}
		}
	}

	protected Timestamp validTimestamp(String str) {
		return validTimestamp(str, true, null);
	}

	protected Timestamp validTimestamp(String str, boolean nullable) {
		return validTimestamp(str, nullable, null);
	}

	protected Timestamp validTimestamp(String str, boolean nullable, Timestamp defValue) {
		if (!StringUtils.isNotBlank(str)) {
			if (null != defValue) {
				return defValue;
			} else if (nullable) {
				return null;
			} else {
				throw new ServiceException("is_null",  "error");
			}
		} else {
			try {
				FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
				Date date = fdf.parse(str);
				String time = fdf.format(date);
				//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//Date date = df.parse(str);
				//String time = df.format(date);
				return Timestamp.valueOf(time);
			} catch (ParseException e) {
				throw new ServiceException("time_format_error", 
							"error");
			} catch (Exception e) {
				throw new ServiceException("valid_time_error", 
						"error");
			}
		}
	}

	protected String validSecurity(String security) {
		return validSecurity(security, null);
	}

	protected String validSecurity(String security, String defValue) {
		if (!StringUtils.isNotBlank(security))
			return null;
		if (!Constant.SECURITY.contains(security)) {
			if (StringUtils.isNotBlank(defValue) && Constant.SECURITY.contains(defValue)) {
				return defValue;
			} else {
				return Constant.NORMAL;
			}
		} else {
			return security;
		}
	}

	/**
	 * 较验信息内容，默认允许为空
	 * 
	 * @param content
	 * @return
	 */
	protected String validContent(String content) {
		return validContent(content, true, null);
	}

	/**
	 * 较验信息内容
	 * 
	 * @param content
	 * @param nullable
	 * @return
	 */
	protected String validContent(String content, boolean nullable) {
		return validContent(content, nullable, null);
	}

	/**
	 * 较验信息内容
	 * 
	 * @param content
	 * @param nullable
	 * @param defValue
	 * @return
	 */
	protected String validContent(String content, boolean nullable, String defValue) {
		// String nohtml = HtmlUtil.cleanHtmlRelaxed(content);
		String nohtml = content;
		if (!StringUtils.isNotBlank(nohtml)) {
			// String defnohtml = HtmlUtil.cleanHtmlRelaxed(defValue);
			String defnohtml = defValue;
			if (StringUtils.isNotBlank(defnohtml)) {
				return defnohtml;
			} else if (nullable) {
				return null;
			} else {
				throw new ServiceException("content_is_null", 
						"error");
			}
		} else {
			return nohtml;
		}
	}

	/**
	 * 较验外部视频链接地址
	 * 
	 * @param content
	 * @param nullable
	 * @param defValue
	 * @return
	 */
	protected String validContentVideo(String content, boolean nullable, String defValue) {
		String nohtml = HtmlUtil.cleanHtmlVideo(content);
		if (!StringUtils.isNotBlank(nohtml)) {
			String defnohtml = HtmlUtil.cleanHtmlRelaxed(defValue);
			if (StringUtils.isNotBlank(defnohtml)) {
				return defnohtml;
			} else if (nullable) {
				return null;
			} else {
				throw new ServiceException("content_is_null", 
						"error");
			}
		} else {
			return nohtml;
		}
	}

	protected Integer validCsort(Integer csort) {
		return validCsort(csort, -1);
	}

	protected Integer validCsort(Integer csort, Integer defValue) {
		try {
			if (csort > 0) {
				return csort;
			} else if (defValue > 0) {
				return defValue;
			} else {
				return 10;
			}
		} catch (Exception e) {
			return 10;
		}
	}

	/**
	 * 取第一张图片，返回值允许为空
	 * 
	 * @param images
	 * @return
	 */
	protected String validImage(List<String> images) {
		return validImage(images, true, null);
	}

	/**
	 * 取第一张图片
	 * 
	 * @param images
	 * @param nullable
	 * @return
	 */
	protected String validImage(List<String> images, boolean nullable) {
		return validImage(images, nullable, null);
	}

	/**
	 * 取第一张图片
	 * 
	 * @param images
	 * @param nullable
	 * @param defValue
	 * @return
	 */
	protected String validImage(List<String> images, boolean nullable, String defValue) {
		try {
			return images.iterator().next();
		} catch (Exception e) {
			if (StringUtils.isNotBlank(defValue)) {
				return defValue;
			} else if (nullable) {
				return null;
			} else {
				throw new ServiceException("image_is_null", 
						"error");
			}
		}
	}

	protected String validImage(String image) {
		return validImage(image, true, null);
	}

	protected String validImage(String image, boolean nullable) {
		return validImage(image, nullable, null);
	}

	protected String validImage(String image, boolean nullable, String defValue) {
		if (!StringUtils.isNotBlank(image)) {
			if (StringUtils.isNotBlank(defValue)) {
				return defValue;
			} else if (nullable) {
				return null;
			} else {
				throw new ServiceException("image_is_null", 
						"error");
			}
		} else {
			return image;
		}
	}

	protected String validStatus(String status) {
		return validStatus(status, null);
	}

	protected String validStatus(String status, String defValue) {
		if (!Constant.STATUS.contains(status)) {
			if (StringUtils.isNotBlank(defValue) && Constant.STATUS.contains(defValue)) {
				return defValue;
			} else {
				return Constant.NORMAL;
			}
		} else {
			return status;
		}
	}

	protected String validCommunityStatus(String status) {
		return validCommunityStatus(status, null);
	}

	protected String validCommunityStatus(String status, String defValue) {
		if (!Constant.COMMUNITY_STATUS.contains(status)) {
			if (StringUtils.isNotBlank(defValue) && Constant.COMMUNITY_STATUS.contains(defValue)) {
				return defValue;
			} else {
				return Constant.COMMUNITY_NORMAL;
			}
		} else {
			return status;
		}
	}

	protected String validStaddressStatus(String status) {
		return validStaddressStatus(status, null);
	}

	protected String validStaddressStatus(String status, String defValue) {
		if (!Constant.STADDRESS_STATUS.contains(status)) {
			if (StringUtils.isNotBlank(defValue) && Constant.STADDRESS_STATUS.contains(defValue)) {
				return defValue;
			} else {
				return Constant.STADDRESS_NORMAL;
			}
		} else {
			return status;
		}
	}

	protected String validCourseStatus(String status) {
		return validCourseStatus(status, null);
	}

	protected String validCourseStatus(String status, String defValue) {
		if (!Constant.COURSE_STATUS.contains(status)) {
			if (StringUtils.isNotBlank(defValue) && Constant.COURSE_STATUS.contains(defValue)) {
				return defValue;
			} else {
				return Constant.COURSE_NORMAL;
			}
		} else {
			return status;
		}
	}

	protected String validEventStatus(String status) {
		return validCourseStatus(status, null);
	}

	protected String validEventStatus(String status, String defValue) {
		if (!Constant.EVENT_STATUS.contains(status)) {
			if (StringUtils.isNotBlank(defValue) && Constant.EVENT_STATUS.contains(defValue)) {
				return defValue;
			} else {
				return Constant.EVENT_NORMAL;
			}
		} else {
			return status;
		}
	}

	protected String validTaskStatus(String status) {
		return validTaskStatus(status, null);
	}

	protected String validTaskStatus(String status, String defValue) {
		if (!Constant.TASK_STATUS.contains(status)) {
			if (StringUtils.isNotBlank(defValue) && Constant.TASK_STATUS.contains(defValue)) {
				return defValue;
			} else {
				return Constant.TASK_NORMAL;
			}
		} else {
			return status;
		}
	}

	protected String validUserstatus(String userstatus) {
		return validUserstatus(userstatus, null);
	}

	protected String validUserstatus(String userstatus, String defValue) {
		if (!Constant.USERSTATUS.contains(userstatus)) {
			if (StringUtils.isNotBlank(defValue) && Constant.USERSTATUS.contains(defValue)) {
				return defValue;
			} else {
				return Constant.NORMAL;
			}
		} else {
			return userstatus;
		}
	}

	protected String validAlbumType(String type, String defValue) {
		if (!Constant.ALBUM_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.ALBUM_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.ALBUM_A;
			}
		} else {
			return type;
		}
	}

	protected String validInfoType(String type, String defValue) {
		if (!Constant.INFO_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.INFO_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.INFO_A;
			}
		} else {
			return type;
		}
	}

	protected String validEventType(String type, String defValue) {
		if (!Constant.EVENT_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.EVENT_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.EVENT_A;
			}
		} else {
			return type;
		}
	}

	protected String validGoodsType(String type, String defValue) {
		if (!Constant.GOODS_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.GOODS_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.GOODS;
			}
		} else {
			return type;
		}
	}

	protected String validJobType(String type, String defValue) {
		if (!Constant.JOB_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.JOB_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.JOB_A;
			}
		} else {
			return type;
		}
	}

	protected String validCommunityType(String type, String defValue) {
		if (!Constant.COMMUNITY_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.COMMUNITY_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.COMMUNITY_PERSONAL;
			}
		} else {
			return type;
		}
	}

	protected String validCourseType(String type, String defValue) {
		if (!Constant.COURSE_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.COURSE_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.COURSE_DEFAULT;
			}
		} else {
			return type;
		}
	}

	protected String validTaskType(String type, String defValue) {
		if (!Constant.TASK_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.TASK_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.TASK_A;
			}
		} else {
			return type;
		}
	}

	protected String validTaskEmerlevel(String emerlevel, String defValue) {
		if (!Constant.TASK_EMERLEVEL.contains(emerlevel)) {
			if (StringUtils.isNotBlank(defValue) && Constant.TASK_EMERLEVEL.contains(defValue)) {
				return defValue;
			} else {
				return Constant.TASK_EMERLEVEL_NORMAL;
			}
		} else {
			return emerlevel;
		}
	}

	protected String validPhotoType(String type, String defValue) {
		if (!Constant.PHOTO_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.PHOTO_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.PUBLISH;
			}
		} else {
			return type;
		}
	}

	protected String validVideoType(String type, String defValue) {
		if (!Constant.PHOTO_TYPE.contains(type)) {
			if (StringUtils.isNotBlank(defValue) && Constant.PHOTO_TYPE.contains(defValue)) {
				return defValue;
			} else {
				return Constant.PUBLISH;
			}
		} else {
			return type;
		}
	}
	
}
