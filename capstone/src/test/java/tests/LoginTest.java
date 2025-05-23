package tests;

import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestConstants;

import java.time.Duration;

public class LoginTest extends BaseTest {

    Logger log = Logger.getLogger(LoginTest.class);

    @Test(priority = 1)
    public void testLogin() throws InterruptedException {
        log.info("Navigating to login page");
        driver.get(TestConstants.LOGIN_URL);
        Thread.sleep(3000);

        LoginPage lp = new LoginPage(driver);

        log.info("Attempting to log in with valid credentials");
        lp.login(TestConstants.VALID_USERNAME,TestConstants.VALID_PASSWORD);
        Thread.sleep(3000);

        log.info("Validating successful login");
        boolean isLoggedIn = lp.isLogoutButtonDisplayed();
        Assert.assertTrue(isLoggedIn, "Login failed: Logout button not displayed");

        Thread.sleep(3000);
        log.info("Login test passed");
    }

    @Test(priority = 2)
    public void testInvalidUsername() throws InterruptedException {
        log.info("Navigating to login page");
        driver.get(TestConstants.LOGIN_URL);
        Thread.sleep(3000);

        LoginPage lp = new LoginPage(driver);

        log.info("Attempting login with invalid username");
        lp.login(TestConstants.INVALID_USERNAME, TestConstants.VALID_PASSWORD);
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).getText();

        log.info("Error message: " + errorText);
        Assert.assertTrue(errorText.contains("Invalid"), "Expected 'Invalid' error message not displayed");

        Thread.sleep(3000);
        log.info("Invalid username test passed");
    }

    @Test(priority = 3)
    public void testInvalidPassword() throws InterruptedException {
        log.info("Navigating to login page");
        driver.get(TestConstants.LOGIN_URL);
        Thread.sleep(3000);

        LoginPage lp = new LoginPage(driver);

        log.info("Attempting login with invalid password");
        lp.login(TestConstants.VALID_USERNAME, TestConstants.INVALID_PASSWORD);
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).getText();

        log.info("Error message: " + errorText);
        Assert.assertTrue(errorText.contains("Invalid"), "Expected 'Invalid' error message not displayed");

        Thread.sleep(3000);
        log.info("Invalid password test passed");
    }
}
