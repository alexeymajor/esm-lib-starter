package ru.avm.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.stomp")
public class StompProperties {
    boolean disabled = false;
}
