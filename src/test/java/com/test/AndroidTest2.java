package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class AndroidTest2 {
    @Test
    public void androidLaunchTest() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName("Pixel 6 Pro API 34");
        options.setApp(System.getProperty("user.dir") + "\\app\\Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        WebElement openMenu= driver.findElement(AppiumBy.accessibilityId("open menu"));
        tap(driver,openMenu);
    }

    @Test
    public void doubleTapTest() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName("Pixel 6 Pro API 34");
        options.setApp(System.getProperty("user.dir") + "\\app\\Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        WebElement openMenu= driver.findElement(AppiumBy.accessibilityId("open menu"));
        doubleTap(driver,openMenu);
    }
    private void tap (AndroidDriver driver, WebElement element){

        Point location = element.getLocation();
        Dimension size= element.getSize();

        Point centerOfElement= getCenterOfElement(location,size);

        PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence= new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), centerOfElement ))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(200)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }

    private void doubleTap (AndroidDriver driver, WebElement element){

        Point location = element.getLocation();
        Dimension size= element.getSize();

        Point centerOfElement= getCenterOfElement(location,size);

        PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence= new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), centerOfElement ))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(100)))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                ;

        driver.perform(Collections.singletonList(sequence));

    }
    private Point getCenterOfElement (Point location, Dimension size){
    return new Point(location.getX() + size.getWidth()/2,
            location.getY()+size.getHeight()/2);
    }
}