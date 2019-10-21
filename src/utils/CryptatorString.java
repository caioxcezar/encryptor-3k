package utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptatorString {
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";


    /**
     * Método para encriptografar Strings
     * @param texto
     * @param senha
     * @return textoCriptografado
     */
    public static String encriptografar(String texto, String senha ) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String textoCriptografado = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = senha.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(texto.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            textoCriptografado = encoder.encodeToString(cipherText);

        } finally {
            return textoCriptografado;
        }
    }

    /**
     *Método para descriptografar Strings
     * @param textoCriptografado
     * @param senha
     * @return textoDescriptografado
     */
    public static String descriptografar(String textoCriptografado, String senha ) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String textoDescriptografado = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = senha.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(textoCriptografado.getBytes("UTF8"));
            textoDescriptografado = new String(cipher.doFinal(cipherText), "UTF-8");
        } finally {
            return textoDescriptografado;
        }
    }
}
