package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException {
        String menuName = "Show AllLaptops & Notebooks";
        // Mouse hover on Laptops & Notebooks Tab.and click
      mouseHoverAndClick  (By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
        // Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("(//a[normalize-space()='Show AllLaptops & Notebooks'])[1]"));
        Thread.sleep(2000);
        // Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");
        int i = 1;
        int j = 1;
        // Value before shorting
        List<WebElement> beforeShortValue = driver.findElements(By.xpath("//*[contains(@class, 'price') and not(contains(@class,'price-tax'))]"));
        List<String> beforeShortDesktopValue = new ArrayList<>();
        for (WebElement ignored : beforeShortValue) {

            String beforeShortValue1 = getTextFromElement(By.xpath("(//p[@class='price'])[" + i + "]"));
            String[] list = beforeShortValue1.split("E");
            beforeShortDesktopValue.add((list[0].replace("$", "")));
            i++;
        }
        // Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");
        Thread.sleep(2000);
        // After shorting value
        List<WebElement> afterShortValue = driver.findElements(By.xpath("//*[contains(@class, 'price') and not(contains(@class,'price-tax'))]"));
        List<String> afterShortDesktopValue = new ArrayList<>();
        Thread.sleep(2000);
        for (WebElement value1 : afterShortValue) {
            String beforeShortValue1 = getTextFromElement(By.xpath("(//p[@class='price'])[" + j + "]"));
            String[] list = beforeShortValue1.split("E");
            afterShortDesktopValue.add((list[0].replace("$", "")));
            j++;
        }
        // Verify the Product price will arrange in High to Low order.
        Assert.assertEquals(beforeShortDesktopValue, afterShortDesktopValue);
    }
    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {

        String menuName = "Show AllLaptops & Notebooks";
        // Mouse hover on Laptops & Notebooks Tab and click
        mouseHoverAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
        // Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("(//a[normalize-space()='Show AllLaptops & Notebooks'])[1]"));
        // Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");
        // Select Product “MacBook”
        clickOnElement(By.xpath("//a[normalize-space()='MacBook']"));
        // Verify the text “MacBook”
        String expectedMessage = "MacBook";
        String actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='MacBook']"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Click on ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        // Verify the message “Success: You have added MacBook to your shopping cart!”
        expectedMessage = "Success: You have added MacBook to your shopping cart! ";
        actualMessage = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String[] actualmsg = actualMessage.split("×");
        verifyTwoTextMessage(expectedMessage, actualmsg[0]);
        // Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        // Verify the text "Shopping Cart"
        expectedMessage = "Shopping Cart  (0.00kg)";
        actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Verify the Product name "MacBook"
        expectedMessage = "MacBook";
        actualMessage = getTextFromElement(By.xpath("(//a[contains(text(),'MacBook')])[2]"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Change Quantity "2"
        sendTextToElement(By.xpath("//input[contains(@name,'quantity')]"), "2");
        //  Click on “Update” Tab
        clickOnElement(By.xpath("//i[@class='fa fa-refresh']"));
        // Verify the message “Success: You have modified your shopping cart!”
        expectedMessage = "Success: You have modified your shopping cart! ";
        actualMessage = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String[] actualmsg1 = actualMessage.split("×");
        verifyTwoTextMessage(expectedMessage, actualmsg1[0]);
        // Verify the Total "$1,204.00"
        expectedMessage = "$1,204.00";
        actualMessage = getTextFromElement(By.xpath("(//td[@class='text-right'][normalize-space()='$1,204.00'])[3]"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Click on “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
        Thread.sleep(2000);
        // Verify the text “Checkout”
        expectedMessage = "Checkout";
        actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Checkout']"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Verify the Text “New Customer”
        expectedMessage = "New Customer";
        actualMessage = getTextFromElement(By.xpath("//h2[normalize-space()='New Customer']"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value='guest']"));
        // Click on “Continue” tab
        clickOnElement(By.xpath("//input[@id='button-account']"));
        // Click on “Continue” tab
        clickOnElement(By.id("button-account"));
        // Fill the mandatory fields
        //first name
        sendTextToElement(By.id("input-payment-firstname"), "Dhara");
        //last name
        sendTextToElement(By.id("input-payment-lastname"), "Patel");
        //email
        sendTextToElement(By.id("input-payment-email"), "dp264@gmail.com");
        //telephone
        sendTextToElement(By.id("input-payment-telephone"), "07512345677");
        //address
        sendTextToElement(By.id("input-payment-address-1"), "21 London Road");
        //city
        sendTextToElement(By.id("input-payment-city"), "London");
        //postcode
        sendTextToElement(By.id("input-payment-postcode"), "SW1 5AD");
        //Region
        selectByVisibleTextFromDropDown(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/fieldset[1]/div[6]/select[1]"), "United Kingdom");
        //city
        selectByVisibleTextFromDropDown(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/fieldset[1]/div[7]/select[1]"), "Croydon");
        // Click on “Continue” Button
        clickOnElement(By.xpath("//div[@class='buttons']/div[@class='pull-right']/input[@value='Continue']"));
        // Add Comments About your order into text area
        sendTextToElement(By.xpath("//div[@class='panel-body']/p[2]/textarea[@name='comment']"), "Comment abcs");
        // Check the Terms & Conditions check box
        clickOnElement(By.xpath("//input[@name='agree']"));
        // Click on “Continue” button
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));
        // Verify the message “Warning: Payment method required!”
        String expectedWarningMessage = "Warning: Payment method required!" + " ×";
        String actualWarningMessage = getTextFromElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
        //verify whether expected equals actual
        Assert.assertEquals("No Alert", expectedWarningMessage, actualWarningMessage);
    }

}
