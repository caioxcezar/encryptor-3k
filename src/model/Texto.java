package model;

import utils.EncryptString;
import utils.PasswordEncrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Texto {
    private EncryptString cryptator;
    private int codigo;
    private String texto;
    private boolean encriptado = false;

    public Texto(int codigo, String texto, String senha) throws Exception {
        this.codigo = codigo;
        this.texto = texto;
        this.cryptator = new EncryptString(String.format("%16s", senha));
    }

    public Texto(int codigo, String texto, String senha, boolean encriptado) throws Exception {
        this.codigo = codigo;
        this.texto = texto;
        this.encriptado = encriptado;
        this.cryptator = new EncryptString(String.format("%16s", senha));
    }

    public Texto(String texto, String senha) throws Exception {
        this.texto = texto;
        this.cryptator = new EncryptString(String.format("%16s", senha));
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTexto() {
        return texto;
    }

    public void setSenha(String senha) throws Exception {
        if (encriptado)
            throw new Exception("O texto deve estar descriptografado");
        this.cryptator.setChave(String.format("%16s", senha));
    }

    public String getSenha() throws Exception {
        return this.cryptator.getChave();
    }

    public void setTexto(String texto) throws IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, UnsupportedEncodingException {
        this.texto = texto;
        if (encriptado)
            this.criptografar();
    }

    public boolean isEncriptado() {
        return encriptado;
    }

    public void criptografar() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        if (!encriptado) {
            this.texto = cryptator.criptografar(texto);
            encriptado = true;
        }
    }

    public void descriptografar() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        if (encriptado) {
            this.texto = cryptator.descriptografar(texto);
            encriptado = false;
        }
    }
}
