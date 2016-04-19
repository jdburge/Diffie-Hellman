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
    private BigInteger x;
    private BigInteger y;

    private BigInteger exp;

    public EllipticCurve(int p, BigInteger a, BigInteger b, BigInteger x, BigInteger y) {
	this.p = p;
	this.a = a;
	this.b = b;
	// Setting generating point?
	this.x = x;
	this.y = y;


	exp = new BigInteger((int)(Math.random()*512)+512, new Random());
    }

    public CurveElement createElement(BigInteger[] info) {
	return new CurveElement(info,this);
    }

    public CurveElement addition(CurveElement x, CurveElement y) {
	// this is the actual function to fill in
	
	BigInteger xX = x.getX();
	BigInteger xY = x.getY();
	BigInteger yX = y.getX();
	BigInteger yY = y.getY();
	BigInteger m;
	BigInteger Rx;
	BigInteger Ry;
	BigInteger a = x.getA();
	BigInteger p = x.getP();
	
	if(x.isId()){
		return y;
	}else if(y.isId()){
		return x;
	}else if(xX.equals(yX) && xY.equals(yY)){ //if they are same
		// I need the "a" from the curve parameters
		
		m = (xX.pow(2).multiply(new BigInteger("3")).add(a)).divide(xY.multiply(new BigInteger("2")));
		//m = (3*xX^2 + a)/(2*xY);
		
		Rx = (m.pow(2)).subtract(xX.multiply(new BigInteger("2")));
		//Rx = m*m - 2*xX;
		Ry = (m.multiply(xX.subtract(Rx))).subtract(xY);
		//Ry = m*(xX-Rx) - xY;
		
		Rx = Rx.mod(p);
		Ry = Ry.mod(p);
		
		return new CurveElement(new BigInteger[2] {Rx,Ry} , x.getGroup());
		
		
		
	}else{ //regular cases
		
		m = (xy.subtract(yY)).divide(xX.subtract(yX));
		//m = (xY-yY)/(xX-yX);
		
		Rx = m.pow(2).subtract(xX.add(yX));
		//Rx = m*m - xX - yX;
		Ry = (m.multiply(xX.subtract(Rx))).subtract(xY);
		//Ry = m*(xX-Rx) - xY;
		
		Rx = Rx.mod(p);
		Ry = Ry.mod(p);
		
		return new CurveElement(new BigInteger[2] {Rx,Ry} , x.getGroup());
		
	}

    }

    public CurveElement getKey() {
	BigInteger[] info = new BigInteger[3];
	info[0] = x;
	info[1] = y;
	info[2] = new BigInteger("0");
	CurveElement g = createElement(info);

	return g.selfAddition(exp);
    }

}