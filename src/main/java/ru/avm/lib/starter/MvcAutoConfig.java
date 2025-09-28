package ru.avm.lib.starter;

import jakarta.servlet.Filter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.UrlHandlerFilter;

@AutoConfiguration
@ConditionalOnProperty(prefix = "app.trailing-slash-matches", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(ExceptionsProperties.class)

public class MvcAutoConfig {

    @Bean
    public Filter trailingSlashFilter() {
        return UrlHandlerFilter.trailingSlashHandler("/**")
                .wrapRequest().build();
    }

}
