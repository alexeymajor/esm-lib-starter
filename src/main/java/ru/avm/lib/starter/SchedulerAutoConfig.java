package ru.avm.lib.starter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import ru.avm.lib.common.SchedulerConfig;

@AutoConfiguration
@ConditionalOnProperty(prefix = "app.scheduler", name = "disabled", havingValue = "false", matchIfMissing = true)
@Import(SchedulerConfig.class)
@EnableConfigurationProperties(SchedulerProperties.class)
public class SchedulerAutoConfig {

}
