package ru.avm.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.network")
public class NetworkProperties {
    boolean disabled = false;
}
