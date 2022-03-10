package com.uniovi.sdi2122203spring.pageobjects;

import com.uniovi.sdi2122203spring.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView{

    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
        //Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);

        //Seleccionamos el alumnos userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);

        //Rellenemos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);

        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);

        By boton = By.className("btn"); driver.findElement(boton).click();
    }

    static public void login(WebDriver driver, String username, String password, String checkText){
        //Comprobamos que entramos en la pagina privada de Alumno
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, username, password);

        PO_View.checkElementBy(driver, "text", checkText);
    }

    static public void logout(WebDriver driver){
        //Ahora nos desconectamos y comprobamos que aparece el menú de registrarse
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);;
    }

    static public void goToPage(WebDriver driver, int numberPage){
        //Esperamos a que se muestren los enlaces de paginación de la lista de notas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");

        //Nos vamos a una página
        elements.get(numberPage).click();
    }

    static public void clickOptionMenu(WebDriver driver, String menu, String direction){
        //Pinchamos en la opción de menú de Notas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement>  elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, '" + menu + "')]/a");
        elements.get(0).click();

        //Pinchamos en la opción de lista de notas.
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, '"+ direction + "')]");
        elements.get(0).click();
    }
}
