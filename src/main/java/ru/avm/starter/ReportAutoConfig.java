package ru.avm.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.avm.reports.ReportConfig;
import ru.avm.reports.ReportController;
import ru.avm.reports.ReportService;
import ru.avm.security.acl.admin.AdminService;

@RequiredArgsConstructor

@Configuration
@ConditionalOnProperty(prefix = "app.report", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(ReportProperties.class)
@AutoConfigureBefore(JpaRepositoriesAutoConfiguration.class)
@EnableJpaRepositories("ru.avm.reports")
@EntityScan("ru.avm.reports")
@Import(ReportConfig.class)
public class ReportAutoConfig {

    private final ReportService reportService;
    private final AdminService adminService;

    @Bean
    @ConditionalOnProperty(prefix = "app.report", name = "rest-controller", havingValue = "true", matchIfMissing = true)
    public ReportController reportController() {
        return new ReportController(reportService, adminService);
    }

}
