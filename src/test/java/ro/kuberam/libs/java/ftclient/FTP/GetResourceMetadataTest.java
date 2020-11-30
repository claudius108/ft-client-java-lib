package ro.kuberam.libs.java.ftclient.FTP;

import javax.xml.transform.stream.StreamResult;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Assert;
import org.junit.Test;

import ro.kuberam.libs.java.ftclient.Disconnect;
import ro.kuberam.libs.java.ftclient.FTClientAbstractTest;
import ro.kuberam.libs.java.ftclient.GetResourceMetadata;

public class GetResourceMetadataTest extends FTClientAbstractTest {

	@Test
	public void test() throws Exception {

		FTPClient remoteConnection = initializeFtpConnection(connectionProperties
				.getProperty("ftp-server-connection-url"));
		String remoteResourcePath = "/dir-with-rights/image-with-rights.gif";
		StreamResult resourceMetadata = GetResourceMetadata.getResourceMetadata(remoteConnection,
				remoteResourcePath);
		Disconnect.disconnect(remoteConnection);
		String resourceMetadataString = resourceMetadata.getWriter().toString();
		
		System.out.println(resourceMetadataString);
		
		Assert.assertTrue(resourceMetadataString.contains("name=\"dir-with-rights\""));
		Assert.assertTrue(resourceMetadataString.contains("type=\"directory\""));
		Assert.assertTrue(resourceMetadataString.contains("absolute-path=\"/dir-with-rights/image-with-rights.gif\""));
		Assert.assertTrue(resourceMetadataString.contains("size=\"4096\""));
		Assert.assertTrue(resourceMetadataString.contains("human-readable-size=\"4 KB\""));
		Assert.assertTrue(resourceMetadataString.contains("user=\"1003\""));
		Assert.assertTrue(resourceMetadataString.contains("user-group=\"1003\""));
		Assert.assertTrue(resourceMetadataString.contains("permissions=\"drwxrwxrwx\""));
		Assert.assertTrue(resourceMetadataString.contains("checksum=\"0\""));
	}

}
