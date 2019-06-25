package edu.cripto.json;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

public class JSONDesafioAnswer extends JSONObject {
	private String filePath;

	public JSONDesafioAnswer(int fixedStep, String token,
			String encryptedMessage, String decryptedMessage, String filePath) {
		this.filePath = filePath.concat("answer.json");
		put("numero_casas", fixedStep);
		put("token", token);
		put("cifrado", encryptedMessage);
		put("decifrado", decryptedMessage);
		String resumo = DigestUtils.sha1Hex(decryptedMessage).toString();
		put("resumo_criptografico", resumo);
	}
	public String getFilePath() {
		return filePath;
	}
}
