/**
 * Jeff Burge, Hunter Dinkins, Kei Takazawa
 * Element Interface
 * Utilized for Diffie-Hellman Encryption
 * Wheaton College Spring 2016
 * 4/18/16
 */

public interface Element {
    
    public Element selfAddition(BigInteger n);

    public void print();

}