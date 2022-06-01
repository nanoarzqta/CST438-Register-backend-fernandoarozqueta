package com.cst438;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

@SpringBootTest
public class EndToEndNewStudent {

	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/Users/Matt/Documents/CSUMB/CST438/Selenium/chromedriver.exe";

	public static final String URL = "https://cst438-register-front-end.herokuapp.com";

	public static final String TEST_USER_EMAIL = "e2etest@csumb.edu";
	
	public static final String TEST_USER_NAME = "TEST NAME";

	public static final int SLEEP_DURATION = 1000; // 1 second.

	@Autowired
	StudentRepository studentRepository;
	
	@Test
	public void addStudentTest() throws Exception {
		
		//Remove test student
		Student x = null;
		do {
			x = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (x != null)
				studentRepository.delete(x);
		} while (x != null);
		
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {
			
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			List<WebElement> listOfElements = driver.findElements(By.xpath("//a"));
			listOfElements.get(1).click();
			
			driver.findElement(By.xpath("//button[span='Add Student']")).click();
			Thread.sleep(SLEEP_DURATION);
			
			// enter Student Name and Email and click "Add"
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(TEST_USER_EMAIL);
			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(TEST_USER_NAME);
			driver.findElement(By.xpath("//button[span='Add']")).click();
			Thread.sleep(SLEEP_DURATION);
			
			// verify that new student row has been inserted to database.
			Student e = studentRepository.findByEmail(TEST_USER_EMAIL);
			assertNotNull(e, "Student record not found in database.");
			
		} catch (Exception ex) {
			throw ex;
		} finally {
	
			// clean up database.
			Student e = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (e != null)
				studentRepository.delete(e);
	
			driver.quit();
		}
	}
}