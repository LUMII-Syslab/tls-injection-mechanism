package org.bouncycastle.pqc.crypto.ntruprime;

import org.bouncycastle.crypto.CipherParameters;

/**
 * StreamLined NTRU Prime Parameter Specs
 */
public class SNTRUPrimeParameters
    implements CipherParameters
{
    public static final SNTRUPrimeParameters SNTRUP653 = new SNTRUPrimeParameters("sntrup653", 653, 4621, 288,
                                                                    994, 865, 994, 1518);
    public static final SNTRUPrimeParameters SNTRUP761 = new SNTRUPrimeParameters("sntrup761", 761, 4591, 286,
                                                                    1158, 1007, 1158, 1763);
    public static final SNTRUPrimeParameters SNTRUP857 = new SNTRUPrimeParameters("sntrup857", 857, 5167, 322,
                                                                    1322, 1152, 1322, 1999);
    public static final SNTRUPrimeParameters SNTRUP953 = new SNTRUPrimeParameters("sntrup953", 953, 6343, 396,
                                                                    1505, 1317, 1505, 2254);
    public static final SNTRUPrimeParameters SNTRUP1013 = new SNTRUPrimeParameters("sntrup1013", 1013, 7177, 448,
                                                                    1623, 1423, 1623, 2417);
    public static final SNTRUPrimeParameters SNTRUP1277 = new SNTRUPrimeParameters("sntrup1277", 1277, 7879, 492,
                                                                    2067, 1815, 2067, 3059);

    private final String name;
    private final int p;
    private final int q;
    private final int w;
    private final int rqPolynomialBytes;
    private final int roundedPolynomialBytes;
    private final int publicKeyBytes;
    private final int privateKeyBytes;

    /**
     * Construct Parameter set and initialize engine
     *
     * @param name                   name of parameter spec
     * @param p                      p is prime and degree of ring polynomial
     * @param q                      q is prime and used for irreducible ring polynomial
     * @param w                      w is a positive integer less than p
     * @param rqPolynomialBytes      rqPolynomialBytes is bytes taken to represent the ring polynomial
     * @param roundedPolynomialBytes roundedPolynomialBytes is bytes taken to represent rounded polynomial
     * @param publicKeyBytes         Public Key byte length
     * @param privateKeyBytes        Private Key byte length
     */
    private SNTRUPrimeParameters(String name, int p, int q, int w, int rqPolynomialBytes, int roundedPolynomialBytes, int publicKeyBytes, int privateKeyBytes)
    {
        this.name = name;
        this.p = p;
        this.q = q;
        this.w = w;
        this.rqPolynomialBytes = rqPolynomialBytes;
        this.roundedPolynomialBytes = roundedPolynomialBytes;
        this.publicKeyBytes = publicKeyBytes;
        this.privateKeyBytes = privateKeyBytes;
    }

    public String getName()
    {
        return name;
    }

    public int getP()
    {
        return p;
    }

    public int getQ()
    {
        return q;
    }

    public int getW()
    {
        return w;
    }

    public int getPublicKeyBytes()
    {
        return publicKeyBytes;
    }

    public int getPrivateKeyBytes()
    {
        return privateKeyBytes;
    }

    public int getRqPolynomialBytes()
    {
        return rqPolynomialBytes;
    }

    public int getRoundedPolynomialBytes()
    {
        return roundedPolynomialBytes;
    }
}
