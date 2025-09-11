package ru.avm.lib.starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.avm.lib.common.dto.AuthUserDto;
import ru.avm.lib.common.dto.AuthorityDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@AutoConfiguration
@ConditionalOnProperty(prefix = "app.network", name = "disabled", havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(NetworkProperties.class)
@EnableFeignClients("ru.avm")
public class NetworkAutoConfig {

    private final ObjectMapper objectMapper;

    @Value("${spring.application.name}")
    private String applicationName;

    @SuppressWarnings("UastIncorrectHttpHeaderInspection")
    private static final String X_AUTH_HEADER = "X-Auth-User";

    @Bean
    public AuthUserDto serviceUser() {
        return AuthUserDto.builder()
                .id(1_000_001L)
                .authorities(List.of(AuthorityDto
                        .builder()
                        .authority("SCOPE_SERVICE")
                        .build()))
                .name(applicationName)
                .sid(applicationName)
                .build();
    }

    @SneakyThrows
    @Bean
    public String serviceAuthString() {
        return Base64.toBase64String(objectMapper.writeValueAsBytes(serviceUser()));
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header(X_AUTH_HEADER, serviceAuthString());
    }

    @Bean
    public RestOperations restTemplate() {
        val restTemplate = new RestTemplate();
        restTemplate.setInterceptors(
                new ArrayList<>(
                        List.of((request, body, execution) -> {
                            request.getHeaders().add(X_AUTH_HEADER, serviceAuthString());
                            return execution.execute(request, body);
                        }))
        );
        return restTemplate;
    }

    @Bean
    public Decoder feignDecoder() {
        ObjectFactory<HttpMessageConverters> objectFactory = () ->
                new HttpMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper));

        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

}
