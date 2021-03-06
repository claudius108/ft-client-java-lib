package ro.kuberam.libs.java.ftclient.FTP;

import java.net.URI;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Assert;
import org.junit.Test;

import ro.kuberam.libs.java.ftclient.Connect;
import ro.kuberam.libs.java.ftclient.Disconnect;
import ro.kuberam.libs.java.ftclient.FTClientAbstractTest;
import ro.kuberam.libs.java.ftclient.RetrieveResource;

public class RetrieveBinaryResourceTest extends FTClientAbstractTest {

	@Test
	public void retrieveBinaryResourceFromFtpServer() throws Exception {

		FTPClient connection = Connect.connect(new URI(connectionProperties.getProperty("ftp-server-connection-url")), "");

		String actualResult = getBinaryResourceAsBase64String(RetrieveResource.retrieveResource(connection, "/dir-with-rights/image-with-rights.gif"));

		Disconnect.disconnect(connection);

		String expectedResult = getBinaryResourceAsBase64String("../image-with-rights.gif");

		Assert.assertTrue(expectedResult.equals(actualResult));
	}
}
