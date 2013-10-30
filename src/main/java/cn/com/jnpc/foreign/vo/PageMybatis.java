package cn.com.jnpc.foreign.vo;

import cn.com.jnpc.foreign.utils.Untils;

public class PageMybatis {
    long pagesize=5;// 每页大小
    long nowpage;// 当前页
    long allcount;// 总条数
    long allpagesize;//总页数
    long nowsize;// 现在数量
    long nextsize;// 下一页数量
    String pageurl;// 页面链接
    String querysql;// 查询的主体sql

    public Long getPagesize() {
	return pagesize;
    }

    public void setPagesize(Long pagesize) {
	if(Untils.NotNull(allcount+"")){
	    Double value=Math.ceil((double)allcount/(double)pagesize);
	    allpagesize=value.longValue();
	}
	this.pagesize = pagesize;
    }

    public Long getNowpage() {
	return nowpage;
    }

    public void setNowpage(Long nowpage) {
	this.nowpage = nowpage;
    }

    public Long getAllcount() {
	return allcount;
    }

    public void setAllcount(Long allcount) {
	if(Untils.NotNull(pagesize+"")){
	    Double value=Math.ceil((double)allcount/(double)pagesize);
	    allpagesize=value.longValue();
	}
	this.allcount = allcount;
    }

    public long getNowsize() {
	if (Untils.NotNull(nowpage+"") && Untils.NotNull(pagesize+"")) {
		nowsize = (nowpage - 1)
			* pagesize;
	    } else {
		nowsize = 0;
	    }
	return nowsize;
    }

    public long getNextsize() {
	if (Untils.NotNull(nowpage+"") && Untils.NotNull(pagesize+"")) {
		nextsize = nowpage
			* pagesize + 1;
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

    public Long getAllpagesize() {
        return allpagesize;
    }

    public void setAllpagesize(Long allpagesize) {
        this.allpagesize = allpagesize;
    }
}
