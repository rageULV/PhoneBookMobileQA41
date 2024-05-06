package tests;

import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

import static config.AppiumConfig.driver;

public class RegistrationTests extends AppiumConfig {

    @Test
    public void registrationNewUserTest(){
        ContactListScreen contactListScreen = new SplashScreen(driver)
                .switchToAuthScreen()
                .fillEmailField(EmailGenerator.generateEmail(4,4,4))
                .fillPasswordField(PasswordStringGenerator.generateString())
                .clickByRegistrationButton();
        Assert.assertTrue(contactListScreen.isContactListPresent());
    }
    //reg negative
     @Test
    public void registrationNewUserNegative(){
        AuthenticationScreen authenticationScreen = new SplashScreen(driver)
                .switchToAuthScreen()
                .fillEmailField("aosifj@fiopjas.spoafj")
                .fillPasswordField("iosjfaoisf")
                .clickByRegistrationButton();
        Assert.assertTrue(authenticationScreen.isItAuthenticationScreen());
     }
}
