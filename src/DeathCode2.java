/*
 * This uses the acm Library.
 */

import acm.program.*;

public class DeathCode2 extends ConsoleProgram {
	private int pin;
	private final int wrapScope = 95;
	
	private String encryption = "";
	private String plainText;
	
	private boolean textIsLegal = false;
	
	public void run() {
		getPin();
		plainText = getPlainText();		
		encrypt(plainText, pin);
		showEncryption();
	}
	
	
	private void showEncryption() {
		println("Encryption:" + encryption + ":");
	}
	
	private void encrypt(String str, int pin) {
	 	for (int i=0; i<str.length(); i++) { 
	 		char ch = encryptCharacter(str.charAt(i), pin); 
	 	encryption += ch; 
	 	}
	}
	
	
	private char encryptCharacter(char ch, int key) { 
		/*if(ch == ' ') {
			ch = '_';
			return ch;
		}*/														 
		ch = (char) (' ' + (ch - ' ' + key) % wrapScope);    // 95 is the # of columns wrapped around
		return ch;                                           // difference of largest # and smallest # plus 1
	}
	
	private String getPlainText() {		
		String text;
		
		do {
			text = readLine("Enter Plaintext:");
			
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
	
	private boolean checkCharacter(char ch) {
		if((ch >= 32 & ch <= 126)) return true;
		return false;
	}
	
	
	private void getPin() {
		do {
			pin = readInt("Enter PIN:");
			if(!(pin >= 32 & pin <= 126)) {
				println("Invalid PIN!");
			}
		} while(!(pin >= 32 & pin <= 126));
	} 
}