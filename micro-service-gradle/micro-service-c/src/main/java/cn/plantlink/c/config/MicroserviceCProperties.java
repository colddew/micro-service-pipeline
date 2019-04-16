package cn.plantlink.c.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class MicroserviceCProperties {

    @Value("${remote.base.url.d}")
    private String dBaseUrl;

    public String getDBaseUrl() {
        return dBaseUrl;
    }

    public void setDBaseUrl(String dBaseUrl) {
        this.dBaseUrl = dBaseUrl;
    }
}
