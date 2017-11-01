package com.youkke.info.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Filter;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.youkke.info.domain.Info;
import com.youkke.utils.MyPage;

@Component
@Transactional
public class InfoDao extends BaseDao {
	private static final Logger log = LoggerFactory.getLogger(InfoDao.class);
	
	public void refreshInfoCount(String id) {
		try {
			String sql = "update community set infocount=("
					+ "select count(id) as number from info where community=:community" + ") where id=:community";
			Query<?> query = getSession().createNativeQuery(sql).setParameter("community", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("refreshInfoCount_failed", re);
			throw re;
		}
	}

	public void refreshCmcount(String id) {
		try {// and type='comment'
			String sqlcount = "update info set cmcount=("
					+ "select count(id) as number from `infocomment` where info=:info" + ")+1 where id=:info";
			SQLQuery sqlcountquery = getSession().createSQLQuery(sqlcount);
			sqlcountquery.setParameter("info", id);
			sqlcountquery.executeUpdate();
		} catch (RuntimeException re) {
			log.error("refreshCmcount_failed", re);
			throw re;
		}
	}

	public void refreshIlike(String id) {
		try {
			String sqlcount = "update info set ilike=("
					+ "select count(id) as number from `infocomment` where info=:info and ilike>0 and type='like'"
					+ ") where id=:info";
			SQLQuery sqlcountquery = getSession().createSQLQuery(sqlcount);
			sqlcountquery.setParameter("info", id);
			sqlcountquery.executeUpdate();
		} catch (RuntimeException re) {
			log.error("refreshInfoCount_failed", re);
			throw re;
		}
	}

	// 按不同占比分组统计各占比总分
	public void refreshScore(String id) {
		try {
			String sqlcount = "update info set score=(" + "select sum(sum_column) as number from ("
					+ "select avg(score*proportion) as sum_column from `infocomment` where "
					+ "info=:info and score>0 and proportion>0 and type='rating' group by proportion) as t1"
					+ ") where id=:info";
			SQLQuery sqlcountquery = getSession().createSQLQuery(sqlcount);
			sqlcountquery.setParameter("info", id);
			sqlcountquery.executeUpdate();
		} catch (RuntimeException re) {
			log.error("refreshInfoCount_failed", re);
			throw re;
		}
	}

	public Integer plusCmcount(String id) {
		try {
			Info info = findById(id);
			info.setCmcount(info.getCmcount() + 1);
			merge(info);
			return info.getCmcount();
		} catch (RuntimeException re) {
			log.error("plusCmcount", re);
			throw re;
		}
	}

	public Integer plusSharecount(String id) {
		try {
			Info info = findById(id);
			info.setSharecount(info.getSharecount() + 1);
			merge(info);
			return info.getSharecount();
		} catch (RuntimeException re) {
			log.error("plusCmcount", re);
			throw re;
		}
	}

	public Integer plusReadcount(String id) {
		try {
			Info info = findById(id);
			info.setReadcount(info.getReadcount() + 1);
			merge(info);
			return info.getReadcount();
		} catch (RuntimeException re) {
			log.error("plusReadcount", re);
			throw re;
		}
	}
	
	public void delete(String id) {
		try {
			String sqlcount = "delete from info where id=:id";
			SQLQuery sqlcountquery = getSession().createSQLQuery(sqlcount);
			sqlcountquery.setParameter("id", id);
			sqlcountquery.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete", re);
			throw re;
		}
	}

	public void deletes(List<String> ids) {
		try {
			for (String id : ids) {
				delete(id);
			}
		} catch (RuntimeException re) {
			log.error("deletes", re);
			throw re;
		}
	}

	public void save(Info transientInstance) {
		log.debug("saving Info instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public Info findById(java.lang.String id) {
		log.debug("getting Info instance with id: " + id);
		try {
			Info instance = (Info) getSession().get("com.youkke.info.domain.Info", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public MyPage<Info> findByIds(String userid, List<String> ids, int pagesize, int page) {
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Info.class);
			if (StringUtils.isNotBlank(userid)) // TODO 测试完删除
				dc.add(Property.forName("userid").eq(userid));
			if (null != ids && ids.size() > 0)
				dc.add(Property.forName("id").in(ids));
			return findPageByCriteria(dc, pagesize, page);
		} catch (RuntimeException re) {
			log.error("findByIds failed", re);
			throw re;
		}
	}

	public Integer getTotal(String uid, Timestamp starttime, Timestamp endtime) {
		try {
			boolean ok = false;
			DetachedCriteria dc = DetachedCriteria.forClass(Info.class);
			if (StringUtils.isNotBlank(uid)) {
				ok = true;
				dc.add(Property.forName("userid").eq(uid));
			}

			if (null != starttime) {
				ok = true;
				dc.add(Property.forName("ctime").ge(starttime));
			}

			if (null != endtime) {
				ok = true;
				dc.add(Property.forName("ctime").le(endtime));
			}
			if (ok)
				return this.getCountByCriteria(dc);// 避免统计整个表
			else
				return 0;
		} catch (RuntimeException re) {
			log.error("findByIds failed", re);
			throw re;
		}
	}

	public MyPage<Info> findAll(String uid, String communityid, String courseid, String lessonid, String eventid,
			String taskid, Set<String> scopes, Set<String> syscateids, Set<String> cmcateids,
			Set<String> statuses, String keyword, String orderfield, String direction, int pagesize,
			int page) {
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Info.class);

			Session session = getSession();
			Filter filter = session.enableFilter("comments");
			filter.setParameter("embed", "yes");// 默认只加载指定数据

			if (StringUtils.isNotBlank(uid)) {
				dc.add(Property.forName("userid").eq(uid));
			}

			if (StringUtils.isNotBlank(communityid)) {
				dc.add(Property.forName("communityid").in(communityid));
			}
			
			if (StringUtils.isNotBlank(courseid)) {
				dc.add(Property.forName("courseid").in(courseid));
			}
			
			if (StringUtils.isNotBlank(lessonid)) {
				dc.add(Property.forName("lessonid").in(lessonid));
			}
			
			if (StringUtils.isNotBlank(eventid)) {
				dc.add(Property.forName("eventid").in(eventid));
			}
			
			if (StringUtils.isNotBlank(taskid)) {
				dc.add(Property.forName("taskid").in(taskid));
			}

			if (null != scopes && scopes.size() > 0) {
				Disjunction dis = Restrictions.disjunction();
				for (String scope : scopes) {
					dis.add(Property.forName("scope").eq(scope));
				}
				dc.add(dis);
			}

			if (null != syscateids && syscateids.size() > 0) {
				Disjunction dis = Restrictions.disjunction();
				for (String syscate : syscateids) {
					dis.add(Property.forName("syscateid").eq(syscate));
				}
				dc.add(dis);
			}

			if (null != cmcateids && cmcateids.size() > 0) {
				Disjunction dis = Restrictions.disjunction();
				for (String cmcate : cmcateids) {
					dis.add(Property.forName("cmcateid").eq(cmcate));
				}
				dc.add(dis);
			}

			if (null != statuses && statuses.size() > 0) {
				Disjunction dis = Restrictions.disjunction();
				for (String status : statuses) {
					dis.add(Property.forName("status").like(status, MatchMode.ANYWHERE));
				}
				dc.add(dis);
			}

			if (StringUtils.isNotBlank(keyword)) {
				Disjunction dis = Restrictions.disjunction();
				dis.add(Property.forName("title").like(keyword, MatchMode.ANYWHERE));
				dis.add(Property.forName("content").like(keyword, MatchMode.ANYWHERE));
				dc.add(dis);
			}

			if (StringUtils.equals(orderfield, "csort")) {
				if (StringUtils.equals(direction, "desc")) {
					dc.addOrder(Order.desc("csort"));
				} else {
					dc.addOrder(Order.asc("csort"));
				}
			} else if (StringUtils.equals(orderfield, "hot")) {
				if (StringUtils.equals(direction, "desc")) {
					dc.addOrder(Order.desc("readcount"));
					dc.addOrder(Order.desc("cmcount"));
					dc.addOrder(Order.desc("sharecount"));
					dc.addOrder(Order.desc("ilike"));
				} else {
					dc.addOrder(Order.asc("readcount"));
					dc.addOrder(Order.asc("cmcount"));
					dc.addOrder(Order.asc("sharecount"));
					dc.addOrder(Order.asc("ilike"));
				}
			} else {
				if (StringUtils.equals(direction, "asc")) {
					dc.addOrder(Order.asc("ctime"));
				} else {
					dc.addOrder(Order.desc("ctime"));
				}
			}
			dc.addOrder(Order.desc("ctime"));

			try {
				if (pagesize <= 0) {
					pagesize = 20;
				}
			} catch (Exception e) {
			}

			return findPageByCriteria(getSession(), dc, pagesize, page);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Info merge(Info detachedInstance) {
		log.debug("merging Info instance");
		try {
			Info result = (Info) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

}