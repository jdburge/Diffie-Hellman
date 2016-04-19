/**
 * Jeff Burge, Hunter Dinkins, Kei Takazawa
 * Elliptic Curve Group
 * Utilized for Diffie-Hellman Encryption
 * Wheaton College Spring 2016
 * 4/18/16
 */

import java.util.*;
import java.io.*;
import java.math.*;

public class EllipticCurve implements Group{

    // data
    private int p;
    private BigInteger a;
    private BigInteger b;
    // Generating point?

    public EllipticCurve(int p, BigInteger a, BigInteger b) {
	this.p = p;
	this.a = a;
	this.b = b;
	// Setting generating point?
    }

    public CurveElement createElement(BigInteger[] info) {
	return new CurveElement(info,this);
    }

    public CurveElement addition(CurveElement x, CurveElement y) {
	// this is the actual function to fill in
	
    }

    public CurveElement getKey() {

    }

}