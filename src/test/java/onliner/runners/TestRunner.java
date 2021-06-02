package onliner.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/onliner/features", glue = "onliner/stepdefinitions")
public class TestRunner extends AbstractTestNGCucumberTests {
}