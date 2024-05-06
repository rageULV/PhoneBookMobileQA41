package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class SplashScreen extends BaseScreen{
    public SplashScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/version_text']")
    // tam bila tabulyatsiya(/t), tak shto nada bilo udalit i postavit (='*****')
    MobileElement versionText;

    public String getCurrentVersion(){
        versionText.click();
        return versionText.getText();
    }
    public AuthenticationScreen switchToAuthScreen(){
        return new AuthenticationScreen(driver);
    }
}
