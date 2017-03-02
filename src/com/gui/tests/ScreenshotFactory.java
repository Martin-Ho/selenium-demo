package com.gui.tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotFactory {
	public static void getScreenshot(String filename, WebDriver driver) {
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/Users/cpenarrieta/github/selenium-demo/" + filename + ".png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
