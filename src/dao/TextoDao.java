package dao;

import model.Texto;
import model.Usuario;
import utils.DaoUtils;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class TextoDao {
    public static void salvar(Texto texto, Usuario usuario) throws Exception {
        if (texto.isNotEncriptado()) {
            throw new Exception("A mensagem deve estar encriptada");
        }
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO textos_tb "
                + "(texto, senha, cod_usuario) "
                + "VALUES (?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, texto.getTexto());
            p.setString(2, usuario.getSenha());
            p.setInt(3, usuario.getCodigo());
            p.executeUpdate();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void alterar(Texto texto, Usuario usuario) throws Exception {
        if (texto.isNotEncriptado()) {
            throw new Exception("A mensagem deve estar encriptada");
        }
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE textos_tb "
                + "SET texto = ?, senha = ? "
                + "WHERE codigo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, texto.getTexto());
            p.setString(2, usuario.getSenha());
            p.setInt(3, texto.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void apagar(Texto texto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM textos_tb WHERE codigo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, texto.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    static ArrayList<Texto> listar(Usuario usuario) throws Exception {
        Connection conn = null;
        PreparedStatement p = null;
        ArrayList<Texto> textos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement("SELECT * FROM textos_tb WHERE cod_usuario = ?");
            p.setInt(1, usuario.getCodigo());
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                textos.add(instanciarTexto(rs, usuario));
            }
            return textos;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    private static Texto instanciarTexto(ResultSet rs, Usuario usuario) throws Exception {
        return new Texto(rs.getInt("codigo"), rs.getString("texto"), usuario.getSenhaDes(), true);
    }
}
