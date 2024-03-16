package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    public BasePage(WebDriver driver) {
        init(driver);
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver, WebElement parent){
        init(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(parent), this);
    }

    private void init(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }
}
