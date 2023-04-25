package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {
    @Before
    public void setUp() {
        // Open browser and launch url
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        // close all windows
        closeBrowser();
    }

    //This method is used to select menu

    public void selectMenu(String menu) {
        clickOnElement(By.xpath("//nav[@id='menu']//a[normalize-space()='" + menu + "']"));
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {

        String menuName = "Show AllDesktops";
        // Mouse hover on Desktops Tab.and click
        mouseHoverAndClick(By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Desktops']"));
        // Click on “Show All Desktops”
        selectMenu(menuName);
        // before shorting value
        List<WebElement> beforeShortValue = driver.findElements(By.xpath("//div[@class='caption']//h4"));
        List<String> beforeShortDesktopValue = new ArrayList<>();
        for (WebElement value : beforeShortValue) {
            beforeShortDesktopValue.add(value.getText());
        }
        // Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (Z - A)");
        Thread.sleep(2000);
        // After shorting value
        List<WebElement> afterShortValue = driver.findElements(By.xpath("//div[@class='caption']//h4"));
        List<String> afterShortDesktopValue = new ArrayList<>();
        Thread.sleep(2000);
        for (WebElement value1 : afterShortValue) {
            afterShortDesktopValue.add(value1.getText());
        }
        Collections.sort(beforeShortDesktopValue, String.CASE_INSENSITIVE_ORDER);// Ascending order
        Collections.reverse(beforeShortDesktopValue); // descending order
        // Verify the Product will arrange in Descending order.
        Assert.assertEquals(beforeShortDesktopValue, afterShortDesktopValue);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        String menuName = "Show AllDesktops";
        // Mouse hover on Desktops Tab.and click
      mouseHoverAndClick(By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Desktops']"));
        // Click on “Show All Desktops”
        selectMenu(menuName);
        // Select Sort By position "Name: A to Z"
      selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (A - Z)");
        // Select product “HP LP3065”
        clickOnElement(By.xpath("//a[normalize-space()='HP LP3065']"));
        String expectedMessage = "HP LP3065";
        String actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']"));
        //Verify the Text "HP LP3065"
     verifyTwoTextMessage(expectedMessage, actualMessage);
        // Select Delivery Date "2022-11-30"
        selectDate("30", "November", "2022");
        // Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        // Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        expectedMessage = "Success: You have added HP LP3065 to your shopping cart!\n";
        actualMessage = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String[] actualmsg = actualMessage.split("×");
        verifyTwoTextMessage(expectedMessage, actualmsg[0]);
        // Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        // Verify the text "Shopping Cart"
        expectedMessage = "Shopping Cart  (1.00kg)";
        actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        //Verify the Product name "HP LP3065"
        expectedMessage = "HP LP3065";
        actualMessage = getTextFromElement(By.xpath("(//a[contains(text(),'HP LP3065')])[2]"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Verify the Delivery Date "2022-11-30"
        expectedMessage = "2022-11-30";
        actualMessage = getTextFromElement(By.xpath("(//small)[2]"));
        String[] actualmsg1 = actualMessage.split(":");
        verifyTwoTextMessage(expectedMessage, actualmsg1[1]);
        // Verify the Model "Product21"
        expectedMessage = "Product 21";
        actualMessage = getTextFromElement(By.xpath("//td[normalize-space()='Product 21']"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Verify the Todat "£74.73"
        expectedMessage = "$122.00";
        actualMessage = getTextFromElement(By.xpath("(//td[@class='text-right'][normalize-space()='$122.00'])[4]"));
        verifyTwoTextMessage(expectedMessage, actualMessage);

    }


    public void selectDate(String date, String month, String year) throws InterruptedException {

        clickOnElement(By.xpath("//i[@class='fa fa-calendar']"));
        while (true) {
            String monthYear = driver.findElement(By.xpath("(//th[@class='picker-switch'])[1]")).getText();
            String[] a = monthYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
            }
        }
        Thread.sleep(1000);
        // Select the date
        List<WebElement> allDates = driver.findElements(By.xpath("//*[@class='datepicker-days']//tbody//tr//td"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }


    }

}
