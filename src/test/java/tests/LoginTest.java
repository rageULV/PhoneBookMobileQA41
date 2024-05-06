package tests;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig{

    @Test
    public void loginTestPositive(){
        ContactListScreen listScreen = new SplashScreen(driver)
                .switchToAuthScreen()
                .fillEmailField("hurigosutalo@gmail.com")
                .fillPasswordField("!Hurigosutalo2552")
                .clickByLoginButton();
        Assert.assertTrue(listScreen.isContactListPresent());
    }
//logout
    @Test
    public void loginAndLogoutTest(){
        ContactListScreen listScreen = new SplashScreen(driver)
                .switchToAuthScreen()
                .fillEmailField("hurigosutalo@gmail.com")
                .fillPasswordField("!Hurigosutalo2552")
                .clickByLoginButton();

        AuthenticationScreen authenticationScreen = listScreen.logout();
        Assert.assertTrue(authenticationScreen.isItAuthenticationScreen());
    }

}
