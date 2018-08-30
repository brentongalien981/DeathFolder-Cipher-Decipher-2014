import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DecipherFrame {
	private JTextField jtf;
	private JButton jb;
	private JLabel jl;
	
	private String decryption = "";
	private String encryption;
	private final int wrapScope = 95;
	private int dKey;
	
	DecipherFrame(int dcphrKey) {
		dKey = dcphrKey;
		
		JFrame jf = new JFrame("Decipher");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setSize(100, 100);
		jf.setLayout(new FlowLayout());
		
		// Textfield
		jtf = new JTextField(10);
		
		// Button
		jb = new JButton("Decrypt");
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				getEncryption();
				decrypt(encryption, dKey);
				showDecryption();
				jtf.setText(null);
				decryption = "";
			}
		});
		
		// Decryption
		jl = new JLabel("Decryption");
		
		jf.add(jtf);
		jf.add(jb);
		jf.add(jl);
		
		jf.setVisible(true);		
	}
	
	private void showDecryption() {
		jl.setText("Decryption:" + decryption + ":");
	}
	
	private void getEncryption() {
		encryption = jtf.getText();
	}
	
	private void decrypt(String str, int shiftNum) {
	 	for (int i=0; i<str.length(); i++) { 
	 		char ch = decryptChar(str.charAt(i), shiftNum); 
	 	decryption += ch; 
	 	}
	}	
	
	private char decryptChar(char ch, int key) { 												 
		ch = (char) (' ' + (ch - ' ' + key) % wrapScope);
		return ch;                                           
	}
}
