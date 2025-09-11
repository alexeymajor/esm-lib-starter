package ru.avm.lib.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.common")
public class SchedulerProperties {
    boolean disabled = false;
}
