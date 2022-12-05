package lk.ijse.dep9;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.dep9.entity.User;
import lk.ijse.dep9.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.BlobProxy;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;

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
        // to save in the computer
//        File profilePicDir = new File(new File(System.getProperty("user.home"), "Documents/dep-9/phase-4/file-uploading-2"), "profile-pic");
//
//        File file = new File(profilePicDir, profilepic.getSubmittedFileName());
//        file.createNewFile();
//        profilepic.write(file.getAbsolutePath());

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
//            BlobProxy.generateProxy() only availble in native hibernate
            byte[] bytes = profilepic.getInputStream().readAllBytes();
            SerialBlob serialBlob = new SerialBlob(bytes);
            User user = new User(fullName, username, password, new SerialBlob(serialBlob));

            session.persist(user);
            session.getTransaction().commit();
        } catch (SerialException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
