package onliner.stepdefinitions;

import framework.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onliner.enums.Filter;
import onliner.pages.CatalogPage;
import onliner.pages.HomePage;
import onliner.pages.ProductsPage;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class SearchTVsUsingFiltersSteps extends BaseTest {
    private HomePage homePage;
    private CatalogPage catalogPage;
    private ProductsPage productsPage;
    private static HashMap<String,String> filtersData = new HashMap<String, String>();

    @Given("User is on the Onliner home page")
    public void userVisitOnliner(){
        initializeWebdriver();
        homePage = new HomePage();
    }
    @And("User opens {string} page")
    public void userNavigateToOnlinerSection(String menuSection){
        homePage.navigateSection(menuSection);
    }

    @And("User selects {string} from catalog main page")
    public void userNavigateToCatalogPageSection(String catalogItem){
        catalogPage = new CatalogPage();
        catalogPage.navigateCatalogItem(catalogItem);
    }

    @And("User selects {string} and open {string} from Electronica submenu")
    public void userNavigateToTelevisionsPageFromElectronicaSubmenu(String asideItem, String dropDownItem){
        catalogPage.selectCatalogMenuDropdownItem(asideItem,dropDownItem);
        productsPage = new ProductsPage();
            }

    @When("User selects filters on Televisions page")
    public void userSelectFiltersOnProductsPage(Map<String,String> userFilters){
        userFilters.forEach((title, value) -> {
            productsPage.setFilter(Filter.valueOf(title), value);
            filtersData.put(title,value);
        });
    }
@Then("search results satisfy all entered filters")
    public void searchResultsSatisfyAllFilterValues(){
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(productsPage.isEachProductHasTitleWithFilterValue(filtersData.get("PRODUCER")),
        String.format("Not all Search Results contain provided Producer %s", filtersData.get("PRODUCER")));
    softAssert.assertTrue(productsPage.isEachProductWithinMinAndMaxRange(filtersData.get("MINDIAGONAL"), filtersData.get("MAXDIAGONAL")),
        String.format("Not all Search Results contain Diagonal in a set range between %s and %s", filtersData.get("MINDIAGONAL"),filtersData.get("MAXDIAGONAL")));
    softAssert.assertTrue(productsPage.isEachProductContainsFilterValue(filtersData.get("RESOLUTION")),
        String.format("Not all Search Results contain provided  %s ", filtersData.get("RESOLUTION")));
    softAssert.assertTrue(productsPage.isEachProductHasPriceByFilterValue(filtersData.get("MAXPRICE")),
        String.format("Not all Search Results have provided price %s", filtersData.get("MAXPRICE")));

    softAssert.assertAll();
}





}
