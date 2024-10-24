package Test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Page.DataTablePage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;

public class DataTableTest {
    private WebDriver driver;
    private DataTablePage dataTablePage;

    @BeforeMethod
    public void setUp() {
     
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");

        dataTablePage = new DataTablePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testTableDataInsertion() {
        
        String jsonData = "[{\"name\":\"Bob\",\"age\":20,\"gender\":\"male\"}," +
                "{\"name\":\"George\",\"age\":42,\"gender\":\"male\"}," +
                "{\"name\":\"Sara\",\"age\":42,\"gender\":\"female\"}," +
                "{\"name\":\"Conor\",\"age\":40,\"gender\":\"male\"}," +
                "{\"name\":\"Jennifer\",\"age\":42,\"gender\":\"female\"}]";

     
        dataTablePage.enterJsonDataAndRefreshTable(jsonData);

       
        Map<String, List<String>> expectedData = new HashMap<>();
        expectedData.put("Bob", Arrays.asList("Bob", "20", "male"));
        expectedData.put("George", Arrays.asList("George", "42", "male"));
        expectedData.put("Sara", Arrays.asList("Sara", "42", "female"));
        expectedData.put("Conor", Arrays.asList("Conor", "40", "male"));
        expectedData.put("Jennifer", Arrays.asList("Jennifer", "42", "female"));

  
        List<WebElement> rows = dataTablePage.getTableRows();
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            String name = columns.get(0).getText();
            String age = columns.get(1).getText();
            String gender = columns.get(2).getText();

            List<String> actualRowData = Arrays.asList(name, age, gender);

 
            Assert.assertEquals(actualRowData, expectedData.get(name), "Mismatch found for " + name);
        }
    }
}
