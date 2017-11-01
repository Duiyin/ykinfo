package com.youkke.info.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.youkke.info.client.UserClient;
import com.youkke.info.dao.CommentDao;
import com.youkke.info.dao.InfoDao;
import com.youkke.info.domain.Comment;
import com.youkke.info.domain.Info;
import com.youkke.info.utils.Constant;
import com.youkke.info.web.InfoCreateForm;
import com.youkke.info.web.InfoUpdateForm;
import com.youkke.utils.MyPage;
import com.youkke.utils.ServiceException;

@Component
@Transactional
public class InfoService extends BaseServices {
	@Autowired
	private InfoDao infoDao;
	@Autowired
	private CommentDao commentDao;
	/*@Autowired
	private UserClient userClient;*/

	/**
	 * 查询信息的评论
	 * @param infoid
	 * @param keyword
	 * @param pagesize
	 * @param page
	 * @return
	 */
	public MyPage<Comment> findCommentById(String infoid, String keyword, int pagesize, int page) {
		MyPage<Comment> ps = commentDao.findByInfo(infoid, keyword, pagesize, page);
		Iterator<Comment> it = ps.getItems().iterator();
		while (it.hasNext()) {
			Comment comment = it.next();
			//if (StringUtils.isNotBlank(comment.getUserid()))
				//comment.setUser(userClient.findById(comment.getUserid()));
		}

		return ps;
	}

	/**
	 * 发表评论
	 * @param sessuserid
	 * @param id
	 * @param content
	 * @return
	 */
	public Integer saveComment(String sessuserid, String id, String content) {
		try {
			Info info = infoDao.findById(id);
			if (null != info) {
			} else {
				throw new ServiceException("info.notexist", "comment");
			}

			if (info.checkSetup(Constant.COMMENT_DENY)) {
				throw new ServiceException(Constant.COMMENT_DENY, "comment");
			}

			Comment comment = new Comment(sessuserid, info, content);
			commentDao.save(comment);
			commentDao.setEmbed(id, "no");
			commentDao.setEmbed(id, 3, "yes");
			return info.getCmcount();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 删除
	 * 
	 * @param sessuserid
	 * @param id
	 */
	public void delete(String sessuserid, String id) {
		try {
			Info info = infoDao.findById(id);
			if (!StringUtils.equals(info.getUserid(), sessuserid))
				throw new ServiceException("user.permission.deny", "info");
			infoDao.delete(id);
			commentDao.deleteByInfo(id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 创建
	 * 
	 * @param sessuserid
	 * @param infoCreate
	 */
	public void save(String sessuserid, InfoCreateForm infoCreate) {
		try {
			if (!StringUtils.isNotBlank(sessuserid))
				throw new ServiceException("user.require.login", "info");
			Info info = new Info(sessuserid);
			BeanUtils.copyProperties(infoCreate, info, Info.class);
			infoDao.save(info);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 修改
	 * 
	 * @param sessuserid
	 * @param id
	 * @param infoUpdate
	 */
	public void update(String sessuserid, String id, InfoUpdateForm infoUpdate) {
		try {
			Info info = infoDao.findById(id);
			if (!StringUtils.equals(info.getUserid(), sessuserid))
				throw new ServiceException("user.permission.deny", "info");
			if (!StringUtils.isNotBlank(id))
				throw new ServiceException("info.id.isnull", "info");
			BeanUtils.copyProperties(infoUpdate, info, Info.class);
			infoDao.merge(info);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 查询信息
	 * 
	 * @param sessuserid
	 * @param id
	 * @return
	 */
	public Info findById(String sessuserid, String id) {
		try {
			Info info = infoDao.findById(id);
			if (StringUtils.equals(sessuserid, info.getUserid())) {
				return info;
			} else if (StringUtils.equals(info.getScope(), Constant.PRIVATE)) {
				throw new ServiceException("user.permission.deny", "info");
			}
			// TODO 分享给好友的情况
			//info.setUser(userClient.findById(info.getUserid()));
			infoDao.plusReadcount(id);

			return info;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 查询所有状态正常和推荐到首页的信息
	 * 
	 * @param mid
	 * @param communityid
	 * @param courseid
	 * @param lessonid
	 * @param eventid
	 * @param taskid
	 * @param syscateids
	 * @param cmcateids
	 * @param tags
	 * @param keyword
	 * @param orderfield
	 * @param direction
	 * @param pagesize
	 * @param page
	 * @return
	 */
	public MyPage<Info> findAll(String mid, String communityid, String courseid, String lessonid, String eventid,
			String taskid, Set<String> syscateids, Set<String> cmcateids, Set<String> tags, String keyword,
			String orderfield, String direction, int pagesize, int page) {
		try {
			Set<String> statuses = new HashSet<String>(Arrays.asList(new String[] { "home", "normal" }));
			Set<String> scopes = new HashSet<String>(Arrays.asList(new String[] { "public" }));
			MyPage<Info> ps = infoDao.findAll(null, communityid, courseid, lessonid, eventid, taskid, scopes,
					syscateids, cmcateids, statuses, keyword, orderfield, direction, pagesize, page);
			Iterator it = ps.getItems().iterator();
			while (it.hasNext()) {
				Info info = (Info) it.next();
				//if (StringUtils.isNotBlank(info.getUserid()))
					//info.setUser(userClient.findById(info.getUserid()));
			}
			return ps;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 仅查询标记为推荐到首页的信息
	 * 
	 * @param mid
	 * @param syscateids
	 * @param cmcateids
	 * @param keyword
	 * @param orderfield
	 * @param direction
	 * @param pagesize
	 * @param page
	 * @return
	 */
	public MyPage<Info> findHomeAll(String mid, Set<String> syscateids, Set<String> cmcateids, String keyword,
			String orderfield, String direction, int pagesize, int page) {
		try {
			Set<String> statuses = new HashSet<String>(Arrays.asList(new String[] { "home" }));
			Set<String> scopes = new HashSet<String>(Arrays.asList(new String[] { "public" }));
			MyPage<Info> ps = infoDao.findAll(null, null, null, null, null, null, scopes, syscateids, cmcateids,
					statuses, keyword, orderfield, direction, pagesize, page);
			Iterator it = ps.getItems().iterator();
			while (it.hasNext()) {
				Info info = (Info) it.next();
				//if (StringUtils.isNotBlank(info.getUserid()))
					//info.setUser(userClient.findById(info.getUserid()));
			}
			return ps;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 查询用户发布的信息，或查询自己发布的信息
	 * 
	 * @param mid
	 * @param userid
	 * @param syscateids
	 * @param cmcateids
	 * @param tags
	 * @param keyword
	 * @param orderfield
	 * @param direction
	 * @param pagesize
	 * @param page
	 * @return
	 */
	public MyPage<Info> findByUser(String mid, String userid, Set<String> syscateids, Set<String> cmcateids,
			Set<String> tags, String keyword, String orderfield, String direction, int pagesize, int page) {
		try {
			if (StringUtils.isNotBlank(userid)) {
				Set<String> statuses = new HashSet<String>(Arrays.asList(new String[] { "home" }));
				Set<String> scopes = new HashSet<String>(Arrays.asList(new String[] { "public" }));
				if (StringUtils.isNotBlank(mid)) {
					statuses = new HashSet<String>(Arrays.asList(new String[] { "home", "normal" }));
				}
				// 已登录，且与查询的用户相同，则为查自己的信息
				if (StringUtils.equals(mid, userid)) {
					scopes = null;
				}

				MyPage<Info> ps = infoDao.findAll(userid, null, null, null, null, null, scopes, syscateids, cmcateids,
						statuses, keyword, orderfield, direction, pagesize, page);
				Iterator it = ps.getItems().iterator();
				while (it.hasNext()) {
					Info info = (Info) it.next();
					//if (StringUtils.isNotBlank(info.getUserid()))
						//info.setUser(userClient.findById(info.getUserid()));
				}

				return ps;
			} else {
				return new MyPage<Info>(new ArrayList<Info>(), 0);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
