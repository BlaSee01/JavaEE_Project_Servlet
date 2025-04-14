/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MP3.war;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author student
 */
@WebServlet(name = "DbManager", urlPatterns = {"/DbManager"})
public class DbManager extends HttpServlet {
    
    public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String JDBC_URL = "jdbc:derby://localhost:1527/db";
    private static final String INSERT_SONG_QUERY = "INSERT INTO Songs (id, title, artist, file) VALUES (?, ?, ?, ?)";
    private static Connection conn;
    
    public static boolean connect() throws ClassNotFoundException, SQLException {
        conn = DriverManager.getConnection(JDBC_URL);
        return conn != null;
    }

    public static boolean disconnect() throws SQLException {
        if (conn == null) {
            return false;
        }
        conn.close();
        return true;
    }

    public static boolean insertSong(int id, String title, String artist, String file) {
        if (conn == null) {
            System.err.println("Brak połączenia! Jak mam dodawać?");
            return false;
        }

        try (PreparedStatement stmt = conn.prepareStatement(INSERT_SONG_QUERY)) {
            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, artist);
            stmt.setString(4, file);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteSong(int id) {
        if (conn == null) {
            System.err.println("Brak połączenia! Jak mam usuwać?");
            return false;
        }

        String DELETE_SONG_QUERY = "DELETE FROM Songs WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_SONG_QUERY)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DbManager</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DbManager at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
