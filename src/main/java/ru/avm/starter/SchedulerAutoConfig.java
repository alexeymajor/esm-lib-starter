package ru.avm.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.avm.common.SchedulerConfig;

@Configuration
@ConditionalOnProperty(prefix = "app.scheduler", name = "disabled", havingValue = "false", matchIfMissing = true)
@Import(SchedulerConfig.class)
@EnableConfigurationProperties(SchedulerProperties.class)
public class SchedulerAutoConfig {

}
