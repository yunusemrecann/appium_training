import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MiscellanousAppiumActions extends BaseTest {

    @Test
    public void miscellanous() throws InterruptedException {

        //  adb shell dumpsys window | find "mCurrentFocus" -> windows
        // adb shell dumpsys window | grep -W 'mCurrentFocus' -> MAC

        // App package & App activity
        startActivity("io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies");
        Thread.sleep(3000);
        driver.findElement(By.id("android:id/checkbox")).click();
        Thread.sleep(3000);
        DeviceRotation landScape = new DeviceRotation(0,0,90);
        driver.rotate(landScape);
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle, "WiFi settings");
        // copy paste
        // copy to clipboard and paste it clipboard
        driver.setClipboardText("Yunus Wifi");

        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));

    }
}
