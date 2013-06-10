package org.opens.kbaccess.test;

import org.opens.kbaccess.test.utils.SHA1Hasher;
import com.thoughtworks.selenium.DefaultSelenium;
import java.io.File;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp.BasicDataSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.junit.*;

/**
 * 
 * @author blebail
 */
public class scenario1Test {    
    public static String xvfbDisplay;
    public static String webDriverFirefoxBin;
    public static String hostLocation;
    public static String hostBaseUrl;
    public static String dbUrl;
    public static String dbUser;
    public static String dbPassword;
    
    public static final String kbaUser = "contrib@kbaccess.org";
    public static final String kbaPassword = "Contrib1";
    public static final String kbaNewPassword = "Contrib1";
    
    public static BasicDataSource dataSource;
    public static Connection connection;
    public static WebDriver driver;
    public static DefaultSelenium selenium;
    
    private static void initProperties() {
        xvfbDisplay = System.getProperty("xvfbDisplay");
        webDriverFirefoxBin = System.getProperty("webDriverFirefoxBin");
 
        hostLocation = System.getProperty("hostLocation");
        hostBaseUrl = System.getProperty("hostBaseUrl");
        
        dbUrl = System.getProperty("dbUrl");
        dbUser = System.getProperty("dbUser");
        dbPassword = System.getProperty("dbPassword");        
    }
    
    private static void initDataBase() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setUrl(dbUrl);
        dataSource.setMaxActive(10);
        dataSource.setMaxIdle(5);
        dataSource.setInitialSize(5);
        
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void initContext() {
        try {
           SHA1Hasher sh;
           StringBuilder requestBuilder = new StringBuilder();
           requestBuilder
               .append("INSERT INTO account(EMAIL, FIRST_NAME, LAST_NAME, URL, PASSWORD, ")
               .append("SUBSCRIPTION_DATE, ID_ACCESS_LEVEL, ACTIVATED, ACTIVATION_TOKEN) VALUES ")
               .append("('admin@kbaccess.org', 'admin', 'istrator', 'http://www.admin.com', '")
               .append(SHA1Hasher.getInstance().hashAsString("admin")).append("', NOW(), 1, 1, NULL), ")
               .append("('moder@kbaccess.org', 'moder', 'ator', 'http://www.moder.com', '")
               .append(SHA1Hasher.getInstance().hashAsString("moder")).append("', NOW(), 1, 1, NULL);");
           connection.prepareStatement(requestBuilder.toString()).execute();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    
    private static void deleteContext() {
        try {
           // Delete testcases created
           StringBuilder requestContribTestcase = new StringBuilder();
           requestContribTestcase
               .append("DELETE FROM testcase WHERE ID_ACCOUNT = (SELECT ID_ACCOUNT from account WHERE EMAIL = '" + kbaUser + "');");
           connection.prepareStatement(requestContribTestcase.toString()).execute();
           
           // Delete webarchives created
           StringBuilder requestContribWebarchive = new StringBuilder();
           requestContribWebarchive
               .append("DELETE FROM webarchive WHERE ID_ACCOUNT = (SELECT ID_ACCOUNT from account WHERE EMAIL = '" + kbaUser + "');");
           connection.prepareStatement(requestContribWebarchive.toString()).execute();
           
           StringBuilder requestAccounts = new StringBuilder();
           requestAccounts
               .append("DELETE FROM account WHERE email='admin@kbaccess.org' ")
               .append("OR email='moder@kbaccess.org' ")
               .append("OR email='contrib@kbaccess.org';");
           connection.prepareStatement(requestAccounts.toString()).execute();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    
    private static void initWebDriver() {
        FirefoxBinary ffBinary = new FirefoxBinary(new File(webDriverFirefoxBin));
        if (xvfbDisplay != null) {
            ffBinary.setEnvironmentProperty("DISPLAY", xvfbDisplay);
        }
        driver = new FirefoxDriver(ffBinary, new FirefoxProfile());
       
        selenium = new WebDriverBackedSelenium(driver, hostLocation);
    }
    
    @BeforeClass
    public static void setUp() throws Exception { 
        initProperties();
        initDataBase();
        initContext();
        initWebDriver();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.close();
        driver.quit();
        deleteContext();
        dataSource.close();
    }
    
    @Test
    public void testScenario1() throws Exception {
        selenium.open(hostBaseUrl);
        selenium.waitForPageToLoad("60000");
        selenium.click("link=S'inscrire");
        selenium.type("id=subscription_email", kbaUser);
        selenium.type("id=subscription_password", kbaPassword);
        selenium.type("id=subscription_password_confirmation", kbaPassword);
        selenium.type("id=subscription_lastname", "utor");
        selenium.type("id=subscription_firstname", "contrib");
        selenium.type("id=subscription_url", "http://www.contrib.com");
        selenium.click("xpath=//div[@class='form-actions']/button");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.click("link=Se connecter");
        selenium.type("id=login_email", kbaUser);
        selenium.type("id=login_password", kbaPassword);
        selenium.click("xpath=//div[@class='form-actions']//button[.='Connexion']");
        
         try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String token = "";
        
        try {
             ResultSet rset = null;
             Statement stmt = connection.createStatement();
             rset = stmt.executeQuery("SELECT ACTIVATION_TOKEN FROM account WHERE email='" + kbaUser + "'");
            
              while (rset.next())
                   token = URLEncoder.encode(rset.getString("ACTIVATION_TOKEN"), "UTF-8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selenium.open(hostBaseUrl + "guest/activate-account.html?email=" + kbaUser + "&token=" + token);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testScenario2() throws Exception {
        selenium.open(hostBaseUrl);
        selenium.waitForPageToLoad("60000");
        
        selenium.click("link=Se connecter");
        selenium.type("id=login_email", kbaUser);
        selenium.type("id=login_password", kbaPassword);
        selenium.click("xpath=//div[@class='form-actions']//button[.='Connexion']");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        selenium.click("link=Ajouter un exemple");
        
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/table/tbody/tr[2]/td[2]/select", "value=1");
        selenium.select("xpath=//form[@id='newTestcaseCommand']/table/tbody/tr[3]/td[2]/select", "value=1");
        
        selenium.type("id=testcase_description", "test");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.click("xpath=//form[@id='newTestcaseCommand']/table/tbody/tr[5]/td[2]/input");
        selenium.click("id=testcase_existing_webarchive");
        selenium.select("xpath=//form[@id='newTestcaseCommand']/div[1]/div/div/div/select", "value=30");
        selenium.type("id=webarchive_description", "description");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.click("xpath=//div[@class='form-actions']//button[.='Ajouter']");
        selenium.click("link=Ajouter un exemple");
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/table/tbody/tr[2]/td[2]/select", "value=1");
        selenium.select("xpath=//form[@id='newTestcaseCommand']/table/tbody/tr[3]/td[2]/select", "value=2");
        selenium.type("id=testcase_description", "test");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.click("xpath=//form[@id='newTestcaseCommand']/table/tbody/tr[5]/td[2]/input");
        selenium.click("id=testcase_create_webarchive");
        selenium.type("id=webarchive_url", "http://www.kbaccess.org/");
        selenium.type("id=webarchive_description", "description");
        selenium.click("xpath=//div[@class='form-actions']//button[.='Ajouter']");
        selenium.click("link=Mes exemples");
        selenium.click("xpath=//table[@id='testcase']/tbody/tr[1]/td[1]/a/img");
        selenium.waitForPageToLoad("60000");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.click("link=Modifier");
        selenium.select("xpath=/html/body/div[2]/div/div/div[3]/div/form/div/div/select", "value=103");
        selenium.select("xpath=//form[@id='editTestcaseCommand']/div[2]/div/select", "value=2");
        selenium.type("id=testcase_description", "test test");
        selenium.click("xpath=//div[@class='form-actions']//button[.='Modifier']");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.click("link=Supprimer");
        selenium.click("xpath=//form[@id='deleteTestcaseCommand']//button[.='Confirmer']");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.click("link=Ajouter une webarchive");
        selenium.type("id=webpage_url", "http://www.kbaccess.org");
        selenium.click("id=webpage_description");
        selenium.type("id=webpage_description", "description");
        selenium.click("xpath=//div[@class='form-actions']//button[.='Archiver']");
        selenium.waitForPageToLoad("60000");
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.click("xpath=/html/body/div[2]/div/div/div/div[3]/p[2]/a");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.goBack();
        selenium.waitForPageToLoad("60000");
        selenium.click("link=Mes webarchives");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(scenario1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selenium.click("xpath=//div[@class='navbar-inner']/nav/ul[2]/li[2]/a/img");
    }
    
    @Test
    public void testScenario3() throws Exception {
        selenium.open(hostBaseUrl);
        selenium.waitForPageToLoad("60000");
        selenium.click("link=Se connecter");
        selenium.type("id=login_email", kbaUser);
        selenium.type("id=login_password", kbaPassword);
        selenium.click("xpath=//div[@class='form-actions']//button[.='Connexion']");
        selenium.click("link=Mon compte");
        selenium.click("xpath=/html/body/div[2]/div/div/div[3]/ul/li/a");
        Thread.sleep(3000);
        selenium.click("link=Mes exemples");
        Thread.sleep(3000);
        selenium.click("link=Mon compte");
        selenium.type("id=account_last_name", "Le Bail1");
        selenium.type("id=account_first_name", "Baptiste1");
        selenium.type("id=account_url", "http://www.monurl.com1");
        selenium.type("id=account_password", kbaPassword);
        selenium.click("xpath=/html/body/div[2]/div/div/div[5]/div/form/div[7]/button");
        Thread.sleep(5000);
        selenium.click("id=change-password-btn");
        selenium.type("id=current_password", kbaPassword);
        selenium.type("id=new_password", kbaNewPassword);
        selenium.type("id=account_password_confirmation", kbaNewPassword);
        selenium.click("xpath=//div[@class='form-actions']//button[.='Changer le mot de passe']");
        Thread.sleep(3000);
        selenium.click("xpath=//div[@class='navbar-inner']/nav/ul[2]/li[2]/a/img");
        selenium.type("id=login_email", kbaUser);
        selenium.type("id=login_password", kbaNewPassword);
        selenium.click("xpath=//div[@class='form-actions']//button[.='Connexion']");
//        selenium.click("xpath=//div[@class='navbar-inner']/nav/ul[2]/li[2]/a/img");
//        selenium.click("link=Mot de passe oublié ?");
//        selenium.type("id=password_lost_email", "test@test.Fr");
//        selenium.click("xpath=//div[@class='form-actions']//button[.='Envoyer']");
//        Thread.sleep(3000);
//        selenium.type("id=password_lost_email", kbaUser);
//        selenium.click("xpath=//div[@class='form-actions']//button[.='Envoyer']");
        
        /*
         * HTTP get on url with change password token
         * Change password back to kbaPassword
         */

//        selenium.open(hostBaseUrl);
//        selenium.waitForPageToLoad("60000");
//        selenium.click("link=Se connecter");
//        selenium.type("id=login_email", kbaUser);
//        selenium.type("id=login_password", kbaPassword);
//        selenium.click("xpath=//div[@class='form-actions']//button[.='Connexion']");
        selenium.click("xpath=//div[@class='navbar-inner']/nav/ul[2]/li[2]/a/img");
    }
    
    @Test
    public void testScenario4() throws Exception {
        selenium.open(hostBaseUrl);
        selenium.waitForPageToLoad("60000");
        selenium.click("link=Se connecter");
        selenium.type("id=login_email", "admin@kbaccess.org");
        selenium.type("id=login_password", "admin");
        selenium.click("xpath=//div[@class='form-actions']//button[.='Connexion']");
        selenium.click("link=Voir tous les utilisateurs");
        Thread.sleep(3000);
        selenium.click("xpath=/html/body/div[2]/div/div/div[3]/table/tbody/tr[4]/td[7]/a");
        selenium.type("id=account_last_name", "ALTINIER1");
        selenium.type("id=account_first_name", "Armony1");
        selenium.type("id=account_url", "http://acs-horizons.fr1");
        selenium.select("xpath=//form[@id='accountCommand']/div[6]/div/select", "value=3");
        selenium.select("xpath=//form[@id='accountCommand']/div[6]/div/select", "value=2");
        selenium.click("xpath=//div[@class='form-actions']//button[.='Modifier']");
        Thread.sleep(3000);
        selenium.click("link=Voir tous les exemples");
        selenium.click("xpath=/html/body/div[2]/div/div/div[2]/table/tbody/tr/td/a");
        selenium.click("link=Modifier");
        selenium.select("xpath=/html/body/div[2]/div/div/div[3]/div/form/div/div/select", "value=47");
        selenium.select("xpath=//form[@id='editTestcaseCommand']/div[2]/div/select", "value=1");
        selenium.type("id=testcase_description", "modification");
        Thread.sleep(2000);
        selenium.click("xpath=//div[@class='form-actions']//button[.='Modifier']");
        Thread.sleep(3000);
        selenium.click("link=Supprimer");
        selenium.click("xpath=//form[@id='deleteTestcaseCommand']//button[.='Confirmer']");
        Thread.sleep(2000);
        selenium.click("xpath=//div[@class='navbar-inner']/nav/ul[2]/li[2]/a/img");
        selenium.type("id=login_email", "moder@kbaccess.org");
        selenium.type("id=login_password", "moder");
        selenium.click("xpath=//div[@class='form-actions']//button[.='Connexion']");
        selenium.click("xpath=/html/body/div[2]/div/div/div[3]/table/tbody/tr/td/a");
        selenium.click("link=Modifier");
        selenium.select("xpath=/html/body/div[2]/div/div/div[3]/div/form/div/div/select", "value=48");
        selenium.select("xpath=//form[@id='editTestcaseCommand']/div[2]/div/select", "value=3");
        selenium.type("id=testcase_description", "modification");
        Thread.sleep(3000);
        selenium.click("xpath=//div[@class='form-actions']//button[.='Modifier']");
        Thread.sleep(3000);
        selenium.click("link=Supprimer");
        selenium.click("xpath=//form[@id='deleteTestcaseCommand']//button[.='Confirmer']");
        Thread.sleep(2000);
        selenium.open(hostBaseUrl + "kba/AW21/1.1.1/");
        selenium.waitForPageToLoad("60000");
        Thread.sleep(2000);
        selenium.open(hostBaseUrl + "kba/AW21/Failed/");
        selenium.waitForPageToLoad("60000");
        Thread.sleep(5000);
        selenium.open(hostBaseUrl + "kba/AW21/1.1.1/Failed/");
        selenium.waitForPageToLoad("60000");
        Thread.sleep(3000);
        selenium.open(hostBaseUrl);
        selenium.waitForPageToLoad("60000");
        selenium.click("link=Rechercher par choix");
        selenium.click("xpath=//div[@class='span10']/div[2]/form/input");
        Thread.sleep(5000);
        selenium.click("link=Recherche d'exemples");
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/div[3]/select", "value=1");
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/div[4]/select", "value=146");
        selenium.click("xpath=//div[@class='span10']/div[2]/form/input");
        Thread.sleep(3000);
        selenium.click("link=Recherche d'exemples");
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/div[3]/select", "value=11");
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/div[4]/select", "value=13");
        selenium.click("xpath=//div[@class='span10']/div[2]/form/input");
        Thread.sleep(3000);
        selenium.click("link=Recherche d'exemples");
        selenium.select("xpath=//div[@class='span10']/div[2]/form/div[1]/select", "value=12");
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/div[2]/select", "value=11");
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/div[3]/select", "value=11");
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/div[4]/select", "value=11");
        selenium.select("xpath=/html/body/div[2]/div/div/div[2]/form/div[5]/select", "value=11");
        selenium.select("xpath=//div[@class='span10']/div[2]/form/div[6]/select", "value=13");
        selenium.click("xpath=//div[@class='span10']/div[2]/form/input");
        Thread.sleep(3000);
        selenium.click("id=brand-name");
        selenium.click("xpath=//table[@id='testcase']/tbody/tr[1]/td[1]/a/img");
        selenium.click("xpath=/html/body/div[2]/div/div/div[3]/div[4]/div/ul/li/a");
        Thread.sleep(10000);
        selenium.goBack();
        selenium.click("xpath=/html/body/div[2]/div/div/div[3]/div[4]/div/ul/li[2]/a");
        Thread.sleep(5000);
        selenium.goBack();
        selenium.click("xpath=/html/body/div[2]/div/div/div[3]/div[4]/div/ul/li[3]/a");
        Thread.sleep(5000);
        selenium.click("xpath=//div[@class='navbar-inner']/nav/ul[2]/li[2]/a/img");
    }
}
