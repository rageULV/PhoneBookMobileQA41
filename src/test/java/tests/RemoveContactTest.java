package tests;

import config.AppiumConfig;
import helpers.AddressGenerator;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import models.ContactModel;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RemoveContactTest extends AppiumConfig {

    @BeforeTest
    public void precondition(){
        ContactListScreen listScreen = new SplashScreen(driver)
                .switchToAuthScreen()
                .fillEmailField("hurigosutalo@gmail.com")
                .fillPasswordField("!Hurigosutalo2552")
                .clickByLoginButton();
    }
    @Test
    public void removeContact(){
        ContactListScreen listScreen = new ContactListScreen(driver);

        ContactModel contact = new ContactModel(NameAndLastNameGenerator.generateName()
                , NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(3,3,3),
                AddressGenerator.generateAddress(),"Descr");

        listScreen.openNewContactForm().fillTheForm(contact).createContact();

        Assert.assertTrue(listScreen.removeAContact().isContactRemoved());

    }
    @Test
    public void removeAllContacts(){
        ContactListScreen listScreen = new ContactListScreen(driver);
        Assert.assertTrue(listScreen.removeAllContacts().isNoContactsMessage());

    }

    //editContact
}
