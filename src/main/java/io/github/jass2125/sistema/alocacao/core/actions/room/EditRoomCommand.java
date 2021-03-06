/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.sistema.alocacao.core.actions.room;

import io.github.jass2125.sistema.alocacao.core.business.Room;
import io.github.jass2125.sistema.alocacao.core.business.User;
import io.github.jass2125.sistema.alocacao.core.dao.RoomDao;
import io.github.jass2125.sistema.alocacao.core.factory.Factory;
import io.github.jass2125.sistema.alocacao.core.factory.FactoryDao;
import io.github.jass2125.sistema.alocacao.core.util.Command;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anderson Souza
 */
public class EditRoomCommand implements Command  {

    private Factory factory;
    private RoomDao dao;

    public EditRoomCommand() {
        factory = new FactoryDao();
        dao = factory.createRoomDao();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            Long idRoom = Long.parseLong(request.getParameter("idRoom"));
            Room room = dao.findById(idRoom);
            String nameRoom = request.getParameter("nameRoom");
            Long idFloor = Long.parseLong(request.getParameter("floor"));
            Integer capacicity = Integer.parseInt(request.getParameter("capacity"));
            String typeRoom = request.getParameter("typeRoom");
            room.setCapacity(capacicity);
            room.setNameRoom(nameRoom);
            room.setTypeRoom(typeRoom);
            dao.update(room);
            request.getSession().setAttribute("rooms", dao.listRooms());
            return user.getRole() + "/gerenciarsala.jsp";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return user.getRole() + "/gerenciarsala.jsp";
        }
    }

}
