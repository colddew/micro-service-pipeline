package cn.plantlink.a.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class MicroserviceAProperties {

    @Value("${remote.base.url.b}")
    private String bBaseUrl;

    @Value("${remote.base.url.c}")
    private String cBaseUrl;

    @Value("${service.message}")
    private String message;

    public String getBBaseUrl() {
        return bBaseUrl;
    }

    public void setBBaseUrl(String bBaseUrl) {
        this.bBaseUrl = bBaseUrl;
    }

    public String getCBaseUrl() {
        return cBaseUrl;
    }

    public void setCBaseUrl(String cBaseUrl) {
        this.cBaseUrl = cBaseUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
