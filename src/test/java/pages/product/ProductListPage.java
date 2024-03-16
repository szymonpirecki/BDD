package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;
import pages.product.sorting.SortBy;
import pages.product.sorting.SortOrder;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductListPage extends BasePage {

    @FindBy(css = ".inventory_container")
    private WebElement productGrid;

    @FindBy(css = ".inventory_item")
    private List<WebElement> productMiniatures;

    @FindBy(css = ".product_sort_container")
    private WebElement sortSelect;

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductMiniatureComponent> getProducts() {
        return productMiniatures.stream()
                .map(p -> new ProductMiniatureComponent(driver, p))
                .toList();
    }

    public void sortProducts(SortBy sortBy, SortOrder sortOrder) {
        var sortingValue = "";
        if (sortBy.equals(SortBy.NAME)) {
            sortingValue = sortOrder.equals(SortOrder.ASC) ? "az" : "za";
        } else {
            sortingValue = sortOrder.equals(SortOrder.ASC) ? "lohi" : "hilo";
        }
        new Select(sortSelect).selectByValue(sortingValue);
    }

    public void checkIfProductGridIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(productGrid));
        if (!productGrid.isDisplayed()) {
            throw new NoSuchElementException("Products are not displayed");
        }
    }
}
