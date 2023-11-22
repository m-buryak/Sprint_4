package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentPage {
    private WebDriver driver;
    private By datePicker = By.cssSelector("div.react-datepicker__input-container input");
    private By rentalPeriod = By.cssSelector("div.Dropdown-placeholder");
    private By blackColor = By.xpath(".//label[@class='Checkbox_Label__3wxSf'][1]/input");
    private By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private By approvalButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    private By completeOrder = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[text()='Заказ оформлен']");

    public RentPage(WebDriver driver) {
        this.driver  = driver;
    }

    public void waitForLoadingElement(String xpathExpression) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
    }

    public void setDate() {
        driver.findElement(datePicker).click();
        String datePickerElement = ".//div[@class='react-datepicker__month-container']/div[@class='react-datepicker__month']/div[@class='react-datepicker__week'][last()]/div[1]";
        waitForLoadingElement(datePickerElement);
        driver.findElement(By.xpath(datePickerElement)).click();
    }

    public void setRentalPeriod() {
        driver.findElement(rentalPeriod).click();
        String dropDownElement = ".//div[@class='Dropdown-option'][1]";
        waitForLoadingElement(dropDownElement);
        driver.findElement(By.xpath(dropDownElement)).click();
    }

    public void chooseBlackColor() {
        driver.findElement(blackColor).click();
    }

    public void setComment(String commentText) {
        driver.findElement(comment).sendKeys(commentText);
    }

    public void doOrder() {
        driver.findElement(orderButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(approvalButton));
        driver.findElement(approvalButton).click();
    }

    public boolean isDisplayedModalProcessedWindow() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(completeOrder));
        return driver.findElement(completeOrder).isDisplayed();
    }
}
