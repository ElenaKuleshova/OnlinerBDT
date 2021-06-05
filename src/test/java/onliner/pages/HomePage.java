package onliner.pages;

import framework.BaseForm;
import framework.BaseTest;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseForm {
    private static final By ONLINER_LOGO = By.className("b-top-logo");
    private static final String formName = HomePage.class.getName();

    public HomePage() {
        super(ONLINER_LOGO,formName );
    }

    private final String onlinerSection= "%s";

    public void navigateSection(String mainSectionName){
    Label lblOnlinerSection = new Label(By.linkText(String.format(onlinerSection, mainSectionName)));
    lblOnlinerSection.clickAndWait();
    }
}
