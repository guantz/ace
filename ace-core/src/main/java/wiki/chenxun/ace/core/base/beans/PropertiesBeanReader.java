package wiki.chenxun.ace.core.base.beans;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import wiki.chenxun.ace.core.base.io.Resource;

/**
 * Properties文件读取bean
 * @author GUANTZ
 *
 */
public class PropertiesBeanReader implements BeanDefinitionReader{

	public static final String DOT_SEPARATOR = ".";
	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<String,Object>();
	
	@Override
	public int loadBeanDefinitions(Resource resource,String prefix) throws Exception {
		int beanCount = 0;
		Properties props = new Properties();
		try
		{
			InputStream is = resource.getInputStream();
			try
			{
				props.load(is);
				beanCount = registerBeans(props,prefix);
			}finally {
				is.close();
			}
		}catch (Exception e) {
			
		}
		return beanCount;
	}

	/**
	 * 注册bean
	 * @param props
	 * @param prefix
	 */
	private int registerBeans(Properties props, String prefix) {
		if(null == prefix)
		{
			prefix="";
		}
		
		Map<String,Set<String>> nameAndPropertiesMap = new HashMap<String,Set<String>>();
		Set<String> propSet = new HashSet<String>();
		
		for(Object key:props.keySet())
		{
			if(!(key instanceof String))
			{
				throw new IllegalArgumentException("Illegal key [" + key + "]: only Strings allowed");
			}
			
			String keyString = (String)key;
			if(keyString.startsWith(prefix))
			{
				//获取属性
				String nameAndProperty = keyString.substring(prefix.length());
				int sepIdx = -1;
				sepIdx = nameAndProperty.lastIndexOf(DOT_SEPARATOR);
				
				if(sepIdx != -1)
				{
					String beanName = nameAndProperty.substring(0, sepIdx);
					if(!nameAndPropertiesMap.containsKey(beanName))
					{
						//先清除之前的
						propSet.clear();
					}
					propSet.add(keyString);
					nameAndPropertiesMap.put(beanName, propSet);
				}
			}
		}
		
		try
		{
			//注册生成bean,暂时不支持构造参数的，只支持get/set
			for(Map.Entry<String, Set<String>> entry:nameAndPropertiesMap.entrySet())
			{
				String className = entry.getKey()+DOT_SEPARATOR+"class";
				Set<String> values = entry.getValue();
				values.remove(className);
				
				Class<?> clz = Class.forName(props.getProperty(className));
				Object obj = clz.newInstance();
				String str="";
				String value="";
				for(String s:values)
				{
					str = s.substring(s.lastIndexOf(DOT_SEPARATOR)+1, s.length());
					PropertyDescriptor pd = new PropertyDescriptor(str,clz);
					Method method = pd.getWriteMethod();
					value = props.getProperty(s);
					if(isNumber(value))
					{
						
						method.invoke(obj, Integer.valueOf(value));
					}else
					{
						method.invoke(obj, value);
					}
				}
				beanMap.putIfAbsent(entry.getKey(), obj);
			}
		}catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return beanMap.size();
	}
	
	public Object getBean(String beanName)
	{
		return beanMap.get(beanName);
	}
	
	private boolean isNumber(String str)
	{
		Pattern pattern = Pattern.compile(NUMBER_PATTERN);
		if(pattern.matcher(str).matches())
		{
			return true;
		}
		return false;
	}

	@Override
	public int loadBeanDefinitions(String prefix, Resource... resources) throws Exception {
		if(resources != null && resources.length > 0)
		{
			for(Resource resource:resources)
			{
				loadBeanDefinitions(resource, prefix);
			}
		}
		return 0;
	}
}
