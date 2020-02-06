package io.github.mamachanko;

import org.fluentlenium.adapter.FluentStandalone;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;

import java.io.File;

public class E2eTest {

    @Rule
    public BrowserWebDriverContainer browser = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(VncRecordingMode.RECORD_ALL, new File("/tmp/e2e"));

    private FluentStandalone fluent;

    @Before
    public void setUp() {
        fluent = new FluentStandalone() {
            @Override
            public WebDriver newWebDriver() {
                return browser.getWebDriver();
            }
        };
        fluent.init();
    }

    @Test
    public void test() {
        fluent.goTo("https://github.com/testcontainers/testcontainers-java");
    }
}
