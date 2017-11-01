package com.youkke.info.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Event implements Serializable {
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
	
	private String edistrict;
	private String eaddress;
	private Double elatitude;
	private Double elongitude;
	private Timestamp starttime;
	private Timestamp endtime;

	public Event() {
		this.ilike = 0;
		this.usercount = 0;
		this.infocount = 0;
		this.eventcount = 0;
		this.photocount = 0;
		this.videocount = 0;
	}

	public Event(String id, String scope, String status) {
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

	public String getEdistrict() {
		return edistrict;
	}

	public void setEdistrict(String edistrict) {
		this.edistrict = edistrict;
	}

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}

	public Double getElatitude() {
		return elatitude;
	}

	public void setElatitude(Double elatitude) {
		this.elatitude = elatitude;
	}

	public Double getElongitude() {
		return elongitude;
	}

	public void setElongitude(Double elongitude) {
		this.elongitude = elongitude;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
}
