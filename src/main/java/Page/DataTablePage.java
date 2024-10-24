package Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import org.openqa.selenium.By;

public class DataTablePage {
    WebDriver driver;

    // Locators
    @FindBy(xpath = "//*[text()='Table Data']")
    private WebElement buttonTableData;

    @FindBy(xpath = "//textarea[@id='jsondata']")
    private WebElement inputJsonData;

    @FindBy(xpath = "//button[@id='refreshtable']")
    private WebElement buttonRefreshTable;

    @FindBy(xpath = "//table[@id='dynamictable']/tbody/tr")
    private List<WebElement> tableRows;

    // Constructor
    public DataTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to perform actions: click button, enter data, and refresh table
    public void enterJsonDataAndRefreshTable(String jsonData) {
        buttonTableData.click();
        inputJsonData.clear();
        inputJsonData.sendKeys(jsonData);
        buttonRefreshTable.click();
    }

    // Method to get table data from the page
    public List<WebElement> getTableRows() {
        return tableRows;
    }
}
