package control;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        String html = null;
        // 账号存在
        if (User.userHashMap.get(username)!=null){
            // 从HashMap里获取这个账号密码并判断是否与登录的账号密码一致
            if (User.userHashMap.get(username).getUsername().equals(username) && User.userHashMap.get(username).getPassword().equals(password)){
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                session.setAttribute("email",User.userHashMap.get(username).getEmail());
                System.out.println("登录成功！");
                html = "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>个人资料</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<h1>" + session.getAttribute("username") +
                        ",登录成功</h1>" +
                        "<table>\n" +
                        "    <tr>\n" +
                        "        <td>账号:</td>\n" +
                        "<td>"+  session.getAttribute("username") +"</td>" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "        <td>密码:</td>\n" +
                        "<td>"+  session.getAttribute("password") +"</td>" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "        <td>邮箱:</td>\n" +
                        "<td>"+  session.getAttribute("email") +"</td>" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "        <td><input type=\"button\" value=\"注销\" ></td>\n" +
                        "    </tr>\n" +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>";
            }else {// 返回登录页面
                req.getRequestDispatcher("index.html").forward(req,resp);
                System.out.println("账号或者密码错误！");
            }
        }else {
            req.getRequestDispatcher("index.html").forward(req,resp);
            System.out.println("账号不存在！");
        }
        if(html!=null){
            PrintWriter ps = resp.getWriter();
            ps.println(html);
        }

    }
}
