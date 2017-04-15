package wiki.chenxun.ace.core.base.beans;

import wiki.chenxun.ace.core.base.io.Resource;

public interface BeanDefinitionReader {
	
	int loadBeanDefinitions(Resource resource,String prefix) throws Exception;
	int loadBeanDefinitions(String prefix,Resource... resources) throws Exception;
}
