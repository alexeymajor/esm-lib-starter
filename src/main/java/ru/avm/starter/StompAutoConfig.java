package ru.avm.starter;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.avm.stomp.StompConfig;
import ru.avm.stomp.TrustStompSubscriptionAuthenticator;

@Configuration
@ConditionalOnProperty(prefix = "app.stomp", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(StompProperties.class)
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import(StompConfig.class)
public class StompAutoConfig {

    @Bean
    @ConditionalOnProperty(prefix = "app.stomp", name = "trust", havingValue = "true")
    public TrustStompSubscriptionAuthenticator trustStompSubscriptionAuthenticator() {
        return new TrustStompSubscriptionAuthenticator();
    }

}
