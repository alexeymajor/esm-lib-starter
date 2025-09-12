package ru.avm.lib.starter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import ru.avm.lib.common.ExceptionMapperConfig;
import ru.avm.lib.common.ExceptionsAdviceConfig;

@AutoConfiguration
@ConditionalOnProperty(prefix = "app.exceptions", name = "disabled", havingValue = "false", matchIfMissing = true)
@Import({ExceptionsAdviceConfig.class, ExceptionMapperConfig.class})
@EnableConfigurationProperties(ExceptionsProperties.class)
public class ExceptionsAutoConfig {

}
