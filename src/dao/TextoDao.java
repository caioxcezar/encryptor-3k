package dao;

import model.Texto;
import utils.DaoUtils;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class TextoDao {
    public static Texto get(int codigo) throws Exception {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM textos_tb WHERE codigo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, codigo);
            rs = p.executeQuery();
            rs.next();
            return instanciarTexto(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void salvar(Texto texto) throws Exception {
        if (!texto.isEncriptado()) {
            throw new Exception("A mensagem deve estar encriptada");
        }
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO textos_tb "
                + "(texto, senha) "
                + "VALUES (?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, texto.getTexto());
            p.setString(2, texto.getSenha());
            p.executeUpdate();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void alterar(Texto texto) throws Exception {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE textos_tb "
                + "SET texto = ?, senha = ? "
                + "WHERE codigo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, texto.getTexto());
            p.setString(2, texto.getSenha());
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

    public static ArrayList<Texto> listar() throws Exception {
        Connection conn = null;
        Statement st = null;
        ArrayList<Texto> textos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM textos_tb");
            while (rs.next()) {
                textos.add(instanciarTexto(rs));
            }
            return textos;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }

    private static Texto instanciarTexto(ResultSet rs) throws Exception {
        return new Texto(rs.getInt("codigo"), rs.getString("texto"), rs.getString("senha"), true);
    }
}
