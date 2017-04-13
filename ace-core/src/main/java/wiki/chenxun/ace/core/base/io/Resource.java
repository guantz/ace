package wiki.chenxun.ace.core.base.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 资源接口
 * @author GUANTZ
 *
 */
public interface Resource extends InputStreamSource{

	/**
	 * 资源是否存在
	 * @return
	 */
	boolean exists();
	
	/**
	 * 资源是否为文件
	 * @return
	 */
	boolean isFile();
	
	/**
	 * 获取URL
	 * @return
	 * @throws IOException
	 */
	URL getURL() throws IOException;
	
	/**
	 * 获取文件
	 * @return
	 * @throws IOException
	 */
	File getFile() throws IOException;
	
	/**
	 * 获取类加载器
	 * @return
	 */
	ClassLoader getClassLoader();
}
