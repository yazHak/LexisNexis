import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class GoogleSearch {

    public static void main(String[] args) {

        int n=3;
        String pageTitle = returnNthResultOfLexisNexisSearchOnGoogle(n);
        System.out.println(n+". result's page title for LexisNexis is: "+pageTitle);
    }

    public static String returnNthResultOfLexisNexisSearchOnGoogle (int n){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        By searchBoxLocator = By.name("q");
        // I tried to eliminate "People also ask" block ( div[@class='ifM9O'] )
        By resultListLocator = By.xpath("//div[@class='g']//a/h3[not(ancestor::div[@class='ifM9O'])]");

        driver.get("http://www.google.com"); //1. Open Google search engine
        driver.findElement(searchBoxLocator).sendKeys("LexisNexis"); //2. Search from “LexisNexis”
        driver.findElement(searchBoxLocator).sendKeys(Keys.ENTER);

        //Listing search results
        List<WebElement> resultList = driver.findElements(resultListLocator);

        resultList.get(n-1).click(); //3. Open the third link in the search result
        return driver.getTitle(); //4. Return the page title
    }
}
// Tell all the girls that I am back to town