package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.TestConstants;

import java.util.Iterator;
import java.util.Set;

public class OptionalFunctionalityTest extends BaseTest {

    Logger log = Logger.getLogger(OptionalFunctionalityTest.class);

    private void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Test(priority = 1)
    public void testActionExample() throws InterruptedException {
        log.info("Navigating to actions page");
        driver.get(TestConstants.ACTIONS_URL);
        Thread.sleep(2000);

        Actions actions = new Actions(driver);
        WebElement mainItem = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        scrollIntoView(mainItem);
        Thread.sleep(2000);
        actions.moveToElement(mainItem).perform();
        log.info("Hovered over Main Item 2");

        Assert.assertTrue(mainItem.isDisplayed(), "Main Item 2 not visible after hover");
        log.info("ActionExample test passed");
    }

    @Test(priority = 2)
    public void testIFrameExample() throws InterruptedException {
        log.info("Navigating to iframe page");
        driver.get(TestConstants.IFRAME_URL);
        Thread.sleep(2000);

        driver.switchTo().frame("frame1");
        WebElement heading = driver.findElement(By.id("sampleHeading"));
        scrollIntoView(heading);
        Thread.sleep(2000);

        log.info("IFrame text: " + heading.getText());
        Assert.assertEquals(heading.getText(), "This is a sample page", "IFrame content mismatch");

        driver.switchTo().defaultContent();
        log.info("IFrameExample test passed");
    }

    @Test(priority = 3)
    public void testWindowsExample() throws InterruptedException {
        log.info("Navigating to browser windows page");
        driver.get(TestConstants.WINDOWS_URL);
        Thread.sleep(2000);

        WebElement button = driver.findElement(By.id("windowButton"));
        scrollIntoView(button);
        Thread.sleep(2000);
        button.click();

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> it = allWindows.iterator();

        boolean newWindowValidated = false;

        while (it.hasNext()) {
            String childWindow = it.next();
            if (!parentWindow.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                WebElement heading = driver.findElement(By.id("sampleHeading"));
                scrollIntoView(heading);
                Thread.sleep(2000);
                log.info("New Window Text: " + heading.getText());
                Assert.assertEquals(heading.getText(), "This is a sample page");
                newWindowValidated = true;
                driver.close();
            }
        }

        driver.switchTo().window(parentWindow);
        Assert.assertTrue(newWindowValidated, "New window was not validated");
        log.info("WindowsExample test passed");
    }

    @Test(priority = 4)
    public void testAlertExample() throws InterruptedException {
        log.info("Navigating to alerts page");
        driver.get(TestConstants.ALERTS_URL);
        Thread.sleep(2000);

        WebElement alertButton = driver.findElement(By.id("alertButton"));
        scrollIntoView(alertButton);
        Thread.sleep(2000);
        alertButton.click();

        String alertText = driver.switchTo().alert().getText();
        log.info("Alert text: " + alertText);
        Assert.assertEquals(alertText, "You clicked a button", "Alert text mismatch");
        driver.switchTo().alert().accept();

        log.info("AlertExample test passed");
    }
}
