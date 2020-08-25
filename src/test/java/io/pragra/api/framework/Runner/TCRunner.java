package io.pragra.api.framework.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
             plugin = { "json:target/cucumber.json", "pretty",
                "html:target/cucumber-reports" },
            features = "classpath:FeatureFiles",
            glue = {"io.pragra.api.framework.testcases"}
)
public class TCRunner {

}




