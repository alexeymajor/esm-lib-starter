package ru.avm.lib.starter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.avm.lib.stomp.StompConfig;
import ru.avm.lib.stomp.StompProperties;
import ru.avm.lib.stomp.TrustStompSubscriptionAuthenticator;

@AutoConfiguration
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
