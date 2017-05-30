import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by faisaljarkass on 19/08/16.
 * mvn appengine:update
 * mvn appengine:devserver
 */
public class MyServlet extends HttpServlet {
    public String name = "";
    public String pas = "";
    public String text = "";
    public int allow = -1;

    private static Logger logger = Logger.getLogger(MyServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("username");
        pas = request.getParameter("password");

        BL b1 = new BL();
        int level = b1.getUser(name, pas);

        if(allow == 0){
            text = request.getParameter("txtArea");
        }

        if (level == 0) {
            this.allow = 0;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/loggedIn.html");
            dispatcher.forward(request, response);
        }
        else if(level == 1){
            this.allow = 1;
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            String title = "Velkommen user";
            String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " +
                            "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<ul>\n" +
                    "  <li><b>Username</b>: "
                    + request.getParameter("username") + "\n" +
                    "  <li><b>Password</b>: "
                    + request.getParameter("password") + "\n" +
                    "  <li><b>Text</b>: "
                    + text + "\n" +
                    "</ul>\n" +
                    "</body></html>");
            logger.log(Level.INFO, "doPost ended...");
        }
        else
        {
            this.allow = -1;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index2.html");
            dispatcher.forward(request, response);

        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.INFO, "doGet started...");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Using POST Method to Read Form Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>Username</b>: "
                + request.getParameter("username") + "\n" +
                "  <li><b>Password</b>: "
                + request.getParameter("password") + "\n" +
                "</ul>\n" +
                "</body></html>");
        logger.log(Level.INFO, "doGet ended...");
    }

    ////////////////////////////////////getter//////////////////////////////////
    public String getName()
    {
        return this.name;
    }

    public String getpas()
    {
        return this.pas;
    }
}
