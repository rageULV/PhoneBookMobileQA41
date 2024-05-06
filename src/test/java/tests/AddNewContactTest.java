package tests;

import config.AppiumConfig;
import helpers.AddressGenerator;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import models.ContactModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTest extends AppiumConfig {

    @Test
    public void addNewContact(){
        new SplashScreen(driver).switchToAuthScreen()
                .fillEmailField("hurigosutalo@gmail.com")
                .fillPasswordField("!Hurigosutalo2552")
                .clickByLoginButton();
        ContactModel contact = new ContactModel(NameAndLastNameGenerator.generateName()
                , NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(3,3,3),
                AddressGenerator.generateAddress(),"Descr");
        Assert.assertTrue(new ContactListScreen(driver)
                .openNewContactForm()
                .fillTheForm(contact)
                .createContact()
                .isContactAdded(contact));
    }

}