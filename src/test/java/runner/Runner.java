
package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features = {"src/test/resources/features/ClinicOnboarding.feature"}, ///AppiumAutomation/src/test/resources/Features/Sample.feature
        plugin = {
                "pretty",
                "json:target/cucumber-reports/reports.json",
                "json:target/cucumber-reports/cucumber.runtime.formatter.JSONFormatter"},
        monochrome = true,
        glue = {"steps"},
        tags = "@run")
public class Runner {

}

