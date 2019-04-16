package cn.plantlink.b.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class MicroserviceBProperties {

    @Value("${remote.base.url.d}")
    private String dBaseUrl;

    public String getDBaseUrl() {
        return dBaseUrl;
    }

    public void setDBaseUrl(String dBaseUrl) {
        this.dBaseUrl = dBaseUrl;
    }
}
