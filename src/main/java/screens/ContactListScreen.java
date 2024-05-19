package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.ContactModel;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactListScreen extends BaseScreen{


    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement titleText;
    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOptionsBtn;
    @FindBy(xpath = "//*[@text='Logout']")
    MobileElement logoutBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addButton;


    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> rowName;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> rowPhone;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contacts;
    @FindBy(id = "android:id/button1")
    MobileElement yesButton;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt']")
    MobileElement emptyListMessage;

    String phoneNumber;


    public AuthenticationScreen logout(){
        moreOptionsBtn.click();
        logoutBtn.click();
        return new AuthenticationScreen(driver);
    }
    public boolean isContactListPresent(){
        return isElementPresent(titleText,"Contact list");
    }
    public AddNewContactScreen openNewContactForm() {
        waitForAnElement(addButton);
        addButton.click();
        return new AddNewContactScreen(driver);
    }
    public boolean checkContainsText(List<MobileElement> list, String text) {
        for (MobileElement mobileElement : list) {
            if (mobileElement.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }
    public boolean isContactAdded(ContactModel contact) {
        boolean checkName = checkContainsText(rowName, contact.getName());
        boolean checkPhone = checkContainsText(rowPhone, contact.getPhone());
        return checkName && checkPhone;
    }
    public ContactListScreen removeAContact(){
        waitForAnElement(addButton);
        MobileElement contact = contacts.get(0);

        phoneNumber = rowPhone.get(0).getText();

        Rectangle rectangle = contact.getRect();
        int xStart = rectangle.getX()+rectangle.getWidth()/8;
        int y = rectangle.getY()+rectangle.getHeight()/2;
        int xEnd = xStart + rectangle.getWidth()*6/8;

        new TouchAction<>(driver).longPress(PointOption.point(xStart,y))
                .moveTo(PointOption.point(xEnd,y))
                .release()
                .perform();

        if(isElementPresent(yesButton,"YES")){
            yesButton.click();
        }
        return this;
    }
    public ContactListScreen editAContact(String name,String phoneNumber){
        waitForAnElement(addButton);
        List<MobileElement> contacts = this.contacts;

        for (MobileElement contact : contacts) {
            String contactName = contact.findElement(By.id("com.sheygam.contactapp:id/rowName")).getText();
            String contactPhoneNumber = contact.findElement(By.id("com.sheygam.contactapp:id/rowPhone")).getText();

            if (contactName.equals(name) && contactPhoneNumber.equals(phoneNumber))
            {
                Rectangle rectangle = contact.getRect();
                int xEnd = rectangle.getX()+rectangle.getWidth()/8;
                int y = rectangle.getY()+rectangle.getHeight()/2;
                int xStart = xEnd + rectangle.getWidth()*6/8;

                new TouchAction<>(driver).longPress(PointOption.point(xStart,y))
                        .moveTo(PointOption.point(xEnd,y))
                        .release()
                        .perform();
                break;
            }
        }
        return this;
    }
    public boolean isContactRemoved(){
        return !rowPhone.contains(phoneNumber);
    }
    public ContactListScreen removeAllContacts(){
        waitForAnElement(addButton);
        while (contacts.size()>0){
            removeAContact();
        }
        return this;
    }
    public boolean isNoContactsMessage(){
        return isElementPresent(emptyListMessage, "No Contacts");
    }

    public EditScreen editOneContact() {
        waitForAnElement(addButton);
        MobileElement contact = contacts.get(0);

        Rectangle rectangle = contact.getRect();
        int xStart = rectangle.getX() + rectangle.getWidth() * 7 / 8;
        int xEnd = xStart - rectangle.getWidth() * 6 / 8;
        int y = rectangle.getY() + rectangle.getHeight() / 2;

        new TouchAction<>(driver)
                .longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();
        return new EditScreen(driver);
    }
    public boolean isContactContainsText(String text){
        contacts.get(0).click();
        ContactModel contact = new ViewContactScreen(driver).viewContactObject();
        driver.navigate().back();
        return contact.toString().contains(text);
    }
    //--------------------------------------------------------------------------
    public ContactListScreen scrollingDown(){
        waitForAnElement(addButton);
        MobileElement contact = contacts.get(contacts.size()-1);
        Rectangle rectangle = contact.getRect();
        int x = rectangle.getX()+rectangle.getWidth()/2;
        int y = rectangle.getY()+rectangle.getHeight()/2;

        new TouchAction<>(driver)
                .longPress(PointOption.point(x,y))
                .moveTo(PointOption.point(x, 0))
                .release()
                .perform();
        return this;
    }
    public boolean isTheEndOfTheList(){
        waitForAnElement(addButton);
        String beforeScroll = getLastContact();
        scrollingDown();
        String afterScroll = getLastContact();
        return beforeScroll.equals(afterScroll);
    }
    private String getLastContact(){
        return rowName.get(rowName.size()-1).getText()
                +" "+
                rowPhone.get(rowPhone.size()-1).getText();
    }
    public boolean isContactAddedScroll(ContactModel contact) {
        boolean result = false;
        while(!result && !isTheEndOfTheList()){
            boolean checkName = checkContainsText(rowName, contact.getName());
            boolean checkPhone = checkContainsText(rowPhone, contact.getPhone());
            result = checkName && checkPhone;
            if(!result){scrollingDown();}
        }
        return result;
    }
}
