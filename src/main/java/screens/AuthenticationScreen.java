package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AuthenticationScreen extends BaseScreen{
    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement titleText;
    @FindBy(id = "android:id/alertTitle")
    MobileElement ErrorTitle;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement inputEmailField;
    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    MobileElement inputPasswordField;
    @FindBy(id = "com.sheygam.contactapp:id/regBtn")
    MobileElement regBtn;
    @FindBy(id = "com.sheygam.contactapp:id/loginBtn")
    MobileElement loginBtn;
    @FindBy(id = "android:id/button1")
    MobileElement ErrorAcceptBtn;

    public AuthenticationScreen fillEmailField(String email){
        waitForAnElement(inputEmailField);
        inputEmailField.sendKeys(email);
        return this;
    }
    public AuthenticationScreen fillPasswordField(String password){
        inputPasswordField.sendKeys(password);
        return this;
    }
    public <T extends BaseScreen>T clickByLoginButton(){
        loginBtn.click();
        List<MobileElement> list =
                driver.findElements(By.xpath("//*[@resource-id='android:id/alertTitle']"));

        if(list.size()>0){
            driver.findElement(By.id("android:id/button1")).click();
            return (T)new AuthenticationScreen(driver);
        }
        return (T)new ContactListScreen(driver);
    }
    public boolean isItAuthenticationScreen(){
        return titleText.isDisplayed();
    }

    public <T extends BaseScreen> T clickByRegistrationButton(){
        regBtn.click();
        List<MobileElement> list = driver.findElements(By.id("android:id/alertTitle"));
        if(list.size()>0){
            driver.findElement(By.id("android:id/button1")).click();
            return (T)new AuthenticationScreen(driver);
        }
        return (T)new ContactListScreen(driver);
    }
    public boolean isErrorDisplayed(){
        return ErrorTitle.isDisplayed();
    }
    public void AcceptError(){
        ErrorAcceptBtn.click();
    }


}
