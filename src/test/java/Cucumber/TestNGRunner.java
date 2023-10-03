package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber", glue="Stepdefination", monochrome=true,tags="@Errorvalidation", plugin= {"html:target/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
