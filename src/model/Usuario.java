package model;

import dao.TextoDao;
import utils.EncryptString;
import utils.PasswordEncrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario {
    private EncryptString cryptator = new PasswordEncrypt();
    private String nome, senha;
    private ArrayList<Texto> textos;

    public Usuario(String nome, String senha) throws Exception {
        this.nome = nome;
        this.senha = cryptator.criptografar(String.format("%16s", senha));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException {
        return cryptator.descriptografar(senha);
    }

    public void setSenha(String senha) throws Exception {
        for(Texto t : textos){
            t.descriptografar();
            t.setSenha(senha);
            t.criptografar();
        }
        this.senha = cryptator.criptografar(String.format("%16s", senha));
    }

    public ArrayList<Texto> getTextos() {
        return textos;
    }

    public void rmTexto(Texto texto) throws SQLException, ClassNotFoundException {
        TextoDao.apagar(texto);
        this.textos.remove(texto);
    }

    public void addTexto(Texto texto) throws Exception {
        texto.criptografar();
        TextoDao.salvar(texto);
        this.textos.add(texto);
    }
}
