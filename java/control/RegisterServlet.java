package control;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("INIT");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
         String username = req.getParameter("username");
         String password = req.getParameter("password");
         String email = req.getParameter("email");
         HttpSession session = req.getSession();

        System.out.println(User.userHashMap.get(username));
         if (User.userHashMap.get(username)==null){ // 注册的账号数据库中不存在
             User.userHashMap.put(username,new User(username,password,email));
             System.out.println("注册成功！");
             System.out.println("账号:"+username);
             System.out.println("密码:" + password);
             resp.sendRedirect("index.html");
         }else{// 账号已存在
             req.getRequestDispatcher("register.html").forward(req,resp);
             System.out.println("注册的账号已存在！请重新注册");
         }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }


}
