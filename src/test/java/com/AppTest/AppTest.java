package com.AppTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppTest {
    private AndroidDriver driver;
    private WebDriverWait wait;

    // App details
    private static final String APP_PACKAGE = "flipboard.app";
    // Chỉ tên activity (không kèm package app)
    private static final String APP_ACTIVITY = "flipboard.activities.LaunchActivityAlias";
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("11")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Android Emulator")
                .setApp("F://Testcase//serenity-appium-java//src//test//resources//app//flipboard.apk")
                .setNoReset(false)
                .setAutoGrantPermissions(true)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)  // Thay vì setAppWaitActivity
                //.setAppWaitActivity(APP_ACTIVITY) // Có thể thêm nếu cần đợi nhiều activity

                ;

        // KHỞI TẠO biến toàn cục, không tạo biến cục bộ nữa
        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("Test setup completed successfully");
    }

    @Test(priority = 1, description = "Verify app launches successfully")
    public void testAppLaunch() throws InterruptedException {
        System.out.println("Starting testAppLaunch...");

//        WebElement appLogoAndName = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                AppiumBy.accessibilityId("Edit Home")));
        WebElement appLogoAndName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.ImageView[@resource-id='flipboard.app:id/first_launch_cover_logo']")));

        Assert.assertTrue(appLogoAndName.isDisplayed(), "App Logo should be visible");
        System.out.println("App launched successfully - App Logo is visible");
        Thread.sleep(10000); // giữ app chạy 10 giây trước khi tắt driver
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                System.out.println("Closing driver...");
                //driver.quit();
                System.out.println("Driver closed successfully");
            } catch (Exception e) {
                System.err.println("Error closing driver: " + e.getMessage());
            }
        }
    }
}




//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.AfterClass;
//
//@BeforeClass
//public void setUp() throws MalformedURLException {
//    // Mở app 1 lần cho cả class
//}
//
//@AfterClass
//public void tearDown() {
//    if (driver != null) {
//        driver.quit();
//    }
//}
//
//@Test
//public void testCase1() {
//    // Test 1
//}
//
//@Test
//public void testCase2() {
//    // Test 2
//}

