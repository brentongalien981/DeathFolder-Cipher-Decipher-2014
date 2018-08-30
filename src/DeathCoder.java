/*
 * This is for the encryption process.
 */

import java.util.Scanner;

public class DeathCoder {	
	private final int wrapScope = 95; // the entire scope of characters used
	private int pin; // number of shifts
	
	private String encryption = "";
	private String plainText;
	
	private boolean textIsLegal = false; // if text is in bounds of the wrapScope
	
	
	public void getPin() {
		do {
			System.out.print("Enter PIN:");
			pin = scnr.nextInt();
			if(!(pin >= 32 & pin <= 126)) {
				System.out.println("Invalid PIN!");
			}
		} while(!(pin >= 32 & pin <= 126)); 
	}
	
	public String getPlainText() {		
		String text;		
		do {
			System.out.println("Enter Plaintext:");
			text = scnr.nextLine();
			
			for(int i=0; i < text.length(); i++) {
				textIsLegal = checkCharacter(text.charAt(i));
				if(!(textIsLegal)) {
					text = null;
					break;
				}
			}
		} while(text == null);		
		return text;
	}
	
	public boolean checkCharacter(char ch) {
		if((ch >= 32 & ch <= 126)) return true;
		return false;
	}
	
	public void encrypt(String codeToEncrypt, int shiftNum) {
	 	for (int i=0; i<codeToEncrypt.length(); i++) { 
	 		char ch = encryptChar(codeToEncrypt.charAt(i), shiftNum); 
	 	encryption += ch; 
	 	}
	}	
	
	public char encryptChar(char ch, int key) { 														 
		ch = (char) (' ' + (ch - ' ' + key) % wrapScope);    // 95 is the # of columns wrapped around
		return ch;                                           // difference of largest # and smallest # plus 1
		/*if(ch == ' ') {
		ch = '_';
		return ch;
		}*/
	}
	
	public void showEncryption() {
		System.out.println("Encryption:" + encryption + ":");
	}
	
	private static Scanner scnr;
}