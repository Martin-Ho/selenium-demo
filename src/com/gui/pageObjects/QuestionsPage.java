package com.gui.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuestionsPage {
	private final WebDriver driver;
	WebElement titleLocator;
	WebElement descriptionLocator;
	WebElement saveButtonLocator;
	WebDriverWait wait;

    public QuestionsPage(WebDriver driver) {
        this.driver = driver;

        titleLocator = driver.findElement(By.id("titleTextField"));
		descriptionLocator = driver.findElement(By.id("descriptionTextField"));
		saveButtonLocator = driver.findElement(By.id("saveButton"));
		
		wait = (new WebDriverWait(driver, 10));
    }
    
    public QuestionsPage typeDescription(String description) {
    	descriptionLocator.sendKeys(description);
        return this;    
    }
    
    public QuestionsPage typeTitle(String title) {
    	titleLocator.sendKeys(title);
        return this;    
    }
    
    public QuestionsPage submitQuestion() { 
    	saveButtonLocator.click();
        return this;    
    }
    
    public QuestionsPage submitMultipleQuestion() { 
    	typeTitle("question title 1");
    	typeDescription("question description 1");
    	submitQuestion();
    	
    	typeTitle("question title 2");
    	typeDescription("question description 2");
    	submitQuestion();
    	
    	typeTitle("question title 3");
    	typeDescription("question description 3");
    	submitQuestion();
    	
        return this;    
    }
    
    public String getUrl() {
    	return driver.getCurrentUrl();
    }
    
    public WebElement getErrorBox() {
    	return wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
    }
    
    public String getErrorBoxText() {
    	WebElement errorBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
    	return errorBox.getText();
    }
    
    public String getErrorBoxColor() {
    	WebElement errorBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
    	return errorBox.getCssValue("background-color");
    }
    
    public WebElement getQuestionItem() {
    	return wait.until(ExpectedConditions.presenceOfElementLocated(By.className("question-list-item")));
    }
    
    public String getQuestionItemText() {
    	WebElement questionItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("question-list-item")));
    	return questionItem.getText();
    }
    
    public List<WebElement> getQuestionItems() {
    	return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("question-list-item")));
    }
    
    public int getQuestionItemsLength() {
    	List<WebElement> questions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("question-list-item")));
    	return questions.size();
    }
    
    public void selectQuestionNum(int questionNum) {
    	List<WebElement> questions = getQuestionItems();
    	questions.get(questionNum).click();
    }
    
    public WebElement getSelectedQuestion() {
    	return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='question-list-item selected']")));
    }
    
    public String getSelectedQuestionText() {
    	WebElement selectedQuestion = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='question-list-item selected']")));
    	return selectedQuestion.getText();
    }
    
    public String getSelectedQuestionColor() {
    	WebElement selectedQuestion = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='question-list-item selected']")));
    	return selectedQuestion.getCssValue("background-color");
    }
    
    public WebElement getSelectedQuestionDetail() {
    	return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='question-detail selected']")));
    }
    
    public String getSelectedQuestionDetailColor() {
    	WebElement selectedQuestionDetail = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='question-detail selected']")));
    	return selectedQuestionDetail.getCssValue("background-color");
    }
    
    public String getQuestionDetailTitle() {
    	titleLocator = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("titleTextField")));
    	return titleLocator.getAttribute("value");
    }
    
    public String getQuestionDetailDescription() {
    	descriptionLocator = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("descriptionTextField")));
    	return descriptionLocator.getAttribute("value");
    }
}
