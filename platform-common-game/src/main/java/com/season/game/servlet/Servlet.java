package com.season.game.servlet;


import io.netty.util.Attribute;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.net.JarURLConnection;


import com.season.game.servlet.annotation.PassParameter;
import com.season.game.servlet.annotation.SessionParameter;
import com.season.game.session.SessionConstants;
import com.season.game.session.Users;
import com.season.game.util.ClassUtil;

public class Servlet {
	
	// objectFactory
	
	private static Map<String, ActionInvocation> actionMap = new HashMap<String, ActionInvocation>();
	
	// 
	
	public void init(){
		/*Set<Class<?>> classes = getActionClasses("server.login");
		// ��ν��ޱ�ǵ�Action���뵽beanFactory��
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		for (Class<?> clazz : classes){
			// ͨ��spring factory���object
			Object object = beanFactory.getBean(clazz);
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods){
				ActionInvocation actionInvocation = new ActionInvocation(object, method);
				actionMap.put(actionInvocation.getActionName(), actionInvocation);
			}
		}*/
		
	}
	
	public Response service(Request request){
		ActionInvocation invocation = actionMap.get(request.getCommand());
		Object object = invocation.getObject();
		Method method = invocation.getMethod();
		List<Object> params = new LinkedList<Object>();
		for (int i = 0; i <= method.getParameterTypes().length - 1; i++){
			Class<?> paramType = method.getParameterTypes()[i];
			if (paramType.equals(Request.class))
				params.add(request);
			else{
				Annotation annotation = method.getParameterAnnotations()[i][0];
				if (annotation instanceof PassParameter){
				    String paramName = ((PassParameter)annotation).name();
				    //Class<?> paramtype = ((PassParameter)annotation).type();
				    String paramValue = request.getParamMap().get(paramName);
				    params.add(ClassUtil.stringToObject(paramValue, paramType));
				}
				else if (annotation instanceof SessionParameter){
					String paramName = ((SessionParameter)annotation).name();
					if (paramName.equals("userId")){
						Attribute<String> attr = request.getCtx().attr(SessionConstants.SESSION_ID);
						String sessionId = attr.get();
						Integer userId = Users.getUserId(sessionId);
						params.add(userId);
					}
					else{
						params.add(new Object());
					}
				}
				else{
					params.add(new Object());
				}
			}
			
		}
		
		try {
			byte[] ret = (byte[])method.invoke(object, params.toArray());
			System.out.println("find method: " + method.getName());
			return new Response(request.getId(), ret);
		} catch (Exception e){
			e.printStackTrace();
			return new Response(request.getId(), null);
		}
	}
	
	public void destroy(){

	}

	
	/** 
     * �Ӱ�package�л�ȡ���е�Action Class 
     *  
     * @param pack 
     * @return 
     */  
    public static Set<Class<?>> getActionClasses(String pack) {  
  
        // ��һ��class��ļ���  
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();  
        // �Ƿ�ѭ������  
        boolean recursive = true;  
        // ��ȡ�������� �������滻  
        String packageName = pack;  
        String packageDirName = packageName.replace('.', '/');  
        // ����һ��ö�ٵļ��� ������ѭ�����������Ŀ¼�µ�things  
        Enumeration<URL> dirs;  
        try {  
        	// �õ���ǰ�̵߳�classLoader
            dirs = Thread.currentThread().getContextClassLoader().getResources(  
                    packageDirName);  
            // ѭ��������ȥ  
            while (dirs.hasMoreElements()) {  
                // ��ȡ��һ��Ԫ��  
                URL url = dirs.nextElement();  
                // �õ�Э�������  
                String protocol = url.getProtocol();  
                // ��������ļ�����ʽ�����ڷ�������  
                if ("file".equals(protocol)) {  
                    //System.err.println("file���͵�ɨ��");  
                    // ��ȡ��������·��  
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");  
                    // ���ļ��ķ�ʽɨ���������µ��ļ� ����ӵ�������  
                    findAndAddClassesInPackageByFile(packageName, filePath,  
                            recursive, classes);  
                } else if ("jar".equals(protocol)) {  
                    // �����jar���ļ�  
                    // ����һ��JarFile  
                    //System.err.println("jar���͵�ɨ��");  
                    JarFile jar;  
                    try {  
                        // ��ȡjar  
                        jar = ((JarURLConnection) url.openConnection())  
                                .getJarFile();  
                        // �Ӵ�jar�� �õ�һ��ö����  
                        Enumeration<JarEntry> entries = jar.entries();  
                        // ͬ���Ľ���ѭ������  
                        while (entries.hasMoreElements()) {  
                            // ��ȡjar���һ��ʵ�� ������Ŀ¼ ��һЩjar����������ļ� ��META-INF���ļ�  
                            JarEntry entry = entries.nextElement();  
                            String name = entry.getName();  
                            // �������/��ͷ��  
                            if (name.charAt(0) == '/') {  
                                // ��ȡ������ַ���  
                                name = name.substring(1);  
                            }  
                            // ���ǰ�벿�ֺͶ���İ�����ͬ  
                            if (name.startsWith(packageDirName)) {  
                                int idx = name.lastIndexOf('/');  
                                // �����"/"��β ��һ����  
                                if (idx != -1) {  
                                    // ��ȡ���� ��"/"�滻��"."  
                                    packageName = name.substring(0, idx)  
                                            .replace('/', '.');  
                                }  
                                // ������Ե�����ȥ ������һ����  
                                if ((idx != -1) || recursive) {  
                                    // �����һ��.class�ļ� ���Ҳ���Ŀ¼  
                                    if (name.endsWith(".class")  
                                            && !entry.isDirectory()) {  
                                        // ȥ�������".class" ��ȡ����������  
                                        String className = name.substring(  
                                                packageName.length() + 1, name  
                                                        .length() - 6);  
                                        try {  
                                            // ��Action��β����������ӵ�classes
                                        	if (className.endsWith("Action"))
                                            classes.add(Class  
                                                    .forName(packageName + '.'  
                                                            + className));  
                                        } catch (ClassNotFoundException e) {  
                                            // log  
                                            // .error("����û��Զ�����ͼ����� �Ҳ��������.class�ļ�");  
                                            e.printStackTrace();  
                                        }  
                                    }  
                                }  
                            }  
                        }  
                    } catch (IOException e) {  
                        // log.error("��ɨ���û�������ͼʱ��jar����ȡ�ļ�����");  
                        e.printStackTrace();  
                    }  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        return classes;  
    }  
    
    /** 
     * ���ļ�����ʽ����ȡ���µ�����Class 
     *  
     * @param packageName 
     * @param packagePath 
     * @param recursive 
     * @param classes 
     */  
    public static void findAndAddClassesInPackageByFile(String packageName,  
            String packagePath, final boolean recursive, Set<Class<?>> classes) {  
        // ��ȡ�˰���Ŀ¼ ����һ��File  
        File dir = new File(packagePath);  
        // ��������ڻ��� Ҳ����Ŀ¼��ֱ�ӷ���  
        if (!dir.exists() || !dir.isDirectory()) {  
            // log.warn("�û�������� " + packageName + " ��û���κ��ļ�");  
            return;  
        }  
        // ������� �ͻ�ȡ���µ������ļ� ����Ŀ¼  
        File[] dirfiles = dir.listFiles(new FileFilter() {  
            // �Զ�����˹��� �������ѭ��(������Ŀ¼) ��������.class��β���ļ�(����õ�java���ļ�)  
            public boolean accept(File file) {  
                return (recursive && file.isDirectory())  
                        || (file.getName().endsWith(".class"));  
            }  
        });  
        // ѭ�������ļ�  
        for (File file : dirfiles) {  
            // �����Ŀ¼ �����ɨ��  
            if (file.isDirectory()) {  
                findAndAddClassesInPackageByFile(packageName + "."  
                        + file.getName(), file.getAbsolutePath(), recursive,  
                        classes);  
            } else {  
                // �����java���ļ� ȥ�������.class ֻ��������  
                String className = file.getName().substring(0,  
                        file.getName().length() - 6);  
                try {  
                    // ��ӵ�������ȥ  
                    //classes.add(Class.forName(packageName + '.' + className));  
                                         //�����ظ�ͬѧ�����ѣ�������forName��һЩ���ã��ᴥ��static������û��ʹ��classLoader��load�ɾ�  
                	if (className.endsWith("Action"))
                		classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));    
                                } catch (ClassNotFoundException e) {  
                    // log.error("����û��Զ�����ͼ����� �Ҳ��������.class�ļ�");  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  

}
