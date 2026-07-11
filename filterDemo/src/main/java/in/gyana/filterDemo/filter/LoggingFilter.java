package in.gyana.filterDemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Component
@Order(2)
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        long startTime = System.currentTimeMillis();

        // we are working with http request and response
        // we need to typecast the servletRequest to HttpServletRequest

        HttpServletRequest httpRequest =
                (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse =
                (HttpServletResponse) servletResponse;

        // it generates unique request id in each request hit
        String requestId = UUID.randomUUID().toString();
        httpResponse.setHeader("X-Request-ID", requestId);


        // Request log
        System.out.println("Incoming request : "
                + httpRequest.getMethod() + " "
                + httpRequest.getRequestURI());

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {

            long duration = System.currentTimeMillis() - startTime;

            //Response status log

            System.out.println("Response Status : "
                    + httpResponse.getStatus());

            System.out.println("API Response Time : " + duration/1000 + " seconds");

        }
    }
}
