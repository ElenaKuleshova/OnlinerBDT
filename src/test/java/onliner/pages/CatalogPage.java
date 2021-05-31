package onliner.pages;

import framework.BaseForm;
import framework.elements.Label;
import org.openqa.selenium.By;

public class CatalogPage extends BaseForm {
    private static final By CATALOG_PAGE_TITLE = By.className("catalog-navigation__title");
    private static final String formName = CatalogPage.class.getName();
    private String catalogNavigationItem = "//span[contains(text(),'%s')]";

    public CatalogPage(){
        super (CATALOG_PAGE_TITLE, formName);
    }

    public void navigateCatalogItem(String catalogItem){
        Label lblCatalogNavigation = new Label(By.xpath(String.format(catalogNavigationItem,catalogItem)));
        lblCatalogNavigation.clickAndWait();
    }

}
