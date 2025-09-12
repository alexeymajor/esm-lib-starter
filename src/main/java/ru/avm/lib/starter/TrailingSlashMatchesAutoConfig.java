package ru.avm.lib.starter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@AutoConfiguration
@ConditionalOnProperty(prefix = "app.trailing-slash-matches", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(ExceptionsProperties.class)

public class TrailingSlashMatchesAutoConfig {

    public static class TrailingSlashFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {

            val req = (HttpServletRequest) request;
            String path = req.getRequestURI();

            if (path.length() > 1 && path.endsWith("/")) {
                String newPath = path.substring(0, path.length() - 1);
                val resp = (HttpServletResponse) response;
                resp.sendRedirect(req.getContextPath() + newPath);
                return;
            }

            chain.doFilter(request, response);
        }
    }

    @Bean
    public Filter trailingSlashFilter() {
        return new TrailingSlashFilter();
    }

}
