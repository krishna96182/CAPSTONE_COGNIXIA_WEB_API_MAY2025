package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class CombinedPage {
    WebDriver driver;

    // Form Elements
    @FindBy(id = "firstName") WebElement firstName;
    @FindBy(id = "lastName") WebElement lastName;
    @FindBy(id = "userEmail") WebElement email;
    @FindBy(id = "submit") WebElement submit;

    // Search Elements
    @FindBy(id = "searchBox") WebElement searchBox;

    public CombinedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ----- Form Methods -----
    public void fillForm(String fname, String lname, String mail) {
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        email.sendKeys(mail);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
    }

    // ----- Search Methods -----
    public void search(String query) {
        searchBox.clear();
        searchBox.sendKeys(query);
    }

    public boolean isResultVisible(String expectedText) {
        try {
            WebElement result = driver.findElement(By.xpath("//div[@class='rt-td' and contains(text(),'" + expectedText + "')]"));
            return result.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

