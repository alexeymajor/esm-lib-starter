package ru.avm.starter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.avm.security.SecurityConfig;
import ru.avm.security.acl.SecurityAclConfig;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
@ConditionalOnProperty(prefix = "app.security", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(SecurityProperties.class)
@Import({SecurityConfig.class, SecurityAclConfig.class})
@AutoConfigureOrder(HIGHEST_PRECEDENCE + 10)
@AutoConfigureBefore(SecurityAutoConfiguration.class)
public class SecurityAutoConfig {
}

