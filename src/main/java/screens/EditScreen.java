package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.ContactModel;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditScreen extends BaseScreen{
    public EditScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement Title;
    @FindBy(id = "com.sheygam.contactapp:id/updateBtn")
    MobileElement updateBtn;

    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    MobileElement nameField;
    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    MobileElement lastNameField;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement emailField;
    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    MobileElement phoneField;
    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    MobileElement addressField;
    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    MobileElement descField;


    public void makeSureWeAreLoaded(){
        waitForAnElement(updateBtn);
    }
    public ContactModel saveContactForNow(){
        ContactModel contact = new ContactModel();
        contact.setName(nameField.getText());
        contact.setLastName(lastNameField.getText());
        contact.setEmail(emailField.getText());
        contact.setPhone(phoneField.getText());
        contact.setAddress(addressField.getText());
        contact.setDescription(descField.getText());
        return contact;
    }
    public EditScreen fillTheFieldsWithNewContact(ContactModel contact) {
        nameField.sendKeys(contact.getName());
        lastNameField.sendKeys(contact.getLastName());
        emailField.sendKeys(contact.getEmail());
        phoneField.sendKeys(contact.getPhone());
        addressField.sendKeys(contact.getAddress());
        descField.sendKeys(contact.getDescription());
        return this;
    }
    public void saveEditedContact(){
        updateBtn.click();
    }
    public ContactListScreen submitChanges() {
        updateBtn.click();
        return new ContactListScreen(driver);
    }

    public EditScreen editEmailField(String email){
        waitForAnElement(updateBtn);
        emailField.clear();
        emailField.sendKeys(email);
        return this;

    }
}
