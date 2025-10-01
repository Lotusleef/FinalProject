package api.runners;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/api")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "api.stepdef")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value =
        "pretty, html:build/reports/cucumber/api.html, json:build/reports/cucumber/api.json, junit:build/reports/cucumber/api-junit.xml")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@api")
public class ApiTestRunner {
}
