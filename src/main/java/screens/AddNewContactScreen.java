package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.ContactModel;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    MobileElement inputNameField;
    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    MobileElement inputLastNameField;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement inputEmailField;
    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    MobileElement inputPhoneField;
    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    MobileElement inputAddressField;
    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    MobileElement inputDescriptionField;
    @FindBy(id = "com.sheygam.contactapp:id/createBtn")
    MobileElement createButton;

    public AddNewContactScreen fillTheForm(ContactModel contactModel){
        waitForAnElement(createButton);
        inputNameField.sendKeys(contactModel.getName());
        driver.hideKeyboard();
        inputLastNameField.sendKeys(contactModel.getLastName());
        driver.hideKeyboard();
        inputEmailField.sendKeys(contactModel.getEmail());
        driver.hideKeyboard();
        inputPhoneField.sendKeys(contactModel.getPhone());
        driver.hideKeyboard();
        inputAddressField.sendKeys(contactModel.getAddress());
        driver.hideKeyboard();
        inputDescriptionField.sendKeys(contactModel.getDescription());
        driver.hideKeyboard();
        return this;
    }
    public ContactListScreen createContact(){
        createButton.click();
        return new ContactListScreen(driver);
    }


}