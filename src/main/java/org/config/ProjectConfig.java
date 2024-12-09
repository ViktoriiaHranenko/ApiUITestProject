package org.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/resources/project.properties",
        "system:properties",
        "system:env"
})
public interface ProjectConfig extends Config {
    @DefaultValue("https://petstore.swagger.io/v2/")
    @Key("petstore.api.baseUrl")
    String petstoreApiBaseUrl();

    @Key("swaglabs.api.baseUrl")
    String swaglabsApiBaseUrl();
}
