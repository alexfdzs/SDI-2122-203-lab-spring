package com.uniovi.sdi2122203spring;

import com.uniovi.sdi2122203spring.pageobjects.*;
import com.uniovi.sdi2122203spring.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2122203SpringApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\alexf\\Desktop\\UNIVERSIDAD\\Tercer Curso\\Segundo Cuatri\\Sistemas Distribuidos e Internet\\Laboratorio\\Sesion 5\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    //static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";

    // Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {}

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
    //Cerramos el navegador al finalizar las pruebas
    driver.quit();
    }

    //------------------------------------------------------------------------
    //---------------------------TESTS METHODS--------------------------------
    //------------------------------------------------------------------------

    @Test
    @Order(1)
    void PR01A() {
        PO_HomeView.checkWelcomeToPage(driver, PO_Properties.getSPANISH());
    }

    @Test @Order(2)
    void PR01B() {
        List<WebElement> welcomeMessageElement = PO_HomeView.getWelcomeMessageText(driver, PO_Properties.getSPANISH());
        Assertions.assertEquals(welcomeMessageElement.get(0).getText(),
                PO_HomeView.getP().getString("welcome.message", PO_Properties.getSPANISH()));
    }

    //PR02. Opción de navegación. Pinchar en el enlace Registro en la página home
    @Test
    @Order(3) public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
    }

    //PR03. Opción de navegación. Pinchar en el enlace Identifícate en la página home
    @Test
    @Order(4) public void PR03() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
    }

    //PR04. Opción de navegación. Cambio de idioma de Español a Inglés y vuelta a Español
    @Test
    @Order(5)
    public void PR04() {
        PO_HomeView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish",
                PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
    }

    //PR05. Prueba del formulario de registro. registro con datos correctos
    @Test
    @Order(6)
    public void PR05() {
        //Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        //Rellenamos el formulario.
        PO_SignUpView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");

        //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
        String checkText =  "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR06A. Prueba del formulario de registro. DNI repetido en la BD
    @Test
    @Order(7)
    public void PR06A() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.dni.duplicate",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de DNI repetido.
        String checkText = PO_HomeView.getP().getString("Error.signup.dni.duplicate",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06B. Prueba del formulario de registro. Nombre corto.
    @Test
    @Order(8)
    public void PR06B() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");
        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.signup.name.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.name.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06C. Prueba del formulario de registro. Nombre largo.
    @Test
    @Order(9)
    public void PR06C() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990B", "JoseManuelAlvarezNombreMuyLargo", "Perez", "77777", "77777");
        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.signup.name.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.name.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06D. Prueba del formulario de registro. Dni corto.
    @Test
    @Order(10)
    public void PR06D() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "69B", "Josefo", "Perez", "77777", "77777");
        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.signup.dni.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.dni.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06E. Prueba del formulario de registro. Dni largo.
    @Test
    @Order(11)
    public void PR06E() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "694621862132168413216498651B", "Josefo", "Perez", "77777", "77777");
        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.signup.dni.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.dni.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06F. Prueba del formulario de registro. Apellido corto.
    @Test
    @Order(12)
    public void PR06F() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "69999999B", "Josefo", "Salé", "77777", "77777");
        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.signup.lastName.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.lastName.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06G. Prueba del formulario de registro. Apellido largo.
    @Test
    @Order(13)
    public void PR06G() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "69999999B", "Josefo", "PerezRamírezApellidoMuyLargo", "77777", "77777");
        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.signup.lastName.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.lastName.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06H. Prueba del formulario de registro. Contraseña corta.
    @Test
    @Order(14)
    public void PR06H() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "69999999B", "Josefo", "Pérez", "777", "777");
        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.signup.password.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.password.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06I. Prueba del formulario de registro. Contraseña larga.
    @Test
    @Order(15)
    public void PR06I() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "69999999B", "Josefo", "Pérez",
                "77777777777777777777777777777", "77777777777777777777777777777");
        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.signup.password.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.password.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06J. Prueba del formulario de registro. Segunda contraseña no coincide.
    @Test
    @Order(16)
    public void PR06J() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "69999999B", "Josefo", "Pérez",
                "77777", "456827");
        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.signup.passwordConfirm.coincidence",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.passwordConfirm.coincidence",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    @Test
    @Order(17)
    public void PR07() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText =  "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);

        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR08. Prueba de identificación válida con ROL profesor.
    @Test
    @Order(18)
    public void PR08() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999993D", "123456");
        //Comprobamos que entramos en la pagina privada de profesor.
        //Para ello comprobamos que hay un botón 'eliminar' (solo disponible para profesores).
        String checkText =  "eliminar";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);

        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR09. Prueba de identificación válida con ROL administrador.
    @Test
    @Order(19)
    public void PR09() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999988F", "123456");
        //Comprobamos que entramos en la pagina privada de administrador.
        //Para ello comprobamos que se puede acceder al 'menu usuarios' (solo disponible para admins).
        String checkText = "Gestión de usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);

        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR10. Prueba de identificación inválida con ROL alumno.
    @Test
    @Order(20)
    public void PR10() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999988F", "654321");
        //Comprobamos que seguimos de nuevo en el formulario de login.
        String checkText = "Identifícate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);

        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR11(): Prueba de indentificación válida y posterior desconexión
    @Test
    @Order(21)
    public void PR11() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText =  "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        //Desconectamos la sesión.
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

        //Comprobamos que estamos en el formulario de login.
        checkText = "Identifícate";
        result = PO_View.checkElementBy(driver, "text", checkText);

        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse usando el rol de estudiante
    @Test
    @Order(1)
    public void PR12() {
        PO_PrivateView.login(driver, "99999990A", "123456", "Notas del usuario");

        //Contamos el número de filas de notas
        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free",
                "//tbody/tr", PO_View.getTimeout());
        Assertions.assertEquals(4, markList.size());

        //Ahora nos desconectamos comprobamos que aparece el menú de registrarse
        PO_PrivateView.logout(driver);
    }

    //PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion = Nota A2.
    @Test
    @Order(2)
    public void PR13() {
        PO_PrivateView.login(driver, "99999990A", "123456", "99999990A");

        //SeleniumUtils.esperarSegundos(driver, 1);
        //Contamos las notas
        By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]");
        driver.findElement(enlace).click();

        //Esperamos por la ventana de detalle
        String checkText = "Detalles de la nota";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText,result.get(0).getText());

        //Ahora nos desconectamos comprobamos que aparece el menú de registrarse
        PO_PrivateView.logout(driver);
    }

    //P14. Loguearse como profesor y Agregar Nota A2. //P14. Esta prueba podría encapsularse mejor ...
    @Test
    @Order(3)
    public void PR14() {

        PO_PrivateView.login(driver, "99999977E", "123456", "99999977E");

        PO_PrivateView.clickOptionMenu(driver, "marks-menu", "mark/add");

        //Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
        String checkText = "Nota Nueva 1";
        PO_PrivateView.fillFormAddMark(driver, 3, checkText, "8");

        PO_PrivateView.goToPage(driver, 3);

        //Comprobamos que aparece la nota en la página
        List<WebElement> elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());

        //Ahora nos desconectamos comprobamos que aparece el menú de registrarse
        PO_PrivateView.logout(driver);
    }

    @Test
    @Order(4)
    public void PR15() {

        PO_PrivateView.login(driver, "99999977E", "123456", "99999977E");

        PO_PrivateView.clickOptionMenu(driver, "marks-menu", "mark/list");

        PO_PrivateView.goToPage(driver, 3);

        //Esperamos a que aparezca la Nueva nota en la última página
        // Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
        elements.get(0).click();
        //Volvemos a la última página
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        elements.get(3).click();

        //Y esperamos a que NO aparezca la última "Nueva Nota 1"
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Nota Nueva 1",PO_View.getTimeout());

        //Ahora nos desconectamos comprobamos que aparece el menú de registrarse
        PO_PrivateView.logout(driver);
    }
}
