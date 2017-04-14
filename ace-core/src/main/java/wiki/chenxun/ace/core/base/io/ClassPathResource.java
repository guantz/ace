package wiki.chenxun.ace.core.base.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * classpath加载文件
 * @author GUANTZ
 *
 */
public class ClassPathResource implements Resource{

	private String path;
	public ClassPathResource(String path)
	{
		this.path = path;
	}
	@Override
	public InputStream getInputStream() throws IOException {
		File file = getFile();
		InputStream is = new FileInputStream(file);
		return is;
	}

	@Override
	public boolean exists() {
		URL url;
		try {
			url = getURL();
			if(null == url)
			{
				return true;
			}
		} catch (IOException e) {
			
		}
		
		return false;
	}

	@Override
	public boolean isFile() {
		try {
			URL url = getURL();
			if("file".equals(url.getProtocol())){
				return true;
			}
		} catch (IOException e) {
			
		}
		return false;
	}

	@Override
	public URL getURL() throws IOException {
		ClassLoader classLoader = getClassLoader();
		URL url = classLoader.getResource(path);
		return url;
	}

	@Override
	public File getFile() throws IOException {
		String  file = getURL().getFile();
		return new File(file);
	}

	@Override
	public ClassLoader getClassLoader() {
		ClassLoader classLoader = getClass().getClassLoader();
		if(null == classLoader)
		{
			if(null == classLoader)
			{
				classLoader = ClassLoader.getSystemClassLoader();
			}
		}
		return classLoader;
	}
	
}
