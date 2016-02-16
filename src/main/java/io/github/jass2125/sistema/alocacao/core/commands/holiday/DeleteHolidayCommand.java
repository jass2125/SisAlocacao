/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.sistema.alocacao.core.commands.holiday;
                                                                                                                                                                                                                                                                                                                                                            
import io.github.jass2125.sistema.alocacao.core.dao.IHolidayDao;
import io.github.jass2125.sistema.alocacao.core.factory.Factory;
import io.github.jass2125.sistema.alocacao.core.factory.FactoryDao;
import io.github.jass2125.sistema.alocacao.core.util.Command;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe que efetua a exclusão de um feriado
 *
 * @author Anderson Souza
 * @since 2015
 */
public class DeleteHolidayCommand implements Command {

    /**
     * Action que efetua a busca por os feriados
     *
     * @param request Requisição
     * @param response Resposta
     * @return String Retorna a pagina para qual a aplicação será encaminhada
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("idHoliday");
            int idHoliday = Integer.parseInt(id);
            Factory factory = new FactoryDao();
            IHolidayDao dao = factory.createHolidayDao();
            dao.delete(idHoliday);
            request.getSession().setAttribute("listHolidays", dao.list());
            return "administrador/gerenciarferiado.jsp";
        } catch (SQLException | NumberFormatException e) {
            return "error.jsp";
        }
    }

}
