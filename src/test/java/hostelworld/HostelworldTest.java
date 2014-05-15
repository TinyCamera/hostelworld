package hostelworld;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HostelworldTest {
	String URL = "http://www.hostelworld.com/";
	
	String DUBLIN = "Dublin, Ireland";
	String LONDON = "London";
	
	String[] LONDON_CITIES = {"London, England","London, Ontario, Canada", "East London, South Africa"};
	String SEARCH_SUGGESTIONS = "Search Suggestions";
	String SEARCH_SUGGESTIONS_URL_AFFIX = "find/keywordsuggestions";
	static WebDriver webDriver;
	static HostelworldPage hostelworldPage;
	
	/**
	 * This function, which is run once before all tests 
	 * instantiates the selenium webdriver and the Hostel world
	 * page object
	 */
	@BeforeClass
	public static void setup() {

		webDriver = new FirefoxDriver();
		//Use pagefactory to initialize the hostelworld page object
		hostelworldPage = PageFactory.initElements(webDriver,
				HostelworldPage.class);
	}
	
	/** 
	 * This function loads the hostel world webpage 
	 * before each test.
	 */
	@Before
	public void startTest() {
		webDriver.get(URL);
	}
	
	@Test
	public void useCase1(){
		
		//Type the query into the search box, then 
		//select the "City" from the Autocomplete box
		hostelworldPage.getSearchBox().sendKeys(DUBLIN);
		waitForElementVisibleAndClick(hostelworldPage.getSearchAutocompleteElement());
		waitForElementVisibleAndClick(hostelworldPage.getSearchButton());
		
		//Sort by name and show all results 
		new Select(hostelworldPage.getOrderBySelect()).selectByVisibleText("Name");
		new Select(hostelworldPage.getPerPageSelect()).selectByVisibleText("All");
		
		//Store all the results in a list of webelements 
		List<WebElement> results =  hostelworldPage.getSearchResults();
		
		String temp = results.get(0).findElement(By.className("fabdetails")).findElement(By.className("gotoMicrosite")).getAttribute("title");
		
		//Check if all the results are in alphabetical order
		for(WebElement elem : results){
			 String next = elem.findElement(By.className("fabdetails")).findElement(By.className("gotoMicrosite")).getAttribute("title");
			 if(temp.compareToIgnoreCase(next) > 0){
				 Assert.fail("List not sorted alphabetically: " + next + " - " + temp);
			 }else
			 
			 temp = next;
		}
		
	}
	
	
	@Test
	public void useCase2(){
		//Select the Country/city combo using the dropdowns
		new Select(hostelworldPage.getCountrySelect()).selectByVisibleText("France");
		new Select(hostelworldPage.getCitySelect()).selectByVisibleText("Paris");

		//Click seach
		waitForElementVisibleAndClick(hostelworldPage.getSearchButton());
		
		//Check the results, reading from the page, assert that theres more than 10
		if(new Integer(hostelworldPage.getResultCount().getText())<10){
			Assert.fail("Less then 10 results");
		}
		
		
	}
	
	@Test
	public void useCare3(){
		//Type in the query to be autocompleted
		hostelworldPage.getSearchBox().sendKeys(LONDON);
		waitForElementVisible(hostelworldPage.getSearchAutocompleteElement());
		
		//Store the results, that are cities in this list
		List<WebElement> results = hostelworldPage.getSearchAutocompleteCities();
		
		//Assert they are correct
		for(int i = 0; i<results.size();i++){
			if(!results.get(i).getAttribute("title").equals(LONDON_CITIES[i])){
				Assert.fail("Returned cities not correct: "+results.get(i).getAttribute("title")+" != " +LONDON_CITIES[i] );
			}
		}
		
		//Click search to move to the next stage
		waitForElementVisibleAndClick( hostelworldPage.getSearchButton());
		
		//Assert the h1 and the URL are both correct
		Assert.assertTrue("h1 not "+ SEARCH_SUGGESTIONS, webDriver.findElement(By.cssSelector("h1")).getText().equals(SEARCH_SUGGESTIONS));
		Assert.assertTrue("URL inccorect: " + webDriver.getCurrentUrl(), webDriver.getCurrentUrl().startsWith(URL+SEARCH_SUGGESTIONS_URL_AFFIX));
	}
	
	/**
	 * After all tests are finished, quit the webdriver
	 */
	@AfterClass
	public static void finish(){
		webDriver.quit();
	}
	
	
	/**
	 * Some functions to implicitly wait for elements
	 * 
	 * 
	*/
	public void waitForElementVisible(WebElement elem){
		WebDriverWait wait = new WebDriverWait(webDriver, 5);
		wait.until(ExpectedConditions.visibilityOf(elem));
	}
		
	public void waitForElementVisibleAndClick(WebElement elem){
		WebDriverWait wait = new WebDriverWait(webDriver, 5);
		wait.until(ExpectedConditions.visibilityOf(elem));
		elem.click();
	}
		
		
		
}

