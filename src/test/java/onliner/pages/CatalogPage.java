package onliner.pages;

import framework.BaseForm;
import framework.elements.Label;
import org.openqa.selenium.By;

public class CatalogPage extends BaseForm {
    private static final By CATALOG_PAGE_TITLE = By.className("catalog-navigation__title");
    private static final String formName = CatalogPage.class.getName();
    private String catalogNavigationItem = "//span[contains(text(),'%s')]";
    private String catalogAsideItemTitle = "//div[@class='catalog-navigation-list__aside-title'][contains(text(),'%s')]";
    private String catalogDropdownTitle = "//span[@class='catalog-navigation-list__dropdown-title'][contains(text(),'%s')]";

    public CatalogPage(){
        super (CATALOG_PAGE_TITLE, formName);
    }

    public void navigateCatalogItem(String catalogItem){
        Label lblCatalogNavigation = new Label(By.xpath(String.format(catalogNavigationItem,catalogItem)));
        lblCatalogNavigation.clickAndWait();
    }

    public void selectCatalogMenuDropdownItem(String menuAsideTitle, String menuDropdownTitle){
        Label lblAsideMenuItem = new Label(By.xpath(String.format(catalogAsideItemTitle,menuAsideTitle)));
        lblAsideMenuItem.moveMouse();
        Label lblDropdownMenuItem = new Label(By.xpath(String.format(catalogDropdownTitle, menuDropdownTitle)));
        lblDropdownMenuItem.clickAndWait();
    }

}
