/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.sistema.alocacao.core.dao;

import io.github.jass2125.sistema.alocacao.core.business.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Classe que implementa os DAO's de usuario
 *
 * @author Anderson Souza
 * @since 2015
 */
public class UserDao implements IUserDao {

    private Properties info = new Properties();
    private String url;

    public UserDao() {
        info.setProperty("user", "root");
        info.setProperty("password", "12345");
        url = "jdbc:mysql://localhost:3306/sisloc";
    }

    /**
     * Método responsável por buscar um usuario pelo seu identificador
     *
     * @param login Username do usuario
     * @param password Senha do usuario
     * @return user User
     * @throws ClassNotFoundException Ocorre quando o Driver MySQL não é
     * encontrado
     * @throws SQLException Erro de conexão com o banco de dados
     */
    @Override
    public User findByLoginAndPassword(String login, String password) throws ClassNotFoundException, SQLException {
        String sql = "select * from usuario where binary username = ? and binary senha = ? or binary email = ? and binary senha = ?;";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, "root", "12345");
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, login);
        ps.setString(2, password);
        ps.setString(3, login);
        ps.setString(4, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int idUser = rs.getInt("id_usuario");
            String name = rs.getString("nome");
            String username2 = rs.getString("username");
            String email = rs.getString("email");
            String registry = rs.getString("matricula");
            String role = rs.getString("papel");
            boolean status = rs.getBoolean("status");
            return new User(idUser, name, username2, password, email, registry, role, status);
        }
        rs.close();
        ps.clearParameters();
        ps.close();
        return null;
    }

    /**
     * Método responsável por adicionar um usuario
     *
     * @param user Usuario
     * @throws SQLException Erro de conexão com o banco de dados
     */
    @Override
    public void add(User user) throws SQLException {
        String sql = "insert into usuario(nome, username, senha, email, matricula, papel, status) values(?, ?, ?, ?, ?, ?, ?);";
        Connection con = DriverManager.getConnection(url, info);
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getEmail());
        ps.setString(5, user.getRegistry());
        ps.setString(6, user.getRole());
        ps.setBoolean(7, user.isStatus());
        ps.execute();
        ps.close();
        con.close();
    }

    /**
     * Método responsável por buscar todos os usuarios
     *
     * @param idUser Id do usuario
     * @return list Set
     * @throws SQLException UsuarioDao
     */
    @Override
    public List<User> list(int idUser) throws SQLException {
        String sql = "select * from usuario where id_usuario <> ? and status = true;";
        Connection con = DriverManager.getConnection(url, info);
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idUser);
        List<User> list = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_usuario");
            String name = rs.getString("nome");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("senha");
            String registry = rs.getString("matricula");
            String role = rs.getString("papel");
            boolean status = rs.getBoolean("status");
            User user = new User(id, name, username, password, email, registry, role, status);
            list.add(user);
        }
        rs.close();
        ps.clearParameters();
        ps.close();
        con.close();
        return list;
    }

    /**
     * Método responsável por editar um usuario
     *
     * @param usuario Usuario
     * @throws SQLException UsuarioDao
     * @throws ClassNotFoundException A classe do Driver não pôde ser carregada
     */
    @Override
    public void edit(User usuario) throws SQLException, ClassNotFoundException {
        String sql = "update usuario "
                + "set nome = ?, "
                + "username = ?, "
                + "senha = ?, "
                + "email = ?, "
                + "matricula = ?, "
                + "papel = ?, "
                + "status = ? "
                + "where id_usuario = ?;";
        Connection con = DriverManager.getConnection(url, info);
        Class.forName("com.mysql.jdbc.Driver");
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, usuario.getName());
        ps.setString(2, usuario.getUsername());
        ps.setString(3, usuario.getPassword());
        ps.setString(4, usuario.getEmail());
        ps.setString(5, usuario.getRegistry());
        ps.setString(6, usuario.getRole());
        ps.setBoolean(7, usuario.isStatus());
        ps.setInt(8, usuario.getIdUser());
        ps.execute();
        ps.close();
    }
    /**
     * Método responsável por excluir um usuario pelo seu identificador
     *
     * @param idUsuario Id do usuario
     * @throws SQLException UsuarioDao
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public void delete(int idUsuario) throws SQLException, ClassNotFoundException {
        String sql = "delete from usuario where id_usuario = ?;";
        Connection con = DriverManager.getConnection(url, info);
        Class.forName("com.mysql.jdbc.Driver");
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.execute();
    }
    /**
     * Método responsável por buscar um usuario pelo seu identificador
     *
     * @param idUser Id do usuario
     * @return usuario Usuario
     * @throws SQLException UsuarioDao
     */
    @Override
    public User findById(int idUser) throws SQLException {
        String sql = "select * from usuario where id_usuario = ?";
        Connection con = DriverManager.getConnection(url, info);
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idUser);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String senha = rs.getString("senha");
            String matricula = rs.getString("matricula");
            String papel = rs.getString("papel");
            boolean status = rs.getBoolean("status");
            return new User(idUser, nome, username, senha, email, matricula, papel, status);
        }
        rs.close();
        ps.clearParameters();
        ps.close();
        con.close();
        return null;

    }

    /**
     *
     * @param username String
     * @param email String
     * @return user Usuario
     * @throws SQLException Erro de conexão com o banco de dados
     * @throws ClassNotFoundException A classe do Driver do banco de dados não
     * pode ser carregada
     */

    @Override
    public User findByUsernameOrEmail(String username, String email) throws SQLException, ClassNotFoundException {
        String sql = "select * from usuario where binary username = ? or binary email = ?;";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, "root", "12345");
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, username);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int idUsuario = rs.getInt("id_usuario");
            String name = rs.getString("nome");
            String password = rs.getString("senha");
            String username2 = rs.getString("username");
            String email2 = rs.getString("email");
            String registry = rs.getString("matricula");
            String role = rs.getString("papel");
            boolean status = rs.getBoolean("status");
            return new User(idUsuario, name, username2, password, email2, registry, role, status);
        }
        rs.close();
        ps.clearParameters();
        ps.close();
        return null;
    }

}
