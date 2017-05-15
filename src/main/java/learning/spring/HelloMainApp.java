package learning.spring;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloMainApp {
	static final Logger logger = LoggerFactory.getLogger(HelloMainApp.class);
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBean.xml");
		HelloWorld hello1 = (HelloWorld) context.getBean("helloBean");
		hello1.printHello();
	}
	
}
