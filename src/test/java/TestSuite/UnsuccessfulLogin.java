package TestSuite;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;

import java.io.IOException;

public class UnsuccessfulLogin extends TestUtil {

    @DataProvider(name = "wrongUsersList")
    public Object [][] getWrongUsers() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/user.csv");

    }

    @Test(dataProvider = "wrongUsersList")
    public void unsuccessfulLoginTest(String userName, String password){

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        Assert.assertTrue(loginPage.errorLogin("Error message"));
    }

}
