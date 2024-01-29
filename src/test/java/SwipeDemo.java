import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SwipeDemo extends BaseTest {

    @Test
    public void swipeDemoTest() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='1. Photos']")).click();
        WebElement firstImage = driver.findElement(By.xpath("//android.widget.ImageView[1]"));
        Assert.assertEquals(firstImage.getAttribute("focusable"),"true");

        //swipe
        swipeAction(firstImage,"left");

        Assert.assertEquals(firstImage.getAttribute("focusable"),"false");


        WebDriver webDriver = new ChromeDriver();
    }
}
