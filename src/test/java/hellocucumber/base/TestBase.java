package hellocucumber.base;

import org.openqa.selenium.WebDriver;

public class TestBase {

    protected WebDriver driver;

    protected String url = "https://www.saucedemo.com/";
    protected String password = "secret_sauce";
    protected String login_locked_out_user = "locked_out_user";
    protected String login_standard_user = "standard_user";
    protected String warning_msg_locked_out_user = "Epic sadface: Sorry, this user has been locked out.";

}
