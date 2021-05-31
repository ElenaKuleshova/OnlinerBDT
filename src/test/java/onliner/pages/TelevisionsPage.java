package onliner.pages;

import framework.BaseForm;
import framework.elements.Button;
import framework.elements.CheckBox;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TelevisionsPage extends BaseForm {
    private static final By TV_PAGE_TITLE = By.xpath("//h1[contains(text(), 'Телевизоры')]");
    private static final String formName = TelevisionsPage.class.getName();
    private final String checkboxFilterLocator = "//div[@class='schema-filter__label' and contains(.,'%s')]/following-sibling::div//span[contains(text(),'%s')]";
    private final String inputFilter = "//div[@class='schema-filter__label' and contains(.,'%s')]/following-sibling::div//input[@placeholder='%s']";
    private final Button btnFilterResult = new Button(By.className("schema-filter-button__inner-container"));
    private final Label lblProductTitle = new Label(By.className("schema-product__title"));
    private final Label lblProductDescription = new Label(By.className("schema-product__description"));
    private final Label lblProductPrice = new Label(By.xpath("//div[@class='schema-product__price']//span"));
    private String resultButtonText = null;

    public TelevisionsPage() {
        super(TV_PAGE_TITLE, formName);
    }

    public void selectCheckboxFilter(String filterTitle, String filterValue) {
        resultButtonText = btnFilterResult.getText();
        CheckBox chbFilterOption = new CheckBox(By.xpath(String.format(checkboxFilterLocator, filterTitle, filterValue)));
        chbFilterOption.clickElementJS();
        btnFilterResult.waitInvisibilityText(resultButtonText);
    }

    public void setInputFilter(String filterTitle, String placeholder, String inputValue) {
        resultButtonText = btnFilterResult.getText();
        TextBox txbInputFilter = new TextBox(By.xpath(String.format(inputFilter, filterTitle, placeholder)));
        txbInputFilter.sendKeys(inputValue);
        btnFilterResult.waitInvisibilityText(resultButtonText);
    }

    public boolean isEachProductHasTitleWithFilterValue(String filterValue){
        List<WebElement> elements = lblProductTitle.getElements();
        for (WebElement element : elements) {
            if (!element.getText().contains(filterValue)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEachProductHasPriceByFilterValue(String priceValue){
        List<WebElement> elements = lblProductPrice.getElements();
        for (WebElement element : elements){
            String str = element.getText();
            str = str.substring(0,str.length()-3);
            str = str.replace(",", ".");
            double actualPrice = Double.parseDouble(str);
            if (actualPrice > Integer.parseInt(priceValue)){
                return false;
            }
        }
        return true; }

    public boolean isEachProductContainsFilterValue(String filterValue) {
        List<WebElement> elements = lblProductDescription.getElements();
        for (WebElement element : elements) {
            if (!element.getText().contains(filterValue)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEachProductWithinMinAndMaxRange(String minRange, String maxRange){
        List<WebElement> elements = lblProductDescription.getElements();
        for (WebElement element : elements) {
            String str = element.getText();
            str = str.substring(0,4);
            str = str.replace("\"", "");
            double actualDiagonal = Double.parseDouble(str);
            if (!(actualDiagonal>=Integer.parseInt(minRange) && actualDiagonal<=Integer.parseInt(maxRange))) {return false;}
        }
        return true; }
}
