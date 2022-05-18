package stepDefinations.imdb;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import libraries.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinations.TestBase;

import java.io.IOException;
import java.util.List;

public class Top250MoviesList extends TestBase {


    @Given("I am in IMDB website")
    public void i_am_in_imdb_website() throws IOException {
        browser = ConfigReader.getConfigValue("Browser");
        openDriverBrowser();
        navigatetoURL();
    }

    @When("click top {int} movies")
    public void click_top_movies(Integer no) throws Exception {
        click(By.xpath("//a[@role='menuitem' and contains(*,'Top 250 Movies')]"));
    }

    @Then("check whether {string} is present in the list or not")
    public void check_whether_is_present_in_the_list_or_not(String moviename) {
        List<WebElement> movies = driver.findElements(By.xpath("//td[@class='titleColumn']/a"));
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getText().trim().equalsIgnoreCase(moviename)) {
                System.out.println(moviename + " is present in top 250 list");
                break;
            } else if (i == movies.size() - 1) {
                System.out.println(moviename + " is not in top 250 list");
                break;
            } else continue;


        }

    }


}


