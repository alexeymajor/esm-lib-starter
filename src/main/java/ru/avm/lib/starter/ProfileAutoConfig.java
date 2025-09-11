package ru.avm.lib.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.avm.lib.profile.ProfileConfig;

@RequiredArgsConstructor

@AutoConfiguration
@ConditionalOnProperty(prefix = "app.profile", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(ProfileProperties.class)
@AutoConfigureBefore(JpaRepositoriesAutoConfiguration.class)
@EnableJpaRepositories("ru.avm.lib.profile")
@EntityScan("ru.avm.lib.profile")
@Import(ProfileConfig.class)
public class ProfileAutoConfig {

}
