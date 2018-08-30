/*
 * This is the Program
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class DeathGUI {
	JPasswordField jpf;
	JTextField jtf;
	JLabel encLab, pinLab, mouseCoor;
	JButton pinBtn, encBtn, decBtn;
	
	private final int wrapScope = 95; 	 // the entire scope of characters used
	private int pin; 				  	 // number of shifts
	private int dKey;
	private boolean textIsLegal = false; // if text is in bounds of the wrapScope
	private boolean pinIsLegal = false;
	private String encryption = "";
	private String plainText;	
	
	
	DeathGUI() {
		JFrame jf = new JFrame("Death Coder");
		jf.setSize(100, 250);
		jf.setLayout(new FlowLayout());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// PIN Field
		jpf = new JPasswordField(5);
		
		// PIN Button
		pinBtn = new JButton("OK");
		pinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				getPin();
				if(pinIsLegal) { pinLab.setText("PIN Accepted"); }
				else 		   { pinLab.setText("PIN Rejected!"); }
			}
		});
		
		// PIN Label
		pinLab = new JLabel("Enter PIN Here");		

		// TextField
		jtf = new JTextField(10);
		
		// Encryption Label
				encLab = new JLabel("Type Plaintext");
		
		// Encryption Button
		encBtn = new JButton("Encrypt");
		encBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				checkPlainText();
				if(textIsLegal && pinIsLegal) {
					encrypt(plainText, pin);
					encLab.setText("Encryption:" + encryption + ":");					
					jtf.setText(null);
					textIsLegal = false;
					encryption = "";					
				}
				else { encLab.setText("Enter Plaintext"); }
			}
		});
		
		// Decrypt Button
		decBtn = new JButton("Decrypt");
		decBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				getDKey();
				if(pinIsLegal) new DecipherFrame(dKey);			
				else pinLab.setText("Invalid PIN");
			}
		});
		
		//mouseCoor = new JLabel("mouse");
		//mouseCoor.set
		
		jf.add(jpf);
		jf.add(pinLab);
		jf.add(pinBtn);
		jf.add(jtf);
		jf.add(encLab);
		jf.add(encBtn);				
		jf.add(decBtn);
		//jf.add(mouseCoor, 0, 0);
		
		
		/*jf.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent me) {
				showStatus(me.getX(), me.getY());
			}
			
			/*public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}*/
		//});
		
		jf.setVisible(true);
	}
	
	private void getDKey() {
		if(pinIsLegal) {
			dKey = wrapScope - pin;
		}		
	}
	
	private void checkPlainText() {		
		plainText = jtf.getText();
		for(int i=0; i < plainText.length(); i++) {
			textIsLegal = checkCharacter(plainText.charAt(i));
			if(!textIsLegal) { break; }
		}	
	}
	
	private boolean checkCharacter(char ch) {
		if((ch >= ' ') && (ch <= '~')) return true;
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private void getPin() {
		String pinStr;
		//pin = scnr.nextInt();
		try {
			pinStr = jpf.getText();
			pin = Integer.parseInt(pinStr);
			if((pin >= 32) && (pin <= 126)) {
				pinIsLegal = true;
			}
			else pinIsLegal = false;
		}
		catch(Exception exc) {
			pinLab.setText("Exception");//System.out.println(exc);
		}		
	}
	
	private void encrypt(String codeToEncrypt, int shiftNum) {
	 	for (int i=0; i < codeToEncrypt.length(); i++) { 
	 		char ch = encryptChar(codeToEncrypt.charAt(i), shiftNum); 
	 	encryption += ch; 
	 	}
	}	
	
	private char encryptChar(char ch, int key) { 														 
		ch = (char) (' ' + (ch - ' ' + key) % wrapScope);    // 95 is the # of columns wrapped around
		return ch; 
	}                         
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new DeathGUI(); 
			}
		});
	}	
}