package onliner.stepdefinitions;

import framework.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onliner.pages.CatalogPage;
import onliner.pages.ElectronicaMenuPage;
import onliner.pages.OnlinerHomePage;
import onliner.pages.TelevisionsPage;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class SearchTVsUsingFiltersSteps extends BaseTest {
    private OnlinerHomePage onlinerHomePage;
    private CatalogPage catalogPage;
    private ElectronicaMenuPage electronicaMenuPage;
    private TelevisionsPage televisionsPage;
    private List<List<String>> filtersData;

    @Given("User is on the Onliner home page")
    public void userVisitOnliner(){
        initializeWebdriver();
        onlinerHomePage = new OnlinerHomePage();
    }
    @And("User opens {string} page")
    public void userNavigateToOnlinerSection(String menuSection){
        onlinerHomePage.navigateSection(menuSection);
    }

    @And("User selects {string} from catalog main page")
    public void userNavigateToCatalogPageSection(String catalogItem){
        catalogPage = new CatalogPage();
        catalogPage.navigateCatalogItem(catalogItem);
    }

    @And("User selects {string} and open {string} from Electronica submenu")
    public void userNavigateToTelevisionsPageFromElectronicaSubmenu(String asideItem, String dropDownItem){
        electronicaMenuPage = new ElectronicaMenuPage();
        electronicaMenuPage.clickMenuDropdownItem(asideItem,dropDownItem);
    }

    @When("User selects filters on Televisions page")
    public void userSelectFiltersOnTelevisionsPage(List<List<String>> userFilters){
        this.filtersData = userFilters;

        televisionsPage = new TelevisionsPage();
        televisionsPage.selectCheckboxFilter(filtersData.get(0).get(0), filtersData.get(0).get(1));
        televisionsPage.selectCheckboxFilter(filtersData.get(1).get(0), filtersData.get(1).get(1));
        televisionsPage.selectCheckboxFilter(filtersData.get(1).get(0), filtersData.get(1).get(2));
        televisionsPage.selectCheckboxFilter(filtersData.get(2).get(0), filtersData.get(2).get(1));
        televisionsPage.setInputFilter(filtersData.get(3).get(0), filtersData.get(3).get(1), filtersData.get(3).get(2));
    }
@Then("search results satisfy all entered filters")
    public void searchResultsSatisfyAllFilterValues(){
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(televisionsPage.isEachProductHasTitleWithFilterValue(filtersData.get(0).get(1)),
        String.format("Not all Search Results contain provided Producer %s", filtersData.get(0).get(1)));
    softAssert.assertTrue(televisionsPage.isEachProductWithinMinAndMaxRange(filtersData.get(1).get(1), filtersData.get(1).get(2)),
        String.format("Not all Search Results contain Diagonal in a set range between %s and %s", filtersData.get(1).get(1),filtersData.get(1).get(2)));
    softAssert.assertTrue(televisionsPage.isEachProductContainsFilterValue(filtersData.get(2).get(1)),
        String.format("Not all Search Results contain provided  %s ", filtersData.get(2).get(1)));
    softAssert.assertTrue(televisionsPage.isEachProductHasPriceByFilterValue(filtersData.get(3).get(2)),
        String.format("Not all Search Results have provided price %s", filtersData.get(3).get(2)));

    softAssert.assertAll();
}





}
