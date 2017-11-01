package com.youkke.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPage<T> implements Serializable {
	private static final long serialVersionUID = -7538513433895399481L;
	public final static int PAGESIZE = 20;
	private int pagesize = PAGESIZE;
	private List<T> items;
	private int totalcount;
	private int[] indexes = new int[0];
	private int startindex = 0;
	private int currentpage = 0;
	private int pageNumber;
	private int[] pages = new int[0];

	private int prepage;
	private int nextpage;

	public MyPage() {
		items = new ArrayList<T>(0);
	}

	public MyPage(List<T> items, int totalcount) {
		setpagesize(pagesize);
		setTotalcount(totalcount);
		setItems(items);
		setstartindex(0);
	}

	public MyPage(List<T> items, int totalcount, int page) {
		setpagesize(pagesize);
		setTotalcount(totalcount);
		setItems(items);
		setstartindex(startindex);
	}

	public MyPage(List<T> items, int totalcount, int pagesize, int page) {
		setpagesize(pagesize);
		setTotalcount(totalcount);
		setItems(items);
		setstartindex(page);
	}

	public Map<Integer, List<T>> split(int num) {
		Map<Integer, List<T>> map = new HashMap<Integer, List<T>>();
		if (num < 1) {
			num = 1;
		}

		int column = (int) Math.ceil(Arith.div(totalcount, num));// 总数与列数相除，得每列数量
		if (column <= num) {
			num = column;// 不产生多余的集合
		}

		if (null != items && items.size() > 0) {
			for (int i = 1; i <= num; i++) {
				List<T> sublist = new ArrayList<T>();
				map.put(i, sublist);
			}

			int i = 1;
			for (T object : items) {
				List<T> temp = map.get(i);
				temp.add(object);
				if (i >= num) {
					i = 1;
				} else {
					i++;
				}
			}
		}
		return map;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getpagesize() {
		return pagesize;
	}

	public void setpagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		if (totalcount > 0) {
			this.totalcount = totalcount;
			int count = totalcount / pagesize;
			if (totalcount % pagesize > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = pagesize * i;
			}
		} else {
			this.totalcount = 0;
		}
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int getstartindex() {
		return startindex;
	}

	public void setstartindex(int startindex) {
		this.pageNumber = (startindex / pagesize) + 1;
		if (totalcount <= 0)
			this.startindex = 0;
		else if (startindex >= totalcount)
			this.startindex = indexes[indexes.length - 1];
		else if (startindex < 0)
			this.startindex = 0;
		else {
			this.startindex = indexes[startindex / pagesize];
			this.currentpage = startindex / pagesize + 1;
		}
	}

	public int getNextIndex() {
		int nextIndex = getstartindex() + pagesize;
		if (nextIndex >= totalcount)
			return getstartindex();
		else
			return nextIndex;
	}

	public int getPreviousIndex() {
		int previousIndex = getstartindex() - pagesize;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}

	public int getPagecount() {
		return indexes.length;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {// 暂时无用处
		this.pageNumber = pageNumber;
	}

	public int getPrepage() {
		prepage = getPreviousPage();
		return prepage;
	}

	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}

	public int getNextpage() {
		nextpage = getNextPage();
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public int getNextPage() {
		int nextPage = pageNumber + 1;
		if (nextPage > getPagecount())
			nextPage = getPagecount();
		return nextPage;
	}

	public int getPreviousPage() {
		int prePage = pageNumber - 1;
		if (prePage < 1)
			prePage = 1;
		return prePage;
	}

	public int getNextTenPage() {
		int nextPage = pageNumber + 10;
		if (nextPage > getPagecount())
			nextPage = getPagecount();
		return nextPage;
	}

	public int getPreviousTenPage() {
		int prePage = pageNumber - 10;
		if (prePage < 1)
			prePage = 1;
		return prePage;
	}

	public int[] getPages() {// 显示五个页码
		int first = 1;
		int end = pageNumber + 2;
		if (pageNumber - 2 > 0) {
			first = pageNumber - 2;
		}
		if (end > getPagecount()) {
			end = getPagecount();
		}
		if ((end - first) < 4 && (first + 4) <= getPagecount()) {// 后不足五个页码的补齐
			end = first + 4;
		}
		if ((end - first) < 4 && (end - 4) >= 1) {// 前不足五个页码的补齐
			first = end - 4;
		}

		int fornum = end - first + 1;
		pages = new int[fornum];// 容器
		for (int i = 0; i < fornum; i++) {
			pages[i] = first;// 存入页码
			first++;
		}

		return pages;
	}
}
