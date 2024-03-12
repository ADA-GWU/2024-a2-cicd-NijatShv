package ada.edu.demo.webtest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WebInterfaceTests {

	@Autowired
	private WebDriver webDriver;

	@LocalServerPort
	private int port;

	@Test
	@Order(1)
	@DisplayName("Create a user")
	public void CreateUser() {
		webDriver.get("http://localhost:"+port+"/student/new");

		WebElement studentIdInput = webDriver.findElement(By.id("studentId"));
		WebElement firstNameInput = webDriver.findElement(By.id("firstName"));
		WebElement lastNameInput = webDriver.findElement(By.id("lastName"));
		WebElement emailInput = webDriver.findElement(By.id("email"));

		// Check if such a field exists
		assertNotNull(firstNameInput);

		try {
			studentIdInput.sendKeys("1");
			Thread.sleep(2000);
			firstNameInput.sendKeys("Nigar");
			Thread.sleep(2000);
			lastNameInput.sendKeys("Salayeva");
			Thread.sleep(2000);
			emailInput.sendKeys("ns@ada.edu.az");
			Thread.sleep(2000);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}

		// Find and submit the form (assuming there's a submit button with a specific attribute)
		WebElement submitButton = webDriver.findElement(By.id("submit"));
		submitButton.click();
	}

	@Test
	@Order(3)
	@DisplayName("Check the created user")
	public void CheckUser() {
		// Check if the student is added
		webDriver.get("http://localhost:"+port+"/student/list");
		List<WebElement> bodyElementFName = webDriver.findElements(By.xpath("//*[contains(text(), 'Nigar')]"));
		List<WebElement> bodyElementLName = webDriver.findElements(By.xpath("//*[contains(text(), 'Salayeva')]"));
		System.out.println("Element result"+bodyElementLName);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		// Check if the text "Jamal" is present in the page content
		assert(bodyElementFName.size() == 1);
		assert(bodyElementLName.size() == 1);
	}
	@Test
	@Order(4)
	@DisplayName("Check the created User by Name")
	public void CheckUserBYName(){

		webDriver.get("http://localhost:"+port+"/student/list");
		List<WebElement> bodyElementFname = webDriver.findElements(By.xpath("//*[contains(text(), 'Nigar')]"));

		try{
			Thread.sleep(2000);

		} catch (InterruptedException e){
			throw new RuntimeException(e);

		}
		assert (bodyElementFname.size() == 1);
	}

	@Test
	@Order(5)
	@DisplayName("Check the created User by Name")
	public void CheckUserByID(){

		webDriver.get("http://localhost:"+port+"/student/list");
		List<WebElement> bodyElementID = webDriver.findElements(By.xpath("//*[contains(text(), '1')]"));

		try{
			Thread.sleep(2000);

		} catch (InterruptedException e){
			throw new RuntimeException(e);

		}
		assert (bodyElementID.size() == 1);
	}
	@Test
	@Order(5)
	@DisplayName("Update User Details")
	public void UpdateUserDetails(){
		webDriver.get("http://localhost:"+port+"/student/update?id=1");

		WebElement firstNameInput = webDriver.findElement(By.id("firstName"));
		WebElement lastNameInput = webDriver.findElement(By.id("lastName"));
		WebElement emailInput = webDriver.findElement(By.id("email"));

		firstNameInput.clear();
		lastNameInput.clear();
		emailInput.clear();

		try {
			firstNameInput.sendKeys("Nigarr");
			Thread.sleep(2000);
			lastNameInput.sendKeys("Salayevali");
			Thread.sleep(2000);
			emailInput.sendKeys("nsli@ada.edu.az");
			Thread.sleep(2000);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		WebElement submitButton = webDriver.findElement(By.id("submit"));
		submitButton.click();
	}

}
