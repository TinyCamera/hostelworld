package hostelworld;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * A page object to hold all the locators I'll be using for the test cases
 * Will use PageFactory to init them
 * @author Niall
 *
 */
public class HostelworldPage {
	
	@FindBy(how = How.ID, using = "country")
	private WebElement countrySelect;
	
	@FindBy(how = How.ID, using = "city")
	private WebElement citySelect;
	
	@FindBy(how = How.ID, using = "search_keywords")
	private WebElement searchBox;
	
	@FindBy(how = How.ID, using = "find-hostels-btn")
	private WebElement searchButton;
	
	@FindBy(how = How.ID, using = "orderBySelect")
	private WebElement orderBySelect;
	
	@FindBy(how = How.ID, using = "perPage")
	private WebElement perPageSelect;
	
	@FindBy(how = How.ID, using = "ui-id-1")
	private WebElement searchAutocompleteElement;
	
	@FindBy(how = How.CLASS_NAME, using = "ui-menu-item")
	private List<WebElement> searchAutocompleteElementResults;

	@FindBy(how = How.CLASS_NAME, using = "result-type-city")
	private List<WebElement> searchAutocompleteCities;


	@FindBy(how = How.CLASS_NAME, using = "fabresult")
	private List<WebElement> searchResults;
	
	@FindBy(how = How.CLASS_NAME, using = "fabResultCount")
	private WebElement resultCount;

	public List<WebElement> getSearchResults() {
		return searchResults;
	}

	public WebElement getCountrySelect() {
		return countrySelect;
	}

	public WebElement getCitySelect() {
		return citySelect;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchAutocompleteElement() {
		return searchAutocompleteElement;
	}

	public List<WebElement> getSearchAutocompleteCities() {
		return searchAutocompleteCities;
	}

	public List<WebElement> getSearchAutocompleteElementResults() {
		return searchAutocompleteElementResults;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
	public WebElement getOrderBySelect() {
		return orderBySelect;
	}
	
	public WebElement getPerPageSelect() {
		return perPageSelect;
	}


	public WebElement getResultCount() {
		return resultCount;
	}
	
	
	 
}

