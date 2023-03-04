package runner;

import Base.BaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = "src/test/resources/features",glue = "StepDefinition",
        plugin = { "pretty","html:target/cucumber-html-report.html"})
public class TestRunner extends BaseTest {

}
