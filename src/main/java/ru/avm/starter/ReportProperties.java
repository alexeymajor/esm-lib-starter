package ru.avm.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.report")
public class ReportProperties {
    boolean disabled = false;
}
