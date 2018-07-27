import java.io.*;
import java.util.Scanner;
public class Encryption {
	public static void main(String[] args) throws IOException {
		//FileWriter write = new FileWriter("./txt/encrypted.txt", true);
		FileWriter enc = new FileWriter("./txt/encrypted.txt", true);//encryptor
		FileWriter dec = new FileWriter("./txt/decrypted.txt", true);//decryptor
		char[] msg={}, key, keyMsg, encr;
		int ln1=1, ln2=2;
		//.write("hello world!");
		//.flush();
		//.close();

		//  Message
		//+ KeyMess
		//---------
		//  Encrypt(ion) & Key
		
		clearFile("./txt/encrypted.txt");
		clearFile("./txt/decrypted.txt");
		
		while (true) {
			try {
				msg = readMsgLn("./txt/messages.txt", ln1);
			} catch (java.util.NoSuchElementException e) {
				break;
			}
			key = readMsgLn("./txt/messages.txt", ln2);
			keyMsg = new char[msg.length];
			//System.out.println(keyMsg.length);
			//System.out.println(key.length);
			//System.out.println(msg.length);
			for (int i=0; i<key.length; i++) {
				keyMsg[i]=key[i];
			}
			//System.out.println((msg.length-key.length));
			for (int i=key.length; i<msg.length; i++) {
				keyMsg[i]=msg[i-key.length];
			}
			encr = encrypt(msg, keyMsg);//encrypt message
			for (char c : encr) {//write to encrypted.txt
				enc.write(c);
			}
			enc.write("\n");
			for (char c : key) {
				enc.write(c);
			}
			enc.write("\n");
			enc.flush();
			ln1+=2;
			ln2+=2;
		}
		enc.close();
		ln1=0;
		ln2=0;
		//while (true) {
			try {
				encr = readMsgLn("./txt/encrypted.txt", ln1);
			} catch (java.util.NoSuchElementException e) {
				break;
			}
			key = readMsgLn("./txt/encrypted.txt", ln2);
			
			ln1+=2;
			ln2+=2;
		//}
		dec.close();
	}
	static char[] readMsgLn(String filePath, int lnNum) throws IOException {
		//Read a line as char[]
		Scanner read = new Scanner(new FileReader(filePath));
		String ln="";
		for (int i=0; i<lnNum; i++) {
			ln = read.nextLine();
		}
		//System.out.println(ln);
		char[] msg = new char[ln.length()];
		lnNum=0;
		for (char ch : msg) {
			msg[lnNum]=ln.charAt(lnNum);
			lnNum++;
		}
		return msg;
	}

	static char[] encrypt(char[] msg, char[] keyMsg) {
		char[] alf = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		int m;
		int km;
		for (int i=0; i<msg.length; i++) {
			m=msg[i]-65; //got number equivalent 1-26
			km=keyMsg[i]-65;
			msg[i]=alf[m+km];
		}
		return msg;
	}

	static char[] decrypt(char[] encr, char[] key) {
		return encr;
	}
	static void clearFile(String filePath) throws IOException {
		FileWriter justCreatedToClearFile = new FileWriter(filePath);
	}
}
/*
A 65
-65 -> A 0
hi there
1 one
2 two
3 three
4 four
5 five 
6 six
7 seven
8 eight
9 nine
10 ten
SGZVQBUQAFRWSLC
ACM
*/