/* 
 * File: CaesarCipher.java 
 * ----------------------- 
 * This program translates a line of text into its Caesar cipher 
 * form. 
 */ 


import acm.program.*; 

public class DeathCode extends ConsoleProgram { 
	 public void run() { 
		 println("This program uses a Caesar cipher for encryption.");
		 int key;
		 
		 do {
			 key = readInt("Enter encryption key: ");
		 } while(!(key >= 32 | key <= 126));
		
		 String plaintext = readLine("Plaintext: "); 
		 String ciphertext = encryptCaesar(plaintext, key); 
		 println("Ciphertext: " + ciphertext); 
	 } 
	 
	 
 /* 
 * Encrypts a string by adding the value of key to each character. 
 * The first line makes sure that key is always positive by 
 * converting negative keys to the equivalent positive shift. 
 */ 
	 
	 
	 private String encryptCaesar(String str, int key) { 		 
		 String result = ""; 
		 for (int i = 0; i < str.length(); i++) { 
			 char ch = encryptCharacter(str.charAt(i), key); 
			 result += ch; 
		 } 
		 
		 return result; 
	 } 
	 
	 
 /* 
 * Encrypts a single character using the key given. This 
 * method assumes the key is non-negative. Non-letter 
 * characters are returned unchanged. 
 */ 
	 
	 
	 private char encryptCharacter(char ch, int key) { 
		 if (ch != ' ') { 
			 ch = (char) (' ' 
			 + (ch - ' ' + key) % 95); // 95 is the # of columns wrapped around
		 } 							   // difference of largest # and smallest # plus 1
		 return ch; 
	 } 
} 