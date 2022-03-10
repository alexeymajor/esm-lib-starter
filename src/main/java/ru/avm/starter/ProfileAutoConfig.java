package ru.avm.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.avm.profile.ProfileConfig;

@RequiredArgsConstructor

@Configuration
@ConditionalOnProperty(prefix = "app.profile", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(ProfileProperties.class)
@AutoConfigureBefore(JpaRepositoriesAutoConfiguration.class)
@EnableJpaRepositories("ru.avm.profile")
@EntityScan("ru.avm.profile")
@Import(ProfileConfig.class)
public class ProfileAutoConfig {

}
