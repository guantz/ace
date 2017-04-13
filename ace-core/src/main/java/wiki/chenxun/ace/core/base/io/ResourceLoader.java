package wiki.chenxun.ace.core.base.io;

import wiki.chenxun.ace.core.base.constant.ResourceConstance;

/**
 * 资源加载接口
 * @author guantz
 *
 */
public interface ResourceLoader {

	/**
	 * classpath
	 */
	String CLASSPATH_URL_PREFIX = ResourceConstance.CLASSPATH_URL_PREFIX;
	
	/**
	 * 获取资源
	 * @param location
	 * @return
	 */
	Resource getResource(String location);
	
	/**
	 * 获取类加载器
	 * @return
	 */
	ClassLoader getClassLoader();
}
