package ru.stqa.training.selenium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerRegistrationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("ru.stqa.training.selenium.DataProvider#validCustomers")
    public void canRegisterCustomer(Customer customer) {
        driver.get("http://localhost/litecart/admin");
        if (driver.findElements(By.id("box-login")).size() > 0) {
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
        }

        driver.get("http://localhost/litecart/admin/?app=customers&doc=customers");
        Set<String> oldIds = driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());

        driver.get("http://localhost/litecart/en/create_account");
        driver.findElement(By.name("firstname")).sendKeys(customer.getFirstname());
        driver.findElement(By.name("lastname")).sendKeys(customer.getLastname());
        driver.findElement(By.name("address1")).sendKeys(customer.getAddress());
        driver.findElement(By.name("postcode")).sendKeys(customer.getPostcode());
        driver.findElement(By.name("city")).sendKeys(customer.getCity());

        driver.findElement(By.cssSelector("[id ^= select2-country_code]")).click();
        driver.findElement(By.cssSelector(
                String.format(".select2-results__option[id $= %s", customer.getCountry()))).click();
        wait.until((WebDriver d) -> d.findElement(
                By.cssSelector(String.format("select[name=zone_code] option[value=%s]", customer.getZone()))));
        new Select(driver.findElement(By.name("zone_code"))).selectByValue(customer.getZone());

        driver.findElement(By.name("email")).sendKeys(customer.getEmail());
        driver.findElement(By.name("phone")).sendKeys(customer.getPhone());
        driver.findElement(By.name("password")).sendKeys(customer.getPassword());
        driver.findElement(By.name("confirmed_password")).sendKeys(customer.getPassword());
        driver.findElement(By.name("create_account")).click();

        driver.get("http://localhost/litecart/admin/?app=customers&doc=customers");
        Set<String> newIds = driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());

        assertTrue(newIds.containsAll(oldIds));
        assertEquals(newIds.size(), oldIds.size() + 1);
    }

}
