package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.tls.crypto.impl.TlsStreamCipherImpl;

/**
 * A basic wrapper for a JCE Cipher class to provide the needed stream cipher functionality for TLS.
 */
public class JceStreamCipherImpl
    implements TlsStreamCipherImpl
{
    private final int cipherMode;
    private final Cipher cipher;
    private final String baseAlgorithm;

    private SecretKey key;

    public JceStreamCipherImpl(Cipher cipher, String algorithm, boolean isEncrypting)
        throws GeneralSecurityException
    {
        this.cipher = cipher;
        this.baseAlgorithm = algorithm;
        this.cipherMode = (isEncrypting) ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;
    }

    public void setKey(byte[] key, int keyOff, int keyLen)
    {
        this.key = new SecretKeySpec(key, keyOff, keyLen, baseAlgorithm);
    }

    public void init(byte[] iv, int ivOff, int ivLen)
    {
        try
        {
            cipher.init(cipherMode, key, new IvParameterSpec(iv, ivOff, ivLen));
        }
        catch (GeneralSecurityException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public int doFinal(byte[] input, int inputOffset, int inputLength, byte[] output, int outputOffset)
    {
        try
        {
            return cipher.doFinal(input, inputOffset, inputLength, output, outputOffset);
        }
        catch (GeneralSecurityException e)
        {
            throw new IllegalStateException(e);
        }
    }
}
