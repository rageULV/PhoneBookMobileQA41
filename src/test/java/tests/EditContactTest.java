package tests;

import config.AppiumConfig;
import helpers.AddressGenerator;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import jdk.jfr.Description;
import models.ContactModel;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.EditScreen;
import screens.SplashScreen;

public class EditContactTest extends AppiumConfig {
    @BeforeTest
    public void precondition(){
        ContactListScreen listScreen = new SplashScreen(driver)
                .switchToAuthScreen()
                .fillEmailField("hurigosutalo@gmail.com")
                .fillPasswordField("!Hurigosutalo2552")
                .clickByLoginButton();
    }
    @Test
    public void editContactTest(){

        ContactModel contact = new ContactModel(NameAndLastNameGenerator.generateName()
                , NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(3,3,3),
                AddressGenerator.generateAddress(),"Descr");

        ContactListScreen listScreen = new ContactListScreen(driver);
        listScreen.editAContact("Bill Foster","6344773698782");

        EditScreen editScreen = new EditScreen(driver);

        editScreen.makeSureWeAreLoaded();
        ContactModel contactToSave = editScreen.saveContactForNow();

        editScreen.fillTheFieldsWithNewContact(contact).saveEditedContact();

        Assert.assertNotEquals(contact,contactToSave);

    }
    @Test
    public void editContactMailPositive(){
        String text = "updatedMail@mail.com";

        Assert.assertTrue(new ContactListScreen(driver).
                editOneContact().
                editEmailField(text).
                submitChanges().isContactContainsText(text));
    }
}


