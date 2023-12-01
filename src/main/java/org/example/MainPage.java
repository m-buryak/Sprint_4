package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private final By orderTopButton = By.cssSelector("div.Header_Nav__AGCXC button.Button_Button__ra12g");
    private final By orderStatus = By.cssSelector("div.Header_Nav__AGCXC button.Header_Link__1TAG7");
    private final By orderBottomButton = By.cssSelector("div.Home_FinishButton__1_cWm button.Button_Button__ra12g");
    private final By accordions = By.xpath(".//div[@class='accordion__item']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(url);
    }

    public List<WebElement> getAccordionItems() {
        return driver.findElements(accordions);
    }
    public String clickOnAccordionItem(String accordionText) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(accordions));
        List<WebElement> accordionItems = getAccordionItems();
        for (WebElement accordionItem : accordionItems) {
            if (accordionItem.getText().equals(accordionText)) {
                accordionItem.click();
                new WebDriverWait(driver, 3)
                        .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//div[@class='accordion__panel' and not(@hidden)]"))));
                return getAccordionText(accordionItem);
            }
        }
        return "";
    }

    public void clickOnTopOrderButton() {
        WebElement orderTopButtonElement = driver.findElement(orderTopButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", orderTopButtonElement);
        orderTopButtonElement.click();
    }

    public void clickOnBottomOrderButton() {
        WebElement orderBottomButtonElement = driver.findElement(orderBottomButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", orderBottomButtonElement);
        orderBottomButtonElement.click();
    }

    public String getAccordionText(WebElement accordionItem) {
        return accordionItem.getText();
    }

}
