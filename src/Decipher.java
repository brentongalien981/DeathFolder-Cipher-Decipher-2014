import acm.program.*;

public class Decipher extends ConsoleProgram {
	String encryption = "";
	String decryption = "";
	private final int pin = 43;
	private int dKey = 52;		// Decryption Key (wrapScope - pin)
	private int wrapScope = 95;
	
	
	public void run() {
		getEncryption();
		decrypt(encryption, dKey);
		//showDecryption(decryption);
		println(":" + decryption + ":");
	}
	
	private void decrypt(String str, int dKey) {
	 	for (int i=0; i<str.length(); i++) { 
	 		char ch = decryptChar(str.charAt(i), dKey); 
	 	decryption += ch; 
	 	}
	}
	
	
	private char decryptChar(char ch, int dKey) { 
		/*if(ch == ' ') {
			ch = '_';
			return ch;
		}*/													 
		ch = (char) (' ' + (ch - ' ' + dKey) % wrapScope);    // 95 is the # of columns wrapped around
		return ch;                                           // difference of largest # and smallest # plus 1
	}
	
	private String getEncryption() {		
		boolean encryptionIsLegal = false;
		
		do {
			encryption = readLine("Enter encryption:");
			
			for(int i=0; i < encryption.length(); i++) {
				encryptionIsLegal = checkCharacter(encryption.charAt(i));
				if(!(encryptionIsLegal)) {
					encryption = null;
					break;
				}
			}
		} while(encryption == null);		
		return encryption;
	}
	
	private boolean checkCharacter(char ch) {
		if((ch >= 32 & ch <= 126)) return true;
		return false;
	}
}
