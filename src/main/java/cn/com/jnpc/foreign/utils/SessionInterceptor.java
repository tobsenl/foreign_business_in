package cn.com.jnpc.foreign.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.service.AttachmentServices;

@Repository
public class SessionInterceptor extends HandlerInterceptorAdapter {
    private static Logger log = Logger.getLogger(AttachmentServices.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
	    HttpServletResponse response, Object handler) throws Exception {
	try {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");

	    User user = null;
	    user = (User) Untils.getSessionP(request, "user");
	    if (user == null) {
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
		builder.append("alert(\"页面过期，请重新登录\");");
		builder.append("window.top.location.href=\"");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
		builder.append(basePath);
		builder.append("login.jsp\";</script>");
		out.print(builder.toString());
		out.close();
		return false;
	    }
	    return super.preHandle(request, response, handler);
	} catch (Exception e) {
	    log.info(e.getMessage());
	    log.info(e.toString());
	    log.info(e.getCause());
	    return false;
	}
    }
}
