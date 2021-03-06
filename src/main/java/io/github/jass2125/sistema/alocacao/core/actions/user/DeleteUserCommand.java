/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.sistema.alocacao.core.actions.user;

import io.github.jass2125.sistema.alocacao.core.business.User;
import io.github.jass2125.sistema.alocacao.core.factory.Factory;
import io.github.jass2125.sistema.alocacao.core.factory.FactoryDao;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import io.github.jass2125.sistema.alocacao.core.dao.UserDao;
import io.github.jass2125.sistema.alocacao.core.util.Command;

/**
 * Classe que efetua a exclusão de um usuarios
 *
 * @author Anderson Souza
 * @since 2015
 */
public class DeleteUserCommand implements Command  {

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
            Long idUser = Long.parseLong(request.getParameter("idUser"));
            Factory factory = new FactoryDao();
            UserDao dao = factory.createUserDao();
            dao.delete(idUser);
            HttpSession session = request.getSession();
            session.setAttribute("listUsers", dao.list(((User) session.getAttribute("user")).getIdUser()));
            return "administrador/gerenciarusuario.jsp";
        } catch (SQLException | NumberFormatException | ClassNotFoundException e) {
            e.getMessage();
            return "error.jsp";
        }
    }
}
