package com.youkke.info.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.alibaba.fastjson.annotation.JSONField;
import com.youkke.info.utils.Constant;
import com.youkke.utils.ID;
import com.youkke.utils.Time;

@Entity
@Table(name = "comment")
public class Comment implements java.io.Serializable {
	private static final long serialVersionUID = -233199321703147471L;

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "info")
	@NotFound(action = NotFoundAction.IGNORE)
	@JSONField(serialize = false)
	private Info info;

	@Column(name = "userid", length = 32)
	private String userid;
	@Transient
	private User user;

	@NotNull(message = "comment.content.notnull")
	@Size(min = 3, message = "comment.content.too_little")
	private String content;
	private String status;
	private Timestamp ctime;
	private String embed;// yes,只有6条通过过滤器关联查询出来

	public Comment() {
	}

	public Comment(String userid, Info info, String content) {
		this.id = ID.uuid();
		this.userid = userid;
		this.info = info;
		this.content = content;
		this.status = Constant.NORMAL;
		this.ctime = Time.timestamp();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Info getInfo() {
		return this.info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

}