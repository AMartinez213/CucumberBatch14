package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonsMethods;

public class EmployeeListPage extends CommonsMethods {

    @FindBy(id="empsearch_id")
    public WebElement empSearchIdField;

    @FindBy(id="empsearch_employee_name_empName")
    public WebElement empSearchNameField;

    @FindBy(id="searchBtn")
    public WebElement searchButton;

    public EmployeeListPage(){

        PageFactory.initElements(driver,this);
    }
}
