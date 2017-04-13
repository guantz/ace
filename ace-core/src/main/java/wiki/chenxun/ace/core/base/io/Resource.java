package wiki.chenxun.ace.core.base.io;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * 资源接口
 * @author guantz
 *
 */
public interface Resource extends InputStreamSource{
	
	/**
	 * 是否存在
	 * @return
	 */
	boolean exists();
	
	/**
	 * 获取URL
	 * @return
	 */
	URL getURL() throws IOException;
	
	/**
	 * 获取URI
	 * @return
	 */
	URI getURI() throws IOException;
	
	/**
	 * 最后的修改时间
	 * @return
	 * @throws IOException
	 */
	long lastModified() throws IOException;
	
	/**
	 * 获取文件名
	 * @return
	 */
	String getFilename();
}
