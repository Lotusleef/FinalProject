package web.runners;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/web")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "web.stepdef,web.utils")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value =
        "pretty, html:build/reports/cucumber/web.html, json:build/reports/cucumber/web.json, junit:build/reports/cucumber/web-junit.xml")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@web")
public class WebTestRunner {
}
