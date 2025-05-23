package tests;

import pages.CombinedPage;
import utils.TestConstants;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CombinedTest extends BaseTest {

    Logger log = Logger.getLogger(CombinedTest.class);

    @Test(priority = 1)
    public void testFormSubmission() throws InterruptedException {
        log.info("Navigating to form page");
        driver.get(TestConstants.FORM_URL);
        Thread.sleep(1000);

        CombinedPage cp = new CombinedPage(driver);
        log.info("Filling form with valid data");
        cp.fillForm(TestConstants.VALID_FIRST_NAME, TestConstants.VALID_LAST_NAME, TestConstants.VALID_EMAIL);

        Thread.sleep(2000);
        log.info("Validating form submission");

        log.info("Form submission test passed");
    }

    @Test(priority = 2)
    public void testFormSubmissionNegative() throws InterruptedException {
        log.info("Navigating to form page");
        driver.get(TestConstants.FORM_URL);
        Thread.sleep(1000);

        CombinedPage cp = new CombinedPage(driver);
        log.info("Filling form with invalid email");
        cp.fillForm(TestConstants.VALID_FIRST_NAME, TestConstants.VALID_LAST_NAME, TestConstants.INVALID_EMAIL);

        Thread.sleep(2000);

        // Validate that form is NOT submitted (modal not shown)
        boolean isModalDisplayed;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
            isModalDisplayed = true;
        } catch (Exception e) {
            isModalDisplayed = false;
        }

        Assert.assertFalse(isModalDisplayed, "Form should not be submitted with invalid email");
        log.info("Negative form test passed");
    }

    @Test(priority = 3)
    public void testSearchFunctionality() throws InterruptedException {
        log.info("Navigating to Web Tables page");
        driver.get(TestConstants.WEB_TABLES_URL);
        Thread.sleep(1000);

        CombinedPage cp = new CombinedPage(driver);
        log.info("Performing search for 'Cierra'");
        cp.search(TestConstants.VALID_SEARCH_TERM);

        Thread.sleep(2000); // Observe table update

        log.info("Validating search result");
        Assert.assertTrue(cp.isResultVisible("Cierra"), "Search failed: Result not visible");

        log.info("Search test passed");
    }

    @Test(priority = 4)
    public void testSearchNegative() throws InterruptedException {
        log.info("Navigating to Web Tables page");
        driver.get(TestConstants.WEB_TABLES_URL);
        Thread.sleep(1000);

        CombinedPage cp = new CombinedPage(driver);
        log.info("Performing search for 'UnknownName'");
        cp.search(TestConstants.INVALID_SEARCH_TERM);

        Thread.sleep(2000); // Wait to observe empty result

        log.info("Validating that no result is shown");
        Assert.assertFalse(cp.isResultVisible("UnknownName"), "Search should return no results");

        log.info("Negative search test passed");
    }
}
