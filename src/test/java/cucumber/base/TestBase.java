package cucumber.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

public class TestBase {

    protected WebDriver driver;
    protected String url = "https://www.saucedemo.com/";
    protected String password = "secret_sauce";
    protected String login_locked_out_user = "locked_out_user";
    protected String login_standard_user = "standard_user";

    protected static String getRandomString(){
        return RandomStringUtils.random(5, true, true);
    }

}
