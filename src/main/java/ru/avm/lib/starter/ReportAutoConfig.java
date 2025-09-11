package ru.avm.lib.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.avm.lib.profile.ProfileService;
import ru.avm.lib.reports.*;
import ru.avm.lib.reports.repository.ReportRepository;
import ru.avm.lib.security.acl.admin.AdminService;

@RequiredArgsConstructor

@AutoConfiguration
@ConditionalOnProperty(prefix = "app.report", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(ReportProperties.class)
@AutoConfigureBefore(JpaRepositoriesAutoConfiguration.class)
@EnableJpaRepositories("ru.avm.lib.reports")
@EntityScan("ru.avm.lib.reports")
@Import(ReportConfig.class)
public class ReportAutoConfig {

    private final ReportService reportService;
    private final AdminService adminService;
    private final ReportMapper reportMapper;
    private final ReportRepository reportRepository;
    private final ProfileService profileService;

    @Bean
    @ConditionalOnProperty(prefix = "app.report", name = "rest-controller", havingValue = "true", matchIfMissing = true)
    public ReportController reportController() {
        return ReportController.builder()
                .reportRepository(reportRepository)
                .reportMapper(reportMapper)
                .reportService(reportService)
                .adminService(adminService)
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.report", name = "rest-controller", havingValue = "true", matchIfMissing = true)
    public ReportEditController reportEditController() {
        return ReportEditController.builder()
                .reportRepository(reportRepository)
                .profileService(profileService)
                .reportMapper(reportMapper)
                .reportService(reportService)
                .adminService(adminService)
                .build();
    }

}
