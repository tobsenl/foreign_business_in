package cn.com.jnpc.foreign.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

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

import org.springframework.web.multipart.MultipartFile;

import cn.com.jnpc.foreign.controller.invitationController;
import cn.com.jnpc.foreign.po.FiAttachment;
import cn.com.jnpc.foreign.po.FiBlob;

public final class Untils {
    public final static int BY_PROPERTIES = 1;
    public final static int BY_RESOURCEBUNDLE = 2;
    public final static int BY_PROPERTYRESOURCEBUNDLE = 3;
    public final static int BY_CLASS = 4;
    public final static int BY_CLASSLOADER = 5;
    public final static int BY_SYSTEM_CLASSLOADER = 6;
    public final static int BY_WEBINF = 7;
    public static Properties prop;
    protected static Map<String, String> parameters = new HashMap<String, String>();

    @SuppressWarnings("unchecked")
    public static boolean verifyUser(String userId, String password)
	    throws IOException {
	prop = loadProperties("/ad_load.properties", 7);
	boolean flag = true;
	/*
	 * String userName = "20021274"; // 用户名称 password = "123456"; // 密码
	 */
	String host = prop.getProperty("host"); // AD服务器
	String port = prop.getProperty("port"); // 端口
	String domain = prop.getProperty("domain"); // 邮箱的后缀名
	String url = new String("ldap://" + host + ":" + port);
	String user = userId.indexOf(domain) > 0 ? userId : userId + domain;
	Hashtable env = new Hashtable();
	DirContext ctx;
	env.put(Context.SECURITY_AUTHENTICATION, "simple");
	env.put(Context.SECURITY_PRINCIPAL, user); // 不带邮箱后缀名的话，会报错，具体原因还未探究。高手可以解释分享。
	env.put(Context.SECURITY_CREDENTIALS, password);
	env.put(Context.INITIAL_CONTEXT_FACTORY, prop.getProperty("Factory"));
	env.put(Context.PROVIDER_URL, url);
	try {
	    ctx = new InitialDirContext(env);
	    // 域节点
	    String searchBase = prop.getProperty("searchBase");// OU=USERS,OU=JNPC,DC=jnpc,DC=net
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
    public static boolean moveOutSessionP(HttpServletRequest req, String value) {
	HttpSession session = req.getSession();
	if (getSessionP(req, value) != null) {
	    session.removeAttribute(value);
	    return true;
	} else {
	    session.setAttribute(value, null);
	    return true;
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
	} else if (type == BY_WEBINF) {
	    in = new BufferedInputStream(new FileInputStream(
		    GetPath("JavaBean").replace("/classes", "") + name));
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
	    classespath = Untils.class.getClassLoader().getResource("/")
		    .getPath();
	    classespath = classespath.substring(0, classespath.length() - 1);
	    realpath = classespath.substring(0, classespath.lastIndexOf("/"));
	}
	return realpath;
    }

    public final static String requestPath(HttpServletRequest request) {
//	String path = request.getQueryString();
	// String path = request.getContextPath();
	// String basePath = request.getScheme() + "://" +
	// request.getServerName()
	// + ":" + request.getServerPort() + path + "/";
	String a = request.getRequestURL().toString()+"?";
	Map map=request.getParameterMap();
	StringBuffer bf=new StringBuffer();
	Set keysSet=map.keySet();
	Iterator iterator = keysSet.iterator();
	while (iterator.hasNext()) {
            String key=iterator.next().toString();
            String[] str = (String[])map.get(key);
            bf.append("&"+ key + "=" + str[0]);
        }
        if(bf.length()>0){
            return a+(bf.toString().substring(1));
        }else{
            return a;
        }
    }

    public final static String getUrl(MultipartFile blob_v) {
	String url = null;
	String path = Untils.GetPath("JavaBean");
	try {
		prop = loadProperties("/ad_load.properties", 7);
		if (prop != null) {
			String filename = prop.getProperty("picfile");
			String store_path = path.replace("/WEB-INF", "/" + filename + "/")
					+ DateUtil.getCurrentDate("yyyy-MM-dd");
			url = storePic(store_path, blob_v);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return url;
    }
    
    public final static String getSpitpath(String bpath){
	String url = null;
	String path = Untils.GetPath("JavaBean");
	try {
		prop = loadProperties("/ad_load.properties", 7);
		if (prop != null) {
			String filename = prop.getProperty("picfile");
			String [] value=bpath.split(filename);
			if(value.length == 2){
				url=filename+value[1];
			}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return url;
    }
    
    public final static String getUrl(FiBlob blob,String oldpath,String filename) {
	String url = null;
	String store_path =null;
	String syspath =getfilepath();
	String endsuffix=filename.substring(
			filename.lastIndexOf("."));
	String newFileName = UUID.randomUUID() + endsuffix;
	if(Untils.NotNull(oldpath)){
	    store_path=oldpath;
	}else{
		store_path=syspath + File.separator + newFileName;
	}
	byte[] content = blob.getFileV();
	if (content.length > 0) {
	    url = storePic(store_path, content,syspath);
	    return url;
	} else {
	    return null;
	}
    }
    public final static String getfilepath() {
    	String path = Untils.GetPath("JavaBean");
    	String syspath = path.replace("/WEB-INF", "/temp_store/")
    			+ DateUtil.getCurrentDate("yyyy-MM-dd");
    	return syspath;
    }
    public final static List<String> getlistBystr(String str) {
	List<String> list=null;
	str=str.trim();
	if(NotNull(str)){
	    list=new ArrayList<String>();
	    String [] strlist =str.split(",");
	    for (int i = 0; i < strlist.length; i++) {
		if(Untils.NotNull(strlist[i])){
		    list.add(strlist[i]);
		}else{
		    continue;
		} 
	    }
	}
	return list;
    }

    public final static String storePic(String path, MultipartFile blob) {
	try {
	    path=path.replaceAll("\\\\", "/");
	    String syspath=getfilepath();
	    String url = checkDisk(path,syspath);
	    String endsuffix = blob.getOriginalFilename().substring(
		    blob.getOriginalFilename().lastIndexOf("."));
	    String newFileName = UUID.randomUUID() + endsuffix;
	    url = url + File.separator + newFileName;
	    File file = new File(url.replaceAll("\\\\", "/"));
	    file.mkdirs();
	    blob.transferTo(file);
	    return url;
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
    }

    public final static String storePic(String path, byte[] content,String syspath) {
	try {
	    path=path.replaceAll("\\\\", "/");
	    String url = checkDisk(path,syspath);
	    File file =null;
	    url=url+path.substring(path.lastIndexOf("/"),path.toCharArray().length);
	    if(Untils.NotNull(url)){
		file = new File(url.replaceAll("\\\\", "/"));		
	    }else{
		return null;	
	    }
	    OutputStream outstream = new BufferedOutputStream(
		    new FileOutputStream(file));
	    outstream.write(content, 0, content.length);
	    outstream.close();
	    return file.toString();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
    }

    public final static String checkDisk(String url,String syspath) {
	if (NotNull(url)) {
	    url=url.replaceAll("\\\\", "/");
	    File file = new File(url);
	    if (file.exists()) {
		return file.toString();
	    } else {
		if (url.substring(url.lastIndexOf(".")).toCharArray().length <= 4) {
		    file = new File(syspath);
		    file.mkdirs();
		    return file.toString();
		} else {
		    file.mkdirs();
		    return file.toString();
		}
	    }
	} else {
	    return null;
	}
    }

    public final static String getWorkPath(HttpServletRequest request,
	    String url) {
	url=url.replaceAll("\\\\", "/");
	String basePath = request.getScheme()
		+ "://"
		+ request.getServerName()
		+ ":"
		+ request.getServerPort()
		+ "/"
		+ request.getContextPath();
	return basePath + "/" + url;
    }
}
