package cn.com.jnpc.foreign.vo;

import cn.com.jnpc.foreign.utils.Untils;

public class PageMybatis {
    String pagesize="5";// 每页大小
    String nowpage;// 当前页
    String allcount;// 总条数
    String allpagesize;//总页数
    long nowsize;// 现在数量
    long nextsize;// 下一页数量
    String pageurl;// 页面链接
    String querysql;// 查询的主体sql

    public String getPagesize() {
	return pagesize;
    }

    public void setPagesize(String pagesize) {
	if(Untils.NotNull(allcount)){
	    allpagesize=(Long.parseLong(allcount)/Long.parseLong(pagesize))+"";
	}
	this.pagesize = pagesize;
    }

    public String getNowpage() {
	return nowpage;
    }

    public void setNowpage(String nowpage) {
	this.nowpage = nowpage;
    }

    public String getAllcount() {
	return allcount;
    }

    public void setAllcount(String allcount) {
	if(Untils.NotNull(pagesize)){
	    allpagesize=(Long.parseLong(allcount)/Long.parseLong(pagesize))+"";
	}
	this.allcount = allcount;
    }

    public long getNowsize() {
	if (Untils.NotNull(nowpage) && Untils.NotNull(pagesize)) {
		nowsize = (Integer.parseInt(nowpage) - 1)
			* Integer.parseInt(pagesize);
	    } else {
		nowsize = 0;
	    }
	return nowsize;
    }

    public long getNextsize() {
	if (Untils.NotNull(nowpage) && Untils.NotNull(pagesize)) {
		nextsize = (Integer.parseInt(nowpage) + 1)
			* Integer.parseInt(pagesize) + 1;
	    } else {
		nextsize = 0;
	}
	return nextsize;
    }

    public String getPageurl() {
	return pageurl;
    }

    public void setPageurl(String pageurl) {
	this.pageurl = pageurl;
    }

    public String getQuerysql() {
	return querysql;
    }

    public void setQuerysql(String querysql) {
	this.querysql = querysql;
    }

    public String getAllpagesize() {
        return allpagesize;
    }

    public void setAllpagesize(String allpagesize) {
        this.allpagesize = allpagesize;
    }
}
