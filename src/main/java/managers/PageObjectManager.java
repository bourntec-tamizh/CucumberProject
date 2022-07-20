package managers;

import org.openqa.selenium.WebDriver;

//this class instantiate and supply page objects
public class PageObjectManager {

        private WebDriver driver;


        public PageObjectManager(WebDriver driver) {

            this.driver = driver;

        }
}
