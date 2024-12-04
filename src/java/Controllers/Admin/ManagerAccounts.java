package Controllers.Admin;

import java.io.IOException;
import DAL.Admin.ManageAccount;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.User;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(name = "ManagerAccounts", urlPatterns = {"/managerAccounts"})
public class ManagerAccounts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManageAccount account = new ManageAccount();

        int page = 1;
        int pageSize = 7;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int totalAccounts = account.getAccountCount();
        int totalPages = (int) Math.ceil((double) totalAccounts / pageSize);

        // Lấy danh sách tài khoản cho trang hiện tại
        List<User> listAcc = account.getAccountsByPage(page, pageSize);
        request.setAttribute("listAcc", listAcc);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("Views/Admin/AdminAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManageAccount account = new ManageAccount();
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        String service = request.getParameter("Service");
        String message = "";

        if (service != null && service.equals("Delete")) {
            String acc_id = request.getParameter("accid");
            account.deleteAccount(acc_id);
            message = "Account with ID " + acc_id + " has been deleted.";
        } else if (username == null || username.isEmpty() || action == null || action.isEmpty()) {
            message = "Username and action are required!";
        } else {
            account.accountStatus(username, action);
            message = "Account " + username + " has been " + (action.equals("ban") ? "banned." : "activated.");
        }

        request.setAttribute("message", message);
        doGet(request, response); 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
