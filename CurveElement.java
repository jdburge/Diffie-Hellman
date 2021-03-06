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

public class CurveElement implements Element {
	
    // data
    private BigInteger x;
    private BigInteger y;
    private boolean isId;
    private EllipticCurve ec;


    public CurveElement(BigInteger[] info, EllipticCurve ec) {
	this.x = info[0];
	this.y = info[1];
	BigInteger zero = new BigInteger("0");
	if(info[2].equals(zero))
	    this.isId = false;
	else
	    this.isId = true;
	this.ec = ec;
    }

    public CurveElement addition(CurveElement x) {
	return ec.addition(this,x);
    }

    // PRECONDITION: n >= 0
    public CurveElement selfAddition(BigInteger n) {
	BigInteger zero = new BigInteger("0");
	if(n.equals(zero))
	    return this;

	BigInteger two = new BigInteger("2");
	
	BigInteger evenOdd = n.mod(two);

	if(evenOdd.equals(zero))
	    return ec.addition(this,this).selfAddition(n.divide(two));
        else 
	    return ec.addition(this,(ec.addition(this,this)).selfAddition(n.divide(two)));
    }

    public boolean isId() {
	return isId;
    }
    
    public BigInteger getX() {
	return x;
    }

    public BigInteger getY() {
	return y;
    }

    public EllipticCurve getGroup() {
	return ec;
    }

    public void print() {
	System.out.println("X: "+x);
	System.out.println("Y: "+y);
    }

}