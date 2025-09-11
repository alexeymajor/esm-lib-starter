package ru.avm.lib.starter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import ru.avm.lib.security.SecurityConfig;
import ru.avm.lib.security.acl.SecurityAclConfig;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@AutoConfiguration
@ConditionalOnProperty(prefix = "app.security", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(SecurityProperties.class)
@Import({SecurityConfig.class, SecurityAclConfig.class})
@AutoConfigureOrder(HIGHEST_PRECEDENCE + 10)
@AutoConfigureBefore(SecurityAutoConfiguration.class)
public class SecurityAutoConfig {
}

