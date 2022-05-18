package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import stepDefinations.TestBase;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class HomePage extends TestBase {
    private By menuLink;
    public By subMenuLink;
    private By travelTab;
    private By travelTabSelectedStatus;
    private By fightTypeRadio;
    private By flightTicketOriginCity;
    private By flightTicketDestinationCity;
    private By findFlightsButton;
    private By originFlightsList;
    private By destinationFlightsList;
    private By TravellerMenuOpen;
    private By TravellerMenuClose;
    private By adultInput;
    private By adultAdd;
    private By fromDateEdit;
    private By economyPrices;
    private By economyRefundablePrices;
    private By businessPrices;
    private By economyPremiumEconomyPrices;
    private By flightInfoDuration;
    private By flightInfoStartTime;
    private By flightInfoEndTime;
    private By bestPricesList;
    private By bestDatesToTravel;
    private By toDateEdit;
    private By subtractAdult;
    private String travelerLocator1,travelerLocator2;
    private By travelers,cabinType,flightInfo,flexMonth,flexDay,flexDayOptions,flexMonthOptions;
    private By flexibleDates;

    //hotel booking page elements
    private By hotelTab,hotelDestination,hotelCheckinDate,hotelCheckoutDate,hotelNoOfRooms,findHotels,roomOptions;
    private By invalidCheckinmsg,invalidCheckoutmsg,invalidStayLengthmsg;

    //car booking page elements
    private By bookCarTab,bookCarPickupInput,bookCarDropoffInput,bookCarPickupDate,bookCarDropoffDate;
    private By driversAge,pickupTime,dropoffTime,showDropOffLocation,hideAgeBox,pickupTimeOptions,dropOffTimeOptions;
    private By findCars;

    //package booking elements
    private  By flightAndHotel;
    private By flightHotelAndCar;


    public By getFlightAndHotel() {
        return flightAndHotel;
    }

    public By getFlightHotelAndCar() {
        return flightHotelAndCar;
    }

    public By getFlightAndCar() {
        return flightAndCar;
    }

    private By flightAndCar;

    public By getBookCarPickupInput_menu() {
        return bookCarPickupInput_menu;
    }

    private By bookCarPickupInput_menu;



    public HomePage() {
        InitElements();
    }


    public By getFromDateEdit() {
        return fromDateEdit;
    }

    public By getToDateEdit() {
        return toDateEdit;
    }

    private void InitElements() {
        travelTab = By.xpath("//li[@id='travelTab']");
        travelTabSelectedStatus = By.xpath("//li[@id='travelTab'][@aria-selected='true']");
        flightTicketOriginCity = By.xpath("//input[@id='bookFlightOriginInput']");
        flightTicketDestinationCity = By.xpath("//input[@id='bookFlightDestinationInput']");
        findFlightsButton = By.xpath("//button[@type='submit']//span[text()='Find flights']");
        originFlightsList = By.xpath("//*[@id='bookFlightOriginInput-menu']//button//span");
        destinationFlightsList = By.xpath("//*[@id='bookFlightDestinationInput-menu']//button//span");
        TravellerMenuOpen = By.xpath("//p[@id='stuff']//following-sibling::button");
        TravellerMenuClose = By.xpath("//button[@class='atm-c-btn atm-c-btn--bare']");
        adultInput = By.xpath("//button[@aria-label='Add one more Adult']//following-sibling::input[1]");
        adultAdd = By.xpath("//button[@aria-label='Substract one Adult']");
        fromDateEdit = By.xpath("//input[@id='DepartDate']");

        toDateEdit = By.xpath("//input[@id='ReturnDate']");
        economyPrices = By.xpath("");
        economyRefundablePrices = By.xpath("");
        economyPremiumEconomyPrices = By.xpath("");
        businessPrices = By.xpath("");
        flightInfoDuration = By.xpath("");
        flightInfoStartTime = By.xpath("");
        flightInfoEndTime = By.xpath("");
        bestPricesList = By.xpath("//div[@class='app-components-Shopping-FareWheelCard-styles__amountValue--3IF2-']//span");
        bestDatesToTravel = By.xpath("//div[@class='app-components-Shopping-FareWheelCard-styles__dateLabel--MAATI']");
        travelerLocator1="//div[@id='passengerMenuId']//p[contains(*,'";
        travelerLocator2="')]//following-sibling::div/button[2]";
        subtractAdult=By.xpath("//button[@aria-label='Add one more Adult']");
        cabinType=By.xpath("//button[@id='cabinType']");
        flightInfo=By.id("flightInfoHeader");
        flexibleDates=By.id("flexibleDates");
        flexDay=By.id("flexDay");
        flexMonth=By.id("flexMonth");
        flexDayOptions=By.xpath("//li[contains(@id,'flexDay_item')]");
        flexMonthOptions=By.xpath("//li[contains(@id,'flexMonth_item')]");




        //hotel
        hotelTab=By.id("bookHotelTab");
        hotelDestination=By.id("bookHotelInput");
        hotelCheckinDate=By.id("bookHotelCheckinDate");
        hotelCheckoutDate=By.id("bookHotelCheckoutDate");
        hotelNoOfRooms=By.id("roomsDropdown");
        roomOptions=By.xpath("//li[contains(@id,'roomsDropdown')]");
        invalidCheckinmsg=By.xpath("//span[contains(text(),'Please enter a valid checkin date')]");
        invalidCheckoutmsg=By.xpath("//span[contains(text(),'Please enter a valid checkout date')]");
        invalidStayLengthmsg=By.xpath("//span[contains(text(),'Please enter a length of stay of 28 day or less')]");

        //car
        bookCarTab=By.id("bookCarTab");
        bookCarPickupInput=By.id("bookCarPickupInput");
        bookCarPickupInput_menu=By.xpath("//ul[@id='bookCarPickupInput-menu']//li");
        bookCarDropoffInput=By.id("bookCarDropoffInput");
        bookCarPickupDate=By.id("bookCarPickupDate");
        bookCarDropoffDate=By.id("bookCarDropoffDate");
        driversAge=By.id("driversAge");
        pickupTime=By.id("pickupTime");
        dropoffTime=By.id("dropoffTime");
        showDropOffLocation=By.id("showDropOffLocation");
        hideAgeBox=By.id("hideAgeBox");
        pickupTimeOptions=By.xpath("//li[contains(@id,'pickupTime_item')]");
        dropOffTimeOptions=By.xpath("//li[contains(@id,'dropoffTime_item')]");
        findCars=By.xpath("//button[contains(text(),'Find cars')]");

        //package
        flightAndHotel=By.id("radiofield-item-id-packageType-0");
        flightHotelAndCar=By.id("radiofield-item-id-packageType-1");
        flightAndCar=By.id("radiofield-item-id-packageType-2");


    }


    public By getFlightInfo() {
        return flightInfo;
    }

    public By getPickupTimeOptions() {
        return pickupTimeOptions;
    }


    public By getFindCars() {
        return findCars;
    }

    public By getDropOffTimeOptions() {
        return dropOffTimeOptions;
    }

    public By getBookCarPickupInput() {
        return bookCarPickupInput;
    }

    public By getBookCarDropoffInput() {
        return bookCarDropoffInput;
    }

    public By getBookCarPickupDate() {
        return bookCarPickupDate;
    }

    public By getBookCarDropoffDate() {
        return bookCarDropoffDate;
    }

    public By getDriversAge() {
        return driversAge;
    }

    public By getPickupTime() {
        return pickupTime;
    }

    public By getDropoffTime() {
        return dropoffTime;
    }

    public By getShowDropOffLocation() {
        return showDropOffLocation;
    }

    public By getHideAgeBox() {
        return hideAgeBox;
    }

    public By getBookCarTab() {
        return bookCarTab;
    }
    public By getInvalidCheckinmsg() {
        return invalidCheckinmsg;
    }

    public By getInvalidCheckoutmsg() {
        return invalidCheckoutmsg;
    }

    public By getInvalidStayLengthmsg() {
        return invalidStayLengthmsg;
    }


    public void selectBookmenu() throws Exception {
        click(travelTab);
        waitForElementToDisplay(travelTabSelectedStatus, 10);
        if(isElementCurrentlyDisplayed(travelTabSelectedStatus)){
            test.pass("Selected Book tab");
        } else {
            test.fail("Selected Book tab");
            Assert.fail("Selected Book tab");
        }
    }

    public void selectFlightType(String flightType) throws Exception {
        fightTypeRadio = By.xpath("//input[@id='"+flightType.toLowerCase()+"']//following-sibling::span");
        click(fightTypeRadio);
        test.info("Flight type selected as: "+flightType);
    }

    public void selectFlexibleDatesCheckbox()
    {
        if(!driver.findElement(flexibleDates).isSelected())
        {
            actionClick(flexibleDates);
        }
    }

    public void selectFromCity(String city) {
        if(sendKeys(flightTicketOriginCity, city)){
            pause(3000);
            List<WebElement> originFlights = driver.findElements(originFlightsList);
            for(int i=0;i<originFlights.size();i++){
                System.out.println(originFlights.get(i).getText().trim());
            }
            if (originFlights.size() > 0) {
                click(originFlights.get(0));
                test.pass("Origin city selected");;
            }
        } else {
            test.fail("Origin city not selected as: "+city);
            Assert.fail("Origin city not selected as: "+city);
        }
    }

    public void selectToCity(String city) {
        if(sendKeys(flightTicketDestinationCity, city)) {
            pause(3000);
            List<WebElement> destinationFlights = driver.findElements(destinationFlightsList);
            for(int i=0;i<destinationFlights.size();i++){
                System.out.println(destinationFlights.get(i).getText().trim());
            }
            if (destinationFlights.size() > 0) {
                click(destinationFlights.get(0));
                test.pass("Destination city selected ");
            }
        }else {
            test.fail("Destination city not selected as: "+city);
            Assert.fail("Destination city not selected as: "+city);
        }
    }

    public void clickOnFindFlightsButton() throws Exception {
        if(click(findFlightsButton)){
            test.pass("Find flights button is selected");
        } else {
            test.fail("Find flights button is selected");
            Assert.fail("Find flights button is selected");
        }
    }

    public void selectTravellersMenu() throws Exception {
        actionClick(TravellerMenuOpen);
    }

    public  void  selectTravelers(String traveler,String count) throws Exception {
        travelers=By.xpath(travelerLocator1+traveler+travelerLocator2);
        click(TravellerMenuOpen);
        pause(2000);
        //click(target);
//        sendKeys(adultInput, travellers);
        if (traveler.toLowerCase().contains("adult")) {
            for (int i = 1; i < Integer.parseInt(count); i++) {
                pause(1000);
                click(travelers);
            }
        }
        else {
            for (int i = 0; i < Integer.parseInt(count); i++) {
                pause(1000);
                click(travelers);
            }
            click(subtractAdult);


        }
        test.info("selected "+count+" "+traveler);
    }
    public void closeTravellersMenu() throws Exception {
        click(TravellerMenuClose);
    }

    public void selectCabinType(String value) throws Exception {
        click(cabinType);
        pause(2000);
        if (value.equalsIgnoreCase("economy")) {
            actionClick(By.xpath("//li[@id='cabinType_item-0']"));
            test.info("economy class selected");
        }
        else if (value.equalsIgnoreCase("premium economy"))
        {
            actionClick(By.xpath("//li[@id='cabinType_item-1']"));
            test.info("premium economy class selected");
        }
        else if (value.equalsIgnoreCase("business")) {
            actionClick(By.xpath("//li[@id='cabinType_item-2']"));
            test.info("business class selected");
        }

    }

    public void selectAdults(String travellers) throws Exception {
        click(TravellerMenuOpen);
        pause(2000);
        click(adultInput);
//        sendKeys(adultInput, travellers);
        for (int i = 1; i < Integer.parseInt(travellers); i++) {
            pause(1000);
            click(adultAdd);
        }
    }

    public void verifyListOfAvailableFlights() {
        pause(10000);
        List<WebElement> pricesList = driver.findElements(bestPricesList);
        List<WebElement> datesList = driver.findElements(bestDatesToTravel);
        for(int i=0;i<pricesList.size();i++){
            test.info("Date: "+datesList.get(i).getText());
            test.info("Price: "+pricesList.get(i).getText());
        }
    }

    public void selectFromDate(String fromDate) {
        if(isElementEnabled(fromDateEdit)){
            sendKeys(fromDateEdit,fromDate );
            pause(1000);
            //sendKeys(toDateEdit,Keys.ENTER);
            test.info("From Date is selected as: "+fromDate);
        }
        else test.info("from date field is not available");
    }

    public void selectToDate(String toDate) {
        if(isElementEnabled(toDateEdit)){
            sendKeys(toDateEdit,toDate );
            //sendKeys(fromDateEdit,Keys.ENTER);
            test.info("To Date is selected as: "+toDate);
        }
        else test.info("to date field is not available");
    }

    public void clickHotelTab() throws Exception {
    if (isElementEnabled(hotelTab))
    {
        click(hotelTab);
        test.info("hotel tab clicked");
    }
    else
    {
        test.fail("hotel tab unavailable");
    }
    }
    public void enterHotelDestination(String destination){
        if (isElementEnabled(hotelDestination))
        {
            clearText(hotelDestination);
            sendKeys(hotelDestination,destination);
            test.info("hotel destination entered as "+destination);
        }
        else
        {
            test.fail("hotel destination field unavailable");
        }


    }
    public void enterHotelCheckin(String checkin) {
        if (isElementEnabled(hotelCheckinDate))
        {
            clearText(hotelCheckinDate);
            sendKeys(hotelCheckinDate,checkin+Keys.ENTER);

            test.info("hotel checkin entered as "+checkin);
        }
        else
        {
            test.fail("hotel checkin field unavailable");
        }

    }
    public void enterHotelCheckout(String checkout) {
        if (isElementEnabled(hotelCheckoutDate))
        {
            clearText(hotelCheckoutDate);
            sendKeys(hotelCheckoutDate,checkout);
            test.info("hotel checkin entered as "+checkout);
        }
        else
        {
            test.fail("hotel checkout field unavailable");
        }


    }
    public void selectNoOfRooms(String howMany) throws Exception {

        if (isElementEnabled(hotelNoOfRooms))
        {
            click(hotelNoOfRooms);
            actionClick(driver.findElements(roomOptions).get(Integer.parseInt(howMany)-1));
            test.info("no of rooms entered as "+howMany);
        }
        else
        {
            test.fail("no of rooms field unavailable");
        }
    }
    public void pressFindHotel() throws Exception {
        click(findHotels);
        test.info("find hotel button is clicked");
    }
    public void selectFlexOptions(String args) throws Exception {
        Random rand=new Random();
        List<WebElement> options;
        if (args.equalsIgnoreCase("days"))
        {
            click(flexDay);
            pause(1000);
            options=driver.findElements(flexDayOptions);
            click(options.get(rand.nextInt(options.size())));
        }
        else if (args.equalsIgnoreCase("months"))
        {
            click(flexMonth);
            pause(1000);
            options=driver.findElements(flexMonthOptions);
            click(options.get(rand.nextInt(options.size())));
        }
    }
}


