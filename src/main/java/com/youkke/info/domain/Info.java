package com.youkke.info.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.alibaba.fastjson.JSON;
import com.youkke.info.utils.Constant;
import com.youkke.utils.HtmlUtil;
import com.youkke.utils.ID;
import com.youkke.utils.SubString;
import com.youkke.utils.Time;

@Entity
@Table(name = "info")
@FilterDefs({ @FilterDef(name = "comments", parameters = { @ParamDef(name = "embed", type = "string") }) })
public class Info implements java.io.Serializable {
	private static final long serialVersionUID = 6264585795647262522L;

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;
	@Column(name = "userid", length = 32)
	private String userid;
	@Transient
	private User user;

	@Column(name = "communityid", length = 32)
	private String communityid;
	@Transient
	private Community community;

	@Column(name = "eventid", length = 32)
	private String eventid;
	@Transient
	private Event event;

	@Column(name = "taskid", length = 32)
	private String taskid;
	@Transient
	private Task task;

	private String syscateid;
	private String cmcateid;
	private String scope;
	private String friend;
	private String title;
	private String image;
	private String link;
	private String markdown;
	private String content;
	private String status;
	private Integer ilike;
	private Integer score;
	private Integer cmcount;
	private Integer sharecount;
	private Integer readcount;
	private String config;
	private Double latitude;
	private Double longitude;
	@Column(name = "ctime", nullable = false, length = 19)
	private Timestamp ctime;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "info")
	@OrderBy(value = "ctime DESC")
	@Filter(name = "comments", condition = "embed=:embed")
	private Set<Comment> comments = new HashSet<Comment>(0);

	@Transient
	private Set<String> scopes = new HashSet<String>(0);

	public Set<String> getScopes() {
		try {
			String[] array = scope.split(",");
			for (String a : array) {
				if (StringUtils.isNotBlank(a)) {
					scopes.add(StringUtils.trim(StringUtils.strip(a)));
				}
			}
		} catch (Exception e) {
		}
		return scopes;
	}

	public void setScopes(Set<String> scopes) {
		this.scopes = scopes;
	}

	public String existScope(String scope) {
		try {
			Set<String> set = getScopes();
			if (set.contains(scope)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}

	@Transient
	private String basiccontent;

	public String getBasiccontent() {
		try {
			basiccontent = HtmlUtil.cleanHtml(content);
		} catch (Exception e) {
		}
		return basiccontent;
	}

	public String getBasiccontent(int length) {
		return getBasiccontent(length, "");
	}

	public String getBasiccontent(int length, String tag) {
		try {
			if (!StringUtils.isNotBlank(tag)) {
				tag = "...";
			}
			return SubString.substring(HtmlUtil.cleanHtml(content), length, tag);
		} catch (Exception e) {
		}
		return basiccontent;
	}

	public boolean checkSetup(String property) {
		try {
			List<Config> list = JSON.parseArray(config, Config.class);
			for (Config setup : list) {
				if (StringUtils.equals(setup.getProperty(), Constant.COMMENT_DENY)) {
					if (StringUtils.equals(setup.getValue(), Constant.YES)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	public Info() {
	}

	public Info(String userid) {
		this.userid = userid;
		this.id = ID.uuid();
		this.status = Constant.NORMAL;
		this.ilike = 0;
		this.score = 0;
		this.cmcount = 0;
		this.sharecount = 0;
		this.readcount = 0;
		this.ctime = Time.timestamp();
	}

	public User getUser() {
		return this.user;
	}

	public Community getCommunity() {
		return community;
	}

	public String getImage() {
		return image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCommunityid() {
		return communityid;
	}

	public void setCommunityid(String communityid) {
		this.communityid = communityid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMarkdown() {
		return markdown;
	}

	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIlike() {
		return ilike;
	}

	public void setIlike(Integer ilike) {
		this.ilike = ilike;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getCmcount() {
		return cmcount;
	}

	public void setCmcount(Integer cmcount) {
		this.cmcount = cmcount;
	}

	public Integer getSharecount() {
		return sharecount;
	}

	public void setSharecount(Integer sharecount) {
		this.sharecount = sharecount;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getSyscateid() {
		return syscateid;
	}

	public void setSyscateid(String syscateid) {
		this.syscateid = syscateid;
	}

	public String getCmcateid() {
		return cmcateid;
	}

	public void setCmcateid(String cmcateid) {
		this.cmcateid = cmcateid;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
}