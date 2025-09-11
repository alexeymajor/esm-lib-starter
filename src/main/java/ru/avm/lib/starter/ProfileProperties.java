package ru.avm.lib.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.profile")
public class ProfileProperties {
    boolean disabled = false;
}
