package ru.stqa.training.selenium.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.training.selenium.model.Customer;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerRegistrationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("ru.stqa.training.selenium.tests.DataProviders#validCustomers")
    public void canRegisterCustomer(Customer customer) {
        Set<String> oldIds = app.getCustomerIds();

        app.registerNewCustomer(customer);

        Set<String> newIds = app.getCustomerIds();

        assertTrue(newIds.containsAll(oldIds));
        assertEquals(newIds.size(), oldIds.size() + 1);
    }
}