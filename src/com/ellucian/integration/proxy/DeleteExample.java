package com.ellucian.integration.proxy;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DeleteExample {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String jwt = "";
		String apiKey = "API KEY";
		String id = "8734be28-9f7e-4d31-963f-654712f37b4b";
		
		//get new token
		HttpPost httpPost = new HttpPost("https://integrate.elluciancloud.com/auth");
		httpPost.addHeader("authorization", "Bearer " + apiKey);
		CloseableHttpResponse tokenResponse = httpclient.execute(httpPost);

		try {
		    HttpEntity entity = tokenResponse.getEntity();
		    jwt = "Bearer " + EntityUtils.toString(entity);
		    System.out.println(jwt);
		} finally {
			tokenResponse.close();
		}
		
		//PUT a buildings resource
		HttpDelete httpdelete = new HttpDelete("https://integrate.elluciancloud.com/api/buildings/" + id);
		httpdelete.addHeader("authorization", jwt);
		CloseableHttpResponse proxyResponse = httpclient.execute(httpdelete);

		try {
		    System.out.println("Delete Status Code: " + proxyResponse.getStatusLine().getStatusCode());
		} finally {
			proxyResponse.close();
		}
	}

}
