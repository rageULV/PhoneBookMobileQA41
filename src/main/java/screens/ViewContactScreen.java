package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.ContactModel;
import org.openqa.selenium.support.FindBy;
import screens.BaseScreen;

public class ViewContactScreen extends BaseScreen {
    public ViewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(id = "com.sheygam.contactapp:id/nameTxt")
    MobileElement nameText;
    @FindBy(id = "com.sheygam.contactapp:id/lastNameTxt")
    MobileElement lastNameText;
    @FindBy(id = "com.sheygam.contactapp:id/emailTxt")
    MobileElement emailText;
    @FindBy(id = "com.sheygam.contactapp:id/phoneTxt")
    MobileElement phoneText;
    @FindBy(id = "com.sheygam.contactapp:id/addressTxt")
    MobileElement addressText;
    @FindBy(id = "com.sheygam.contactapp:id/descTxt")
    MobileElement descriptionText;

    public ContactModel viewContactObject(){
        ContactModel contact = new ContactModel(nameText.getText(),
                lastNameText.getText(),
                emailText.getText(),
                phoneText.getText(),
                addressText.getText(),
                descriptionText.getText());
        return contact;
    }


}