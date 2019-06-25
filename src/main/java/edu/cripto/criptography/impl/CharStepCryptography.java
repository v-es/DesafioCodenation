package edu.cripto.criptography.impl;

import edu.cripto.criptography.base.ICryptographyUtil;

public class CharStepCryptography implements ICryptographyUtil {
	private ECryptographyMode cryptoMode;
	private final int fixedStep;
	final int charPoolSize;

	public CharStepCryptography(ECryptographyMode cryptoMode, int fixedStep) {
		this.cryptoMode = cryptoMode;
		this.fixedStep = fixedStep;
		this.charPoolSize = ICryptographyUtil.charPool.length;
	}

	private String convertMessage(char[] msg) {
		final int msgSize = msg.length;
		for (int i = 0; i < msgSize; ++i) {
			final char msgChar = msg[i];
			msg[i] = fetchInPool(msgChar);
		}
		return String.valueOf(msg);
	}

	private char fetchInPool(char msgChar) {
		
		for (int i = 0; i < charPoolSize; ++i) {
			final char poolChar = ICryptographyUtil.charPool[i];
			if (msgChar == poolChar) {
				msgChar = replaceChar(i);
			}
		}
		return msgChar;
	}

	private char replaceChar(int pos) {
		char c = '#';
		try {
			switch (cryptoMode) {
			case ENCRYPTION:
				pos = pos + fixedStep;
			case DECRYPTION:
				pos = pos - fixedStep;
			}
			if (pos > this.charPoolSize) {
				throw new ArrayIndexOutOfBoundsException();
			}
			if (pos < 0) {
				throw new ArrayIndexOutOfBoundsException();
			}
			c = ICryptographyUtil.charPool[pos];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Um caracter nao pode ser convertido.");
		}
		return c;
	}

	public int getfixedStep() {
		return this.fixedStep;
	}

	public String encrypt(String message) {
		return convertMessage(message.toCharArray());
	}

	public String decrypt(String message) {
		return convertMessage(message.toCharArray());
	}
}
