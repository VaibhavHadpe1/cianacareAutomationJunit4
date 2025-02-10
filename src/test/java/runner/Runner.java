
package runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/ClinicOnboarding.feature") // Ensure this path is correct
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty,json:target/cucumber-reports/Cucumber.json, html:target/cucumber-reports/index.html")
@ConfigurationParameter(key = "cucumber.glue", value = "steps") // Ensure 'steps' package contains step definitions
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@run") // Ensure correct tag filtering
public class Runner {
}

