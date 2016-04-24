/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.sistema.alocacao.core.actions.user;

import io.github.jass2125.sistema.alocacao.core.business.User;
import io.github.jass2125.sistema.alocacao.core.dao.UserDao;
import io.github.jass2125.sistema.alocacao.core.factory.Factory;
import io.github.jass2125.sistema.alocacao.core.factory.FactoryDao;
import io.github.jass2125.sistema.alocacao.core.util.Command;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe que efetua a busca por os usuarios
 *
 * @author Anderson Souza
 * @since 2015
 */
public class ListUsersCommand implements Command {

    private Factory factory;
    private UserDao dao;
    private String role;

    public ListUsersCommand() {
        factory = new FactoryDao();
        dao = factory.createUserDao();
    }

    /**
     * Método que efetua a busca por todos os usuarios
     *
     * @param request Requisição
     * @param response Resposta
     * @return String Retorna a pagina para qual a aplicação será encaminhada
     */

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            this.role = user.getRole();
            List<User> listUsers = dao.list(user.getIdUser());
            HttpSession session = request.getSession();
            session.setAttribute("listUsers", listUsers);
            return this.role + "/gerenciarusuario.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error.jsp";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListUsersCommand.class.getName()).log(Level.SEVERE, null, ex);
            return "error.jsp";
        }
    }
}