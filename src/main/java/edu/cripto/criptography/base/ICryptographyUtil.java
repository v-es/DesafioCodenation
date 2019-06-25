package edu.cripto.criptography.base;

public interface ICryptographyUtil {
	public final char[] charPool = {
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',	'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
	};
	
	public String encrypt(String message);
	public String decrypt(String message);
	
}
