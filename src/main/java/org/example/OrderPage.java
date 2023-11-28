package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.cssSelector("div.Order_NextButton__1_rCA .Button_Button__ra12g");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setNameValue(String nameValue) {
        driver.findElement(name).sendKeys(nameValue);
    }

    public void setSurnameValue(String surnameValue) {
        driver.findElement(surname).sendKeys(surnameValue);
    }

    public void setAddressValue(String addressValue) {
        driver.findElement(address).sendKeys(addressValue);
    }

    public void setMetroValue(String metroStation) {
        this.driver.findElement(this.metro).sendKeys(new CharSequence[]{metroStation, Keys.DOWN, Keys.ENTER});
    }
    public void setTelephoneValue(String telephoneValue) {
        driver.findElement(telephone).sendKeys(telephoneValue);
    }

    public void clickOnNextButton() {
        driver.findElement(nextButton).click();
    }

    public void doOrder(String name, String surname, String metro, String address, String telephone) {
        this.setNameValue(name);
        this.setSurnameValue(surname);
        this.setMetroValue(metro);
        this.setAddressValue(address);
        this.setTelephoneValue(telephone);
        this.clickOnNextButton();
    }



}
