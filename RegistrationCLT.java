package com.javawebapp.Controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.javawebapp.beans.UserBeans;
import com.javawebapp.model.UserModel;
import com.javawebapp.utility.DataUtility;
import com.javawebapp.utility.ServletUtility;
/**
 * Servlet implementation class RegistrationCTL
 */
@WebServlet(name = "RegistrationCTL", urlPatterns = {"/RegistrationCTL"})
public class RegistrationCTL extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationCTL() {
        super();
        // TODO Auto-generated constructor stub
    }
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  //Edit....
    UserModel model = new UserModel();
    long id = DataUtility.getLong(request.getParameter("id"));
    if(id>0) {
      UserBeans bean = null;
      bean = model.FindByPk(id);
      request.setAttribute("bean", bean);

    }
    
    
    request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);

  }
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    
    UserBeans user = new UserBeans();
    user.setFirstName(request.getParameter("firstName"));
    user.setLastName(request.getParameter("lastName"));
    user.setLogin(request.getParameter("login"));
    user.setPassword(request.getParameter("password"));
    user.setDob(DataUtility.getDate(request.getParameter("dob")));
    user.setMobileNo(request.getParameter("mobile"));
    
    user.setId(DataUtility.getLong(request.getParameter("id")));
    if(user.getId()>0) {
        //To Update the records
         long i = UserModel.UpdateUser(user);
            if(i>0) {
              ServletUtility.setSuccessMessage("User Update sucessfully", request);
              
            }else {
              ServletUtility.setErrorMessage("Not insterted", request);
            }
           
        
    }else {
        //To add the new record
         long i = UserModel.addUser(user);
            if(i>0) {
              ServletUtility.setSuccessMessage("User register sucessfully", request);
              
            }else {
              ServletUtility.setErrorMessage("Not insterted", request);
            }
    }  
    request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
  }
}