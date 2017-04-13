package wiki.chenxun.ace.core.base.io;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamSource {

	/**
	 * 获取输入流 
	 * @return
	 * @throws IOException
	 */
	InputStream getInputStream() throws IOException;
}
