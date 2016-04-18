
/**
 * Program to implement the Diffie Hellman algorithm
 * @author Hunter Dinkins, Kei Takazawa
 * Spring 2016
 * Cryptography Seminar, Dr. Lovett
 */

import java.util.*;
import java.io.*;
import java.math.*;

public class DiffieHellman {
	private BigInteger modulus, gExpA, key, b;

	public DiffieHellman() {
		try {
			Scanner readPub = new Scanner(new FileInputStream("PublicKeys"));
			modulus = new BigInteger(readPub.nextLine());
			gExpA = new BigInteger(readPub.nextLine());
			b = new BigInteger((int) (Math.random() * 512) + 512, new Random());

			System.out.println("modulus is " + modulus.toString());
			System.out.println("gExpA is " + gExpA.toString());
			System.out.println("b is " + b.toString());
			
			
			

			computeKey();
			System.out.println("this is the key " + key.toString());

			try {
				PrintWriter prv = new PrintWriter("EncDecKeys");
				prv.println(key.toString());
				prv.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
			
			readPub.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	private void computeKey() {
		key = gExpA.modPow(b, modulus);
	}

	public BigInteger getKey() {
		return key;
	}

	public BigInteger getMod() {
		return modulus;
	}

}