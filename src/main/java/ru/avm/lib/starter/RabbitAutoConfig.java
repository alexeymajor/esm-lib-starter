package ru.avm.lib.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import ru.avm.lib.common.RabbitConfig;

@RequiredArgsConstructor

@AutoConfiguration
@ConditionalOnProperty(prefix = "app.rabbit", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(RabbitProperties.class)
@AutoConfigureBefore(RabbitAutoConfiguration.class)
@Import(RabbitConfig.class)
public class RabbitAutoConfig {

}
