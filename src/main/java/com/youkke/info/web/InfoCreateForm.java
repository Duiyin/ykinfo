package com.youkke.info.web;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.youkke.info.domain.Config;

public class InfoCreateForm {

	private String communityid;
	private String cmcateid;
	private String eventid;
	private String taskid;

	private String scope;
	private String friend;
	private String title;
	private String image;
	private String link;

	@NotNull(message = "info.markdown.notnull")
	@Size(min = 3, message = "info.markdown.too_little")
	private String markdown;

	@NotNull(message = "info.content.notnull")
	@Size(min = 3, message = "info.content.too_little")
	private String content;

	// TODO https://github.com/jirutka/validator-collection 增加集合较验
	private List<Config> configs;

	private Double latitude;
	private Double longitude;

	public InfoCreateForm() {
	}

	public String getCommunityid() {
		return communityid;
	}

	public void setCommunityid(String communityid) {
		this.communityid = communityid;
	}

	public String getCmcateid() {
		return cmcateid;
	}

	public void setCmcateid(String cmcateid) {
		this.cmcateid = cmcateid;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public List<Config> getConfigs() {
		return configs;
	}

	public void setConfigs(List<Config> configs) {
		this.configs = configs;
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

}