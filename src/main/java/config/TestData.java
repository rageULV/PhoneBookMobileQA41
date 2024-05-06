package config;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return new Object[][]{
                {"fakeUser1@gmail.com","fakePassword1"},// can use generators........ will be better
                {"fakeUser2@gmail.com","fakePassword2"}
        };
    }

}
