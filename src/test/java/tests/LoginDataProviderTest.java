package tests;

import config.AppiumConfig;
import config.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginDataProviderTest extends AppiumConfig {


    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void loginTestNegative(String email,String password){
        AuthenticationScreen authScreen = new SplashScreen(driver)
                .switchToAuthScreen()
                .fillEmailField(email)
                .fillPasswordField(password)
                .clickByLoginButton();
        Assert.assertTrue(authScreen.isItAuthenticationScreen());
    }
}
