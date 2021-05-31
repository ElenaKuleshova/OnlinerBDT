package onliner.pages;

import framework.BaseForm;
import framework.elements.BaseElement;
import framework.elements.Label;
import org.openqa.selenium.By;

public class ElectronicaMenuPage extends BaseForm {
    private static final By NAVIGATION_LIST_LOCATOR = By.xpath("//div[@class='catalog-navigation-list__wrapper']");
    private static final String formName = ElectronicaMenuPage.class.getName();
    private String asideItemTitle = "//div[@class='catalog-navigation-list__aside-title'][contains(text(),'%s')]";
    private String dropdownTitle = "//span[@class='catalog-navigation-list__dropdown-title'][contains(text(),'%s')]";

    public ElectronicaMenuPage(){
        super(NAVIGATION_LIST_LOCATOR, formName);
    }
    public void navigateMenuAsideItem(String menuAsideTitle){
        Label lblAsideMenuItem = new Label(By.xpath(String.format(asideItemTitle,menuAsideTitle)));
        lblAsideMenuItem.moveMouse();
    }

    public void clickMenuDropdownItem(String menuDropdownTitle){
        Label lblDropdownMenuItem = new Label(By.xpath(String.format(dropdownTitle, menuDropdownTitle)));
        lblDropdownMenuItem.clickAndWait();
}
public void clickMenuDropdownItem(String menuAsideTitle, String menuDropdownTitle){
        navigateMenuAsideItem(menuAsideTitle);
        clickMenuDropdownItem(menuDropdownTitle);
}
}
