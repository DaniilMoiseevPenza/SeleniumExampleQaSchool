package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(name="firstname")
    public WebElement firstnameInput;

    @FindBy(name="lastname")
    public WebElement lastnameInput;

    @FindBy(name="address1")
    public WebElement address1Input;

    @FindBy(name="postcode")
    public WebElement postcodeInput;

    @FindBy(name="city")
    public WebElement cityInput;

    @FindBy(name="email")
    public WebElement emailInput;

    @FindBy(name="phone")
    public WebElement phoneInput;

    @FindBy(name="password")
    public WebElement passwordInput;

    @FindBy(name="confirmed_password")
    public WebElement confirmedPasswordInput;

    @FindBy(name="create_account")
    public WebElement createAccountButton;

    public void selectCountry(String country) {
        driver.findElement(By.cssSelector("[id ^= select2-country_code]")).click();
        driver.findElement(By.cssSelector(
                String.format(".select2-results__option[id $= %s", country))).click();
    }

    public void selectZone(String zone) {
        wait.until((WebDriver d) -> d.findElement(
                By.cssSelector(String.format("select[name=zone_code] option[value=%s]", zone))));
        new Select(driver.findElement(By.name("zone_code"))).selectByValue(zone);
    }

    @FindBy(css = "table.dataTable tr.row")
    private List<WebElement> customerRows;
}
