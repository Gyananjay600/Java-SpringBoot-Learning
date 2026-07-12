package in.gyananjay;

import in.gyananjay.config.WebConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        String contextPath = "/";
        String baseDoc = new File(("src/main/webapp"))
                .getAbsolutePath();

        Context context = tomcat.addContext(contextPath, baseDoc);

        // Fix: Jasper's TLD scanner only checks WEB-INF/lib by default.
        // Since our JSTL jars come from Maven (not WEB-INF/lib), we tell
        // the JarScanner to also scan the JVM classpath so <c:forEach> etc. resolve.
        StandardJarScanner jarScanner = new StandardJarScanner();
        jarScanner.setScanClassPath(true);
        context.setJarScanner(jarScanner);

        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();

        applicationContext.register(WebConfig.class);

        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(applicationContext);

        tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);
        context.addServletMappingDecoded("/*", "dispatcherServlet");

        tomcat.start();

        System.out.println("Tomcat Started on port : 8080");

        tomcat.getServer().await();
    }
}