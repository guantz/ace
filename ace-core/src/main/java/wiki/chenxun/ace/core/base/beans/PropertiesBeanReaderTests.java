package wiki.chenxun.ace.core.base.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import wiki.chenxun.ace.core.base.io.ClassPathResource;

public class PropertiesBeanReaderTests {
	public static void main(String[] args) throws Exception {
//		File file = new File("D:\\simpleConstructorArg.properties");
//		FileInputStream fis = new FileInputStream(file);
//		BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
//		String line = "";
//		while((line= bf.readLine()) != null)
//		{
//			System.out.println(line);
//		}
		PropertiesBeanReader propertiesBeanReader = new PropertiesBeanReader();
		ClassPathResource classPathResource = new ClassPathResource("simpleConstructorArg.properties");
		propertiesBeanReader.loadBeanDefinitions(classPathResource, "");
		TestBean testBean = (TestBean) propertiesBeanReader.getBean("testBean");
		System.out.println(testBean.getName());
	}
}
