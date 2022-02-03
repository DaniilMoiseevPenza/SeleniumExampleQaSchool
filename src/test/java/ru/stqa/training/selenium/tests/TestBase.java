package ru.stqa.training.selenium.tests;

import org.junit.jupiter.api.BeforeAll;
import ru.stqa.training.selenium.app.Application;

public class TestBase {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public static Application app;

    @BeforeAll
    static void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    app.quit();
                    app = null;
                }));
    }
}
