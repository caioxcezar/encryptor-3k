package model;

import utils.CryptatorString;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Texto {
    private int codigo;
    private String texto;
    private String senha;
    private boolean encriptado = false;

    public Texto(int codigo, String texto, String senha) {
        this.codigo = codigo;
        this.texto = texto;
        setSenha(senha);
    }

    public Texto(int codigo, String texto, String senha, boolean encriptado) {
        this.codigo = codigo;
        this.texto = texto;
        this.encriptado = encriptado;
        this.senha = senha;
    }

    public Texto(String texto, String senha) {
        this.texto = texto;
        setSenha(senha);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTexto() {
        return texto;
    }

    private void setSenha(String senha) {
        if (encriptado == false) {
            this.senha = String.format("%16s", senha);
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setTexto(String texto) {
        if (encriptado == false) {
            this.texto = texto;
        }
    }

    public boolean isEncriptado() {
        return encriptado;
    }

    public void encriptografar() throws NoSuchPaddingException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {

        if (encriptado == false) {
            this.texto = CryptatorString.encriptografar(texto, senha);
            encriptado = true;
        }
    }

    public void descriptografar() throws NoSuchPaddingException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        if (encriptado == true) {
            this.texto = CryptatorString.descriptografar(texto, senha);
            encriptado = false;
        }
    }
}
