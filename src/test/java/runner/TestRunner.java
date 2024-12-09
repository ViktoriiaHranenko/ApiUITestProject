package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",  // Шлях до feature-файлів
        glue = "steps",                            // Шлях до класів зі step definitions
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",  // Звіт HTML
                "json:target/cucumber.json",          // JSON звіт для Allure
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true                         // Форматування консолі
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
