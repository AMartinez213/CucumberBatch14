package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//      features we use to provide  the path of all the feature files
        features = "src/test/resources/features/",
        glue = "steps",
//      when you set dry run to true, it stops actual execution
//      it will quickly scan all the gherkin steps whether they're implemented o r not
//      when we set dry run to false, it starts execution again
        dryRun = false,
        tags = "@tc1101",

//      to remover irrelavant information from console, you need to set monochrome to true
        monochrome = false,
//      pretty keyword prints the steps in the console to increase readability
//      to generate the reports we need plugin of runner class
//      this failed.txt file holds all the scenarios which are failed during execution
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json","rerun:target/failed.txt"}
)
public class SmokeRunner {
}
