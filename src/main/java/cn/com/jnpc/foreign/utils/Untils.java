package cn.com.jnpc.foreign.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.com.jnpc.foreign.controller.invitationController;

public final class Untils {
    public final static int BY_PROPERTIES = 1;
    public final static int BY_RESOURCEBUNDLE = 2;
    public final static int BY_PROPERTYRESOURCEBUNDLE = 3;
    public final static int BY_CLASS = 4;
    public final static int BY_CLASSLOADER = 5;
    public final static int BY_SYSTEM_CLASSLOADER = 6;

    protected static Map<String, String> parameters = new HashMap<String, String>();

    @SuppressWarnings("unchecked")
    public static boolean verifyUser(String userId, String password) {
	boolean flag = true;
	/*
	 * String userName = "20021274"; // 用户名称 password = "123456"; // 密码
	 */
	String host = "10.10.14.46"; // AD服务器
	String port = "389"; // 端口
	String domain = "@ecmuat.jnpc.com"; // 邮箱的后缀名
	String url = new String("ldap://" + host + ":" + port);
	String user = userId.indexOf(domain) > 0 ? userId : userId + domain;
	Hashtable env = new Hashtable();
	DirContext ctx;
	env.put(Context.SECURITY_AUTHENTICATION, "simple");
	env.put(Context.SECURITY_PRINCIPAL, user); // 不带邮箱后缀名的话，会报错，具体原因还未探究。高手可以解释分享。
	env.put(Context.SECURITY_CREDENTIALS, password);
	env.put(Context.INITIAL_CONTEXT_FACTORY,
		"com.sun.jndi.ldap.LdapCtxFactory");
	env.put(Context.PROVIDER_URL, url);
	try {
	    ctx = new InitialDirContext(env);
	    // 域节点
	    String searchBase = "OU=USERS,OU=JNPC,DC=ecmuat,DC=jnpc,DC=com";// OU=USERS,OU=JNPC,DC=jnpc,DC=net
	    // LDAP搜索过滤器类
	    String searchFilter = "(&(objectClass=User)(sAMAccountName="
		    + userId + "))";
	    // 搜索控制器
	    SearchControls searchCtls = new SearchControls(); // Create the
	    // 创建搜索控制器
	    searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Specify
	    // 设置搜索范围
	    String returnedAtts[] = { "CN", "MAIL" };// 定制返回属性
	    searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
	    // 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果
	    NamingEnumeration answer = ctx.search(searchBase, searchFilter,
		    searchCtls);
	    // 初始化搜索结果数为0
	    while (answer.hasMoreElements()) {
		// 遍历结果集
		SearchResult sr = (SearchResult) answer.next();// 得到符合搜索条件的DN
		Attributes Attrs = sr.getAttributes();// 得到符合条件的属性集
		System.out.println(Attrs.get("mail").get());
		System.out.println(Attrs.get("CN").get());
		System.out.println("验证成功！");
	    }
	    ctx.close();

	} catch (NamingException err) {
	    flag = false;
	    err.printStackTrace();
	    System.out.println("验证失败！");
	}
	return flag;
    }

    public static Object getSessionP(HttpServletRequest req, String value) {
	HttpSession session = req.getSession();
	Object obj = session.getAttribute(value);
	return obj;
    }

    public static boolean setSessionP(HttpServletRequest req, String value,
	    Object obj) {
	HttpSession session = req.getSession();
	session.setAttribute(value, obj);
	if (getSessionP(req, value) != null) {
	    return true;
	} else {
	    return false;
	}
    }

    public static boolean NotNull(Object obj) {
	if (obj != null && !"".equals(obj.toString().trim())) {
	    return true;
	} else {
	    return false;
	}
    }

    public final static Properties loadProperties(final String name,
	    final int type) throws IOException {
	Properties p = new Properties();
	InputStream in = null;
	if (type == BY_PROPERTIES) {
	    in = new BufferedInputStream(new FileInputStream(name));
	    assert (in != null);
	    p.load(in);
	} else if (type == BY_RESOURCEBUNDLE) {
	    ResourceBundle rb = ResourceBundle.getBundle(name,
		    Locale.getDefault());
	    assert (rb != null);
	    p = new ResourceBundleAdapter(rb);
	} else if (type == BY_PROPERTYRESOURCEBUNDLE) {
	    in = new BufferedInputStream(new FileInputStream(name));
	    assert (in != null);
	    ResourceBundle rb = new PropertyResourceBundle(in);
	    p = new ResourceBundleAdapter(rb);
	} else if (type == BY_CLASS) {
	    assert (Untils.class.equals(new Untils().getClass()));
	    in = Untils.class.getResourceAsStream(name);
	    assert (in != null);
	    p.load(in);
	    // return new JProperties().getClass().getResourceAsStream(name);
	} else if (type == BY_CLASSLOADER) {
	    assert (Untils.class.getClassLoader().equals(new Untils()
		    .getClass().getClassLoader()));
	    in = Untils.class.getClassLoader().getResourceAsStream(name);
	    assert (in != null);
	    p.load(in);
	    // return new
	    // JProperties().getClass().getClassLoader().getResourceAsStream(name);
	} else if (type == BY_SYSTEM_CLASSLOADER) {
	    in = ClassLoader.getSystemResourceAsStream(name);
	    assert (in != null);
	    p.load(in);
	}

	if (in != null) {
	    in.close();
	}
	return p;
    }

    public final static String GetPath(String byclass) {
	String classespath = "";
	String realpath = "";
	if (byclass == "JavaBean") {
	    classespath = invitationController.class.getClassLoader()
		    .getResource("/").getPath();
	    classespath = classespath.substring(0, classespath.length() - 1);
	    realpath = classespath.substring(0, classespath.lastIndexOf("/"));
	}
	return realpath;
    }

    public final static String requestPath(HttpServletRequest request) {
//	String path = request.getContextPath();
//	String basePath = request.getScheme() + "://" + request.getServerName()
//		+ ":" + request.getServerPort() + path + "/";
	String a= request.getRequestURL().toString()+"?"+(NotNull(request.getQueryString())?request.getQueryString():"");
	//System.out.println(a);
	return a;
    }
}
