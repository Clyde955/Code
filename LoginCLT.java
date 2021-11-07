package com.javawebapp.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javawebapp.beans.UserBeans;
import com.javawebapp.model.UserModel;
import com.javawebapp.utility.DataUtility;
import com.javawebapp.utility.ServletUtility;

/**
 * Servlet implementation class LoginCTL
 */
@WebServlet(name= "LoginCTL", urlPatterns = {"LoginCTL"})
public class LoginCTL extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCTL() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    String op = DataUtility.getString(request.getParameter("operation"));
    HttpSession session=request.getSession(false);
    if("logout".equalsIgnoreCase(op)) {
      session.invalidate();
      ServletUtility.setSuccessMessage("LogoutSucessfully",request);
      
    }
    ServletUtility.forward("jsp/login.jsp", request, response);
    
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
       UserBeans user = new UserBeans();
       String login=request.getParameter("login");
       String pwd=request.getParameter("password");
      
       HttpSession session=request.getSession(true);
       user = UserModel.UserLogin(login,pwd);
       if(user != null) {
           session.setAttribute("user", user.getFirstName());
           //ServletUtility.setSuccessMessage(request.getParameter("login")+ " is login successfully", request);
           ServletUtility.redirect("jsp/welcome.jsp", request, response);
       }else {
           ServletUtility.setErrorMessage("Invalid User", request);
           ServletUtility.forward("jsp/login.jsp", request, response);
       }
  }

}