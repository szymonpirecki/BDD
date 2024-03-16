package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class ProductMiniatureComponent extends BasePage {

    @FindBy(css = ".inventory_item_name")
    private WebElement productName;

    @FindBy(css = ".inventory_item_price")
    private WebElement productPrice;

    public ProductMiniatureComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    public double getPrice() {
        var value = productPrice.getText().replaceAll("\\$", "").trim();
        return Double.parseDouble(value);
    }
}