package ru.avm.lib.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import ru.avm.lib.common.NetworkConfig;

@RequiredArgsConstructor
@AutoConfiguration
@ConditionalOnProperty(prefix = "app.network", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(NetworkProperties.class)
@Import(NetworkConfig.class)
public class NetworkAutoConfig {

}
