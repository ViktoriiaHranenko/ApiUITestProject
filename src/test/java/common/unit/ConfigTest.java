package common.unit;

import org.aeonbits.owner.ConfigFactory;
import org.config.ProjectTestConfig;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ConfigTest {
    @Test
    public void urlIsLoadedFromPropertiesFile() {
        ProjectTestConfig config = ConfigFactory.create(ProjectTestConfig.class);
        String baseUrl = "https://temporary-url-from-properties.com";
        assertEquals(config.testApiBaseUrl(), baseUrl, "Expected URL to be taken from .properties file");
    }

    @Test
    public void urlIsOverriddenBySystemProperties() {
        String systemUrl = "https://url-from-system.com";
        System.setProperty("test.api.baseUrl", systemUrl);
        ProjectTestConfig config = ConfigFactory.create(ProjectTestConfig.class);
        assertEquals(config.testApiBaseUrl(), systemUrl, "Expected URL to be overridden by system properties");
    }

    @Test
    public void urlIsGetFromDefaultValue() {
        String defaultValue = "https://url-from-default-value.com";
        ProjectTestConfig config = ConfigFactory.create(ProjectTestConfig.class);
        assertEquals(config.defaultTestUrl(), defaultValue, "Expected USL to be taken form default value");
    }

    @Test
    public void urlIsSetByMockedConfiguration() {
        String testUrl = "https://mock-url.com";
        ProjectTestConfig config = ConfigFactory.create(ProjectTestConfig.class, Map.of(
                "test.api.baseUrl", testUrl
        ));
        assertEquals(config.testApiBaseUrl(), testUrl, "Expected mocked URL to override all other sources");
    }

    @AfterClass
    public void cleanUp() {
        System.clearProperty("test.api.baseUrl");
    }
}
