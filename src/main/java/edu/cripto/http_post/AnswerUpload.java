package edu.cripto.http_post;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import edu.cripto.json.JSONDesafioAnswer;

public class AnswerUpload {
	private final JSONDesafioAnswer answerFile;

	public AnswerUpload(JSONDesafioAnswer file) {
		this.answerFile = file;
	}

	public void POST(String url, String token) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpEntity entity = MultipartEntityBuilder.create()
				.addBinaryBody("answer",
						new File(this.answerFile.getFilePath()))
				.build();

		HttpPost post = new HttpPost(url + token);
		post.setEntity(entity);

		try {
			CloseableHttpResponse postResponse = httpclient.execute(post);
			System.out.println(postResponse.getStatusLine());
			HttpEntity httpEntity = postResponse.getEntity();

			InputStream is = httpEntity.getContent();
			InputStreamReader isReader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isReader);

			String response;
			while ((response = br.readLine()) != null) {
				System.out.println(response);
			}
			EntityUtils.consume(httpEntity);
			postResponse.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
