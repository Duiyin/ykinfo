package com.youkke.info.domain;

import java.io.Serializable;

public class Course implements Serializable {
	private static final long serialVersionUID = 1606643000507513023L;

	private String id;
	private String scope;
	private String name;
	private String image;
	private String banner;
	private String content;
	private String status;
	private Integer ilike;
	private Integer usercount;
	private Integer infocount;
	private Integer eventcount;
	private Integer photocount;
	private Integer videocount;

	public Course() {
		this.ilike = 0;
		this.usercount = 0;
		this.infocount = 0;
		this.eventcount = 0;
		this.photocount = 0;
		this.videocount = 0;
	}

	public Course(String id, String scope, String status) {
		this.id = id;
		this.scope = scope;
		this.status = status;
		this.ilike = 0;
		this.usercount = 0;
		this.infocount = 0;
		this.eventcount = 0;
		this.photocount = 0;
		this.videocount = 0;
	}

	public Course(String id, String scope, String status, Integer ilike,
			Integer usercount, Integer infocount, Integer eventcount, Integer photocount,
			Integer videocount) {
		this.id = id;
		this.scope = scope;
		this.status = status;
		this.ilike = ilike;
		this.usercount = usercount;
		this.infocount = infocount;
		this.eventcount = eventcount;
		this.photocount = photocount;
		this.videocount = videocount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
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

	public Integer getUsercount() {
		return usercount;
	}

	public void setUsercount(Integer usercount) {
		this.usercount = usercount;
	}

	public Integer getInfocount() {
		return infocount;
	}

	public void setInfocount(Integer infocount) {
		this.infocount = infocount;
	}

	public Integer getEventcount() {
		return eventcount;
	}

	public void setEventcount(Integer eventcount) {
		this.eventcount = eventcount;
	}

	public Integer getPhotocount() {
		return photocount;
	}

	public void setPhotocount(Integer photocount) {
		this.photocount = photocount;
	}

	public Integer getVideocount() {
		return videocount;
	}

	public void setVideocount(Integer videocount) {
		this.videocount = videocount;
	}
}
