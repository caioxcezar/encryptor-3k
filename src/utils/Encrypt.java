package utils;

import com.mysql.jdbc.NotImplemented;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public abstract class Encrypt implements Encryptable {
    // https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#Cipher
    // https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html

    protected final String codificacao = "UTF-8";
    protected final String tranformacao = "AES/CBC/PKCS5PADDING";
    protected final String algoritimo = "AES";

    protected SecretKeySpec secretKey;
    protected Cipher cipher;
    protected byte[] chave;

    public Encrypt(String chave) throws Exception {
        setChave(chave);
        setAtributos();
    }

    protected void setAtributos() throws Exception {
        cipher = Cipher.getInstance(tranformacao);
        secretKey = new SecretKeySpec(this.chave, algoritimo);
    }

    public String getChave() throws Exception {
        return new String(chave, "UTF-8");
    }

    public void setChave(String chave) throws Exception {
        if (chave.length() != 16)
            throw new Exception("Chave devo possuir 16 caracteres");
        this.chave = chave.getBytes(codificacao);
        setAtributos();
    }
}
