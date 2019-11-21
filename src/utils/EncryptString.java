package utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Base64;

public class EncryptString extends Encrypt {

    public EncryptString(String chave) throws Exception {
        super(chave);
    }

    /**
     * Criptrografar texto com base na senha informada
     *
     * @param texto
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    @Override
    public String criptografar(String texto) throws InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        IvParameterSpec ivparameterspec = new IvParameterSpec(chave);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
        byte[] cipherText = cipher.doFinal(texto.getBytes("UTF8"));
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(cipherText);
    }

    /***
     * Descriptografar texto com base na senha informada
     * @param textoCriptografado
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    @Override
    public String descriptografar(String textoCriptografado) throws InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        IvParameterSpec ivparameterspec = new IvParameterSpec(chave);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] cipherText = decoder.decode(textoCriptografado.getBytes("UTF8"));
        return new String(cipher.doFinal(cipherText), "UTF-8");
    }
}
