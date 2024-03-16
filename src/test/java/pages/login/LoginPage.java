package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(css = "#user-name")
    private WebElement usernameInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "#login-button")
    private WebElement submitBtn;

    @FindBy(css = ".error-message-container")
    private WebElement errorMsgLbl;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage provideCredentials(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        return this;
    }

    public void clickLogInButton() {
        submitBtn.click();
    }

    public String getErrorMsg() {
        return wait.until(ExpectedConditions.visibilityOf(errorMsgLbl)).getText();
    }
}