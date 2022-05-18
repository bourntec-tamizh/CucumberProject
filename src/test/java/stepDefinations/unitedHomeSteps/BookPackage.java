package stepDefinations.unitedHomeSteps;

import PageObjects.HomePage;
import PageObjects.PackageBooking;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BookPackage {
    PackageBooking packageBooking=new PackageBooking();
    HomePage homePage=new HomePage();


    @And("enter From in package  {string}")
    public void enterFromInPackage(String arg0) {
        packageBooking.enterVacationOriginInput(arg0);
    }

    @And("enter To in package{string}")
    public void enterToInPackage(String arg0) {
        packageBooking.enterDestinationInput(arg0);
    }

    @And("Click Find Trips")
    public void clickFindTrips() {
        packageBooking.clickFindTrips();
    }

    @When("Select package type {string}")
    public void selectPackageType(String arg0) {
        packageBooking.selectPackageType(arg0);
    }

    @And("select room in package {string}")
    public void selectRoomInPackage(String arg0) {
        packageBooking.selectRooms(arg0);
    }

    @When("user select package tab")
    public void userSelectPackageTab() {
        packageBooking.clickPackageTab();
    }

    @And("if package type {string} is Flight and car, ensure Rooms field is invisible")
    public void ifPackageTypeIsFlightAndCarEnsureRoomsFieldIsInvisible(String arg0) {
        if (arg0.toLowerCase().trim().contains("Flight and car"))
        Assert.assertFalse(packageBooking.ensureRoomsFieldVisibility());
    }



    @Then("a result page opens in new tab and contains details {string} {string} {string} {string} {string} {string}")
    public void aResultPageOpensInNewTabAndContainsDetails(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) throws Exception {
        String mainWindowHandle = homePage.driver.getWindowHandle();
        Set<String> allWindowHandles = homePage.driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        while (iterator.hasNext()) {
            String newTab=iterator.next();
            if (!newTab.equalsIgnoreCase(mainWindowHandle))
            {
                homePage.driver.switchTo().window(newTab);
                break;
            }
        }
        HomePage.waitForPageLoad();
        List<WebElement> results=homePage.driver.findElements(By.xpath("//div[contains(@class,'kOrHSG')]//div[@font-size=1]"));
        for (int i=0;i<4;i++)
        {
            if(i==0)
            {
               Assert.assertTrue(results.get(i).getText().trim().contains(arg0));
            }
            else if(i==1)
            {
                Assert.assertTrue(results.get(i).getText().trim().contains(arg1));
            }
            else if(i==2)
            {
                Assert.assertTrue(results.get(i).getText().trim().contains(arg2) && results.get(i).getText().trim().contains(arg3));
            }
            else if(i==3)
            {
                Assert.assertTrue(results.get(i).getText().trim().contains(arg4.split("")[0]) && results.get(i).getText().trim().contains(arg5));
            }
        }


    }
}
