package MyProject;

import org.testng.Assert;
import org.testng.annotations.Test;

import Tests.baseTest;

public class ErrorValidationsonLoginPage extends baseTest{
	@Test(groups= {"errorvalidations"})
	public void ErrorValidations() {
		String username = "rajpatti@gmail.com";
		String password = "Revariya2019";
		lp.login(username, password);
		String msg=lp.getErrorText();
		Assert.assertEquals("Incorrect email or password.", msg);
		
	}

}
