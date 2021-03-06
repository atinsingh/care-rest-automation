package io.pragra.api.framework.config;

import java.util.Map;

/**
 * @project api-framework
 * Created By Rsingh on 2019-05-15.
 */

public class RouteConfig {

    private String host;
    private String basePath;
    private String datafileName;

    private Map<String, String> api;

    public Map<String, String> getApi() {
        return api;
    }

    public void setApi(Map<String, String> api) {
        this.api = api;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getDatafileName() {
        return datafileName;
    }

    public void setDatafileName(String datafileName) {
        this.datafileName = datafileName;
    }

    @Override
    public String toString() {
        return "RouteConfig{" +
                "api=" + api +
                '}';
    }
}
