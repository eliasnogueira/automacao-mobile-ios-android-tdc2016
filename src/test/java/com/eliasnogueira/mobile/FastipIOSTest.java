package com.eliasnogueira.mobile;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class FastipIOSTest {

	// app para testes
	File app = new File("app/FasTip.app");
	
	@Test
	public void calcularGorjeta() throws MalformedURLException, InterruptedException {
		
		// capacidades para execucao
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 5s");
		dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		dc.setCapability(MobileCapabilityType.VERSION, "9.3");
		
		// abrir conexao com dispostivo (sessao)
		IOSDriver<WebElement> driver = 
				new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),dc);
		
		driver.context("WEBVIEW_com.elias.hibrido");
		
		// interacao com componentes
		driver.findElement(By.className("UIATextField")).sendKeys("100");
		driver.findElementByAccessibilityId("Calculate Tip").click();
		
		// obtendo os textos da tela
		String valor = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")).getText();
		String total = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[4]")).getText();
		
		// validacao (resultado esperado)
		assertEquals("$15.00", valor);
		assertEquals("$115.00", total);
		
		// fechar a app
		driver.quit();
	}

}
