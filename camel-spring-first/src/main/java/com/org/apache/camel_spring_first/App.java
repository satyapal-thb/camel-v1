package com.org.apache.camel_spring_first;

import java.util.Properties;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.apache.camel.impl.DefaultCamelContext;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Properties log4jProperties = new Properties();
		log4jProperties.setProperty("log4j.rootLogger", "ERROR, myConsoleAppender");
		log4jProperties.setProperty("log4j.appender.myConsoleAppender", "org.apache.log4j.ConsoleAppender");
		log4jProperties.setProperty("log4j.appender.myConsoleAppender.layout", "org.apache.log4j.PatternLayout");
		log4jProperties.setProperty("log4j.appender.myConsoleAppender.layout.ConversionPattern", "%-5p %c %x - %m%n");
		PropertyConfigurator.configure(log4jProperties);
		CamelContext context=new DefaultCamelContext();
		 context.addRoutes(new RouteBuilder(){
		      @Override
		      public void configure()throws Exception{

		    	 // from("timer:foo?period=10000")
		   
//		      from("google-drive://drive-files/copy?fileId=19Noh2X65inz3v1Ee0j7yizQQMpGdZRy1")
//		      .process(new MyProcessor()).to("file:C:\\Satyapal\\");
//		      
		from("file:C://inputFolder")
		.to("sftp:13.127.202.119:22/ehccdata/?autoCreate=false&username=ehccdata&password=EHCC@thb#7890&passiveMode=true&binary=true")
		.to("file:C://outputFolder")
		.log("${body}");
		     }});
		    context.start();
		    Thread.sleep(10000);
		    context.stop();
        System.out.println( "Hello World!" );
    }
}
