package common.unit;

import io.cucumber.core.cli.Main;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CucumberFrameworkTest {
    @Test
    public void testCucumberRunsWithoutErrors() {
        byte result = Main.run("--plugin", "pretty",
                "--glue", "cucumber.org.pet_store.steps",
                "src/test/resources/features/pet.feature");

        Assert.assertEquals(result, 0, "Cucumber Runner failed with errors!");
    }
}
