package org.example.chap14.servlet;

import org.example.chap14.dao.UserDAO;
import org.example.chap14.entity.User;
import org.example.chap14.service.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/emailList")

public class EmailListServlet extends HttpServlet {

    private final UserDAO userDAO =
            new UserDAO();

    private final EmailService emailService =
            new EmailService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String firstName =
                request.getParameter("firstName");

        String lastName =
                request.getParameter("lastName");

        String email =
                request.getParameter("email");

        if (firstName == null ||
                firstName.trim().isEmpty() ||
                lastName == null ||
                lastName.trim().isEmpty() ||
                email == null ||
                email.trim().isEmpty()) {
            request.setCharacterEncoding("UTF-8");

            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setAttribute(
                    "message",
                    "Vui lòng điền đầy đủ thông tin bên dưới để đăng ký."
            );

            request.getRequestDispatcher(
                    "/index.jsp"
            ).forward(request, response);

            return;
        }

        try {

            // Tìm email trong database
            User user =
                    userDAO.findByEmail(email);

            if (user == null) {
                user = new User(firstName, lastName, email);
                userDAO.insert(user);
            }

            emailService.sendEmail(email, firstName);

            // Gửi email
            emailService.sendEmail(
                    email,
                    firstName
            );

            // Truyền dữ liệu sang thanks.jsp
            request.setAttribute(
                    "firstName",
                    firstName
            );

            request.setAttribute(
                    "lastName",
                    lastName
            );

            request.setAttribute(
                    "email",
                    email
            );

            request.getRequestDispatcher(
                    "/thanks.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "message",
                    "Có lỗi xảy ra: "
                            + e.getMessage()
            );

            request.getRequestDispatcher(
                    "/index.jsp"
            ).forward(request, response);
        }
    }
}