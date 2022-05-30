package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepDefinations.TestBase;

public class PackageBooking extends TestBase {
    HomePage homePage=new HomePage();


    @FindBy(id = "bookPackageTab")
    WebElement bookPackageTab;

    @FindBy(id = "vacationOriginInput")
    WebElement vacationOriginInput;

    @FindBy(id = "vacationDestinationInput")
    WebElement vacationDestinationInput;


    @FindBy(id = "radiofield-item-id-packageType-0")
    WebElement flightAndHotel;

    @FindBy(id = "radiofield-item-id-packageType-1")
    WebElement flightHotelAndCar;

    @FindBy(id = "radiofield-item-id-packageType-2")
    WebElement flightAndCar;

    @FindBy(id = "selectedRooms")
    WebElement selectedRooms;

    @FindBy(xpath = "//button[*='Find trips']")
    WebElement findTrips;

    By roomOptions=By.xpath("//li[contains(@id,'selectedRooms_item')]");

    public  PackageBooking()
    {
        PageFactory.initElements(TestBase.driver,this);
    }

    public  void  clickPackageTab()
    {
        click(bookPackageTab);
        pause(2000);
    }

    public boolean ensureRoomsFieldVisibility()
    {
        return selectedRooms.isDisplayed();
    }

    public void enterVacationOriginInput(String text) {
        sendKeys(vacationOriginInput,text);
    }

    public void enterDestinationInput(String text) {
        sendKeys(vacationDestinationInput,text);
    }


    public void  clickFindTrips()
    {

        click(findTrips);
    }

    public void selectPackageType(String packageType)
    {
        if(packageType.contains("Flight and hotel"))
        {
            click(flightAndHotel);
            actionClick(flightAndHotel);
        }
        else if(packageType.contains("Flight, hotel and car"))
        {
            click(flightHotelAndCar);
            actionClick(flightHotelAndCar);
        }
        else if(packageType.contains("Flight and car"))
        {
            click(flightAndCar);
            actionClick(flightAndCar);
        }
        pause(2000);
    }

    public void selectRooms(String howMany) {
        boolean x=selectedRooms.isDisplayed();
        if (x)
        {
            click(selectedRooms);
            actionClick(driver.findElements(roomOptions).get(Integer.parseInt(howMany)-1));
            test.info("no of rooms entered as "+howMany);
        }
        else
        {
            test.info("rooms field unavailable");
        }

    }
}
