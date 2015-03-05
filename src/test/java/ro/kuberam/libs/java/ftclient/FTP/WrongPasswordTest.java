package ro.kuberam.libs.java.ftclient.FTP;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;

import ro.kuberam.libs.java.ftclient.Connect;
import ro.kuberam.libs.java.ftclient.ErrorMessages;
import ro.kuberam.libs.java.ftclient.FTClientAbstractTest;

public class WrongPasswordTest extends FTClientAbstractTest {

	@Test
	public void listResourcesFromFtpServer() throws Exception {

		try {
			Connect.connect(new URI(connectionProperties.getProperty("wrong-ftp-server-connection-url")),
					"");
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(e.getLocalizedMessage().equals(ErrorMessages.err_FTC005));
		}
	}

}
