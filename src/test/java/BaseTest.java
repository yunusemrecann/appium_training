import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService serviceBuilder;

    @BeforeClass
    public void configureAppium() throws MalformedURLException {

        // Appium code -> Appium server -> Mobile

        serviceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\yunus\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723).build();
        serviceBuilder.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("YunusPhone");
        options.setApp("C:\\Users\\yunus\\Downloads\\appium_training\\src\\test\\resources\\ApiDemos-debug.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void longPressAction(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",
                ((RemoteWebElement)element).getId(),
                "duration", 2000));((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",
                ((RemoteWebElement)element).getId(),
                "duration", 2000));
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {


            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        } while (canScrollMore);
    }

    public void swipeAction(WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.20
        ));
    }
    @AfterClass
    public void tearDown() {

        driver.quit();
        serviceBuilder.stop();
    }
}
