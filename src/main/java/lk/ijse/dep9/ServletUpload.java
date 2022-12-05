package lk.ijse.dep9;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet(urlPatterns = "/uploads",loadOnStartup = 1)
public class ServletUpload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setHeader("Access-Control-Allow-Origin","*");
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Part profilepic = request.getPart("profilePic");
        System.out.println(fullName);
        System.out.println(username);
        System.out.println(password);
        File profilePicDir = new File(new File(System.getProperty("user.home"), "Documents/dep-9/phase-4/file-uploading-2"), "profile-pic");

        File file = new File(profilePicDir, profilepic.getSubmittedFileName());
        file.createNewFile();
        profilepic.write(file.getAbsolutePath());
    }
}
