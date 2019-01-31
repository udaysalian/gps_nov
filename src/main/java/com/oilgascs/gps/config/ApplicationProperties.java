package com.oilgascs.gps.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties specific to Netra.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    public boolean isLoadData() {
        return loadData;
    }

    public void setLoadData(boolean loadData) {
        this.loadData = loadData;
    }

    private boolean loadData = false;


    public final  InitData initData = new InitData();

    public InitData getInitData() {
        return initData;
    }

    public static class InitData {

        private boolean enabled = false;

        void setEnabled(boolean flag){
            enabled = flag;
        }
        public boolean getEnabled(){
            return enabled;
        }
    }
}
