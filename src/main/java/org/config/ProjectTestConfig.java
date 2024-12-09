package org.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/project-test-config.properties",
        "system:env"
})
public interface ProjectTestConfig extends Config {
    @Config.Key("test.api.baseUrl")
    String testApiBaseUrl();

    @Config.DefaultValue("https://url-from-default-value.com")
    @Config.Key("default.test.api.baseUrl")
    String defaultTestUrl();
}

