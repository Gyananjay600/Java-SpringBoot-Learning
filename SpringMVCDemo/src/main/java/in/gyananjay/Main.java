package in.gyananjay;

import in.gyananjay.config.WebConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        //Boiler plate code
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        String contextPath = "/";
        String baseDoc = new File(("src/main/webapp"))
                .getAbsolutePath();

         Context context = tomcat.addContext(contextPath,baseDoc);

         // Application Context --> means IOC Container Up

        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();

        applicationContext.register(WebConfig.class);

        // Dispatcher Servlet

        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(applicationContext);

        tomcat.addServlet(context,
                "dispatcherServlet", dispatcherServlet);

        context.addServletMappingDecoded("/*", "dispatcherServlet");

        tomcat.start();

        System.out.println("Tomcat Started on port : 8080");

        // Keep server running
        tomcat.getServer().await();

    }
}