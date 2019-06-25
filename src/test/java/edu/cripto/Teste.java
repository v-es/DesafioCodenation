package edu.cripto;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import edu.cripto.criptography.impl.CharStepCryptography;
import edu.cripto.criptography.impl.ECryptographyMode;
import edu.cripto.http_post.AnswerUpload;
import edu.cripto.json.JSONDesafioAnswer;

public class Teste {
	public static void main(String[] args) {
		CharStepCryptography converter = new CharStepCryptography(
				ECryptographyMode.DECRYPTION, 1);

		// apt url e token omitidos por questoes de seguranca
		String url = "http://echo.200please.com";
		String token = "";
		String encryptedMessage = "zpv xbou ju jo pof mjof? epft ju ibwf up gju jo 80 dpmvnot? mbssz xbmm";
		String decryptedMessage = converter.decrypt(encryptedMessage);

		JSONDesafioAnswer answer = new JSONDesafioAnswer(
				converter.getfixedStep(), token, encryptedMessage,
				decryptedMessage, "");

		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("answer.json"));
			char[] json = answer.toString(1).toCharArray();
			for (char b : json) {
				bw.write(b);
			}
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		AnswerUpload post = new AnswerUpload(answer);
		post.POST(url, token);
	}
}
