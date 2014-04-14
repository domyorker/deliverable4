package srr.test.SRRTest;

/**
 * @author Jose Marte
 * ACCEPTANCE AND PERFORMANCE TESTING 
 * April 2014
 */

import java.util.concurrent.TimeUnit;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import srr.main.SRRTest.StopWatch;

public class DefSteps {
	// declare a few class-level variables...
	private String userName;
	private String accountPassword;
	WebDriver driver = new FirefoxDriver();
	String bodyText = "";
	// stopwatch used to record performance
	StopWatch pWatch;

	@Before
	public void startSel() {
		// open the website...
		driver.get("http://localhost:8080/nb_proj_working/");
	}

	/**
	 * A helper method to setup the login credentials
	 * 
	 * @param userName
	 *            The username to use
	 * @param psw
	 *            The password to use
	 */

	@Given("^a login of \"([^\"]*)\" and \"([^\"]*)\"$")
	public void a_login_of_and(String userName, String psw) {
		this.userName = userName;
		this.accountPassword = psw;
	}

	/**
	 * A PERFORMANCE method to login a user x number of times
	 * 
	 * @param number
	 *            The number of times to log username into the system
	 */
	@Given("^I login \"([^\"]*)\" times$")
	public void I_login_times(int number) {
		this.userName = "domyorker";
		this.accountPassword = "up4life";
		for (int i = 1; i <= number; i++) {
			I_log_in();
		}
	}

	/**
	 * A method to perform the login
	 */
	@When("^I log in$")
	public void I_log_in() {

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("txtUserName")).sendKeys(this.userName);
		driver.findElement(By.id("txtPassword")).sendKeys(this.accountPassword);
		driver.findElement(By.id("btnSubmit")).click();
	}

	/**
	 * An assertion for confirming a successful login
	 */
	@Then("^I should be logged in$")
	public void I_should_be_logged_in() {
		assertTrue(driver.findElement(By.id("loginStatus")).getText()
				.contains("Logged in"));
	}

	/**
	 * Test for the presence of text in the given HTML element
	 * 
	 * @param elemID
	 *            The ID of the HTML element
	 * @param text
	 *            The text that the element must contain
	 */
	@Then("^I should see \"([^\"]*)\" in \"([^\"]*)\"$")
	public void textPresent(String text, String elemID) {
		bodyText = driver.findElement(By.id(elemID)).getText();
		assertTrue(bodyText.contains(text));
	}

	/**
	 * A helper method to navigate to the given URL
	 * 
	 * @param url
	 *            The relative url to navigate to
	 */
	@When("^go to \"([^\"]*)\"$")
	public void go_to(String url) {

		driver.findElement(By.linkText(url)).click();
	}

	/**
	 * A helper method to create a resume
	 * 
	 * @param title
	 *            The title to give the new resume
	 */
	@When("^create a resume titled \"([^\"]*)\"$")
	public void create_resume(String title) {
		driver.findElement(By.id("txtResumeName")).clear();
		driver.findElement(By.id("txtResumeName")).sendKeys(title);
		driver.findElement(By.id("btnCreateResume")).click();
	}

	/**
	 * A helper method to populate the summary section of the resume
	 */
	@When("^I enter my summary text$")
	public void I_enter_my_summary_text() {

		driver.findElement(By.id("txtObjective")).clear();
		driver.findElement(By.id("txtObjective"))
				.sendKeys(
						"Looking join a creative company where I can utilize and grow my programming skills.");
		driver.findElement(By.id("txtExperience")).clear();
		driver.findElement(By.id("txtExperience"))
				.sendKeys(
						"I am a senior Information Science Major at the University of Pittsburgh. I have several years of experience working with Microsoft as well as Oracle and Open Source server stacks.");
		driver.findElement(By.id("txtAccomplishments")).clear();
		driver.findElement(By.id("txtAccomplishments"))
				.sendKeys(
						"I have successfully completed a number of school-related projects, some of which are live web applications.\nI have consistently maintained a high GPA in all courses related to my field.");

		driver.findElement(By.id("txtSkill_1")).clear();
		driver.findElement(By.id("txtSkill_1")).sendKeys(
				"Working knowledge of database management systems");
	}

	/**
	 * A method to record registration speed on a number of dummy accounts
	 * 
	 * @param accounts
	 *            The number of dummy accounts to register
	 */
	@When("^I register \"([^\"]*)\" \"([^\"]*)\" accounts$")
	public void the_registration_of_dummy_accounts(int accounts, String userName) {

		if (accounts > 0) {
			for (int i = 1; i <= accounts; i++) {
				driver.findElement(By.linkText("Register")).click();
				driver.findElement(By.id("txtUserName")).sendKeys(userName + i);
				driver.findElement(By.id("txtPassword")).sendKeys("test");
				driver.findElement(By.id("txtFirstName")).sendKeys("Dummy");
				driver.findElement(By.id("txtLastName")).sendKeys("Test");
				new Select(driver.findElement(By.id("ddlGradeLevel")))
						.selectByVisibleText("Freshman");
				driver.findElement(By.id("txtEmail")).sendKeys(
						"jem202@pitt.edu");
				driver.findElement(By.id("txtTel")).sendKeys("4123789565");
				driver.findElement(By.id("txtAddressLine1")).sendKeys(
						"587 Washington Hights");
				driver.findElement(By.id("txtCity")).sendKeys("Pittsburgh");
				new Select(driver.findElement(By.id("ddlState")))
						.selectByVisibleText("Pennsylvania");
				driver.findElement(By.id("txtZIP")).sendKeys("15057");

				driver.findElement(By.id("bntSubmitStudent")).click();

			}
		}
	}

	/**
	 * A helper method to select a resume with the given title for editing
	 * 
	 * @param title
	 *            The title of the resume to edit
	 */

	@When("^edit the resume titled \"([^\"]*)\"$")
	public void edit_the_resume(String title) {
		driver.findElement(By.linkText("Edit")).click();
	}

	/**
	 * A method to submit a form
	 * 
	 * @param btnID
	 *            The submit buttons HTML ID
	 * 
	 */

	@When("^submit the changes by clicking \"([^\"]*)\"$")
	public void submit_the_changes(String btnID) {
		// Submit the form
		driver.findElement(By.id(btnID)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * PERFORMANCE method to start the timer
	 */
	@When("^I start the performance test$")
	public void start_performance_test() {
		pWatch = new StopWatch();
		pWatch.start();
	}

	/**
	 * PERFORMANCE method to stop the timer
	 */
	@And("^I stop the performance test$")
	public void stop_performance_test() {
		pWatch.stop();
	}

	/**
	 * PERFORMANCE method to to create x number of resumes
	 */
	@Given("^create \"([^\"]*)\" \"([^\"]*)\" resumes$")
	public void create_esumes(int number, String name) {
		for (int i = 1; i <= number; i++) {
			go_to("Manage Resumes");
			create_resume(name);
			I_enter_my_summary_text();
			submit_the_changes("btnSubmit");

		}
	}

	/**
	 * Performance reporting method
	 * 
	 */
	@Then("^display performance results$")
	public void display_performance_results() {
		System.out.print("Elapsed Time: " + pWatch.getElapsedTime());
	}

	// Stop Selenium
	@After
	public void destroySelenium() {
		driver.quit();
	}
}
