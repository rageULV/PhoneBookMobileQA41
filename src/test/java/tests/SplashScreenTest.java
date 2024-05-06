package tests;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class SplashScreenTest extends AppiumConfig {

    @Test
    public void splashScreenCheckVersion(){
        String resultVersion = new SplashScreen(driver).getCurrentVersion();
        Assert.assertTrue(resultVersion.contains("Version 1.0.0"));
        System.out.println("the version is "+resultVersion);
    }
}