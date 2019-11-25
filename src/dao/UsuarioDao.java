package dao;

import model.Texto;
import model.Usuario;
import utils.DaoUtils;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDao {
    public static Usuario get(String nome, String senha) throws Exception {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs;
        String sql = "SELECT * FROM usuarios_tb WHERE nome = ? AND senha = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, nome);
            p.setString(2, senha);
            rs = p.executeQuery();
            if (!rs.next())
                return null;
            return instanciarUsuario(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void salvar(Usuario usuario) throws Exception {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO usuarios_tb "
                + "(nome, senha) "
                + "VALUES (?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, usuario.getNome());
            p.setString(2, usuario.getSenha());
            p.executeUpdate();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void alterar(Usuario usuario) throws Exception {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE usuarios_tb "
                + "SET nome = ?, senha = ? "
                + "WHERE codigo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, usuario.getNome());
            p.setString(2, usuario.getSenha());
            p.setInt(3, usuario.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void apagar(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM usuarios_tb WHERE codigo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, usuario.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    private static Usuario instanciarUsuario(ResultSet rs) throws Exception {
        Usuario u = new Usuario(
                rs.getInt("codigo"),
                rs.getString("nome"),
                rs.getString("senha"));
        u.setTextos(TextoDao.listar(u));
        return u;
    }
}
