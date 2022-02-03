package ru.stqa.training.selenium.tests;

import ru.stqa.training.selenium.model.Customer;

public class DataProviders {
    public static Object[][] validCustomers() {
        return new Object[][] {
                { Customer.newEntity()
                        .withFirstname("Adam").withLastname("Smith").withPhone("+0123456789")
                        .withAddress("Hidden Place").withPostcode("12345").withCity("New City")
                        .withCountry("US").withZone("KS")
                        .withEmail("adam"+System.currentTimeMillis()+"@smith.me")
                        .withPassword("qwerty").build() },
                /* ... */
        };
    }
}