package MP3.war;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddSongServlet", urlPatterns = {"/AddSong"})
public class AddSongServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Dodaj utwór</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    background-color: #f0f0f0; /* Kolor tła */");
            out.println("    margin: 0; /* Usunięcie marginesów */");
            out.println("    padding: 0; /* Usunięcie wypełnienia */");
            out.println("    display: flex; /* Ustawienie kontenera na flexbox */");
            out.println("    justify-content: center; /* Wyśrodkowanie zawartości w poziomie */");
            out.println("    align-items: center; /* Wyśrodkowanie zawartości w pionie */");
            out.println("    height: 70vh; /* Wysokość kontenera do wysokości widoku */");
            out.println("}");
            out.println(".container {");
            out.println("    width: 30%; /* Szerokość kontenera */");
            out.println("    background-color: #fff; /* Kolor tła kontenera */");
            out.println("    padding: 15px; /* Wypełnienie kontenera */");
            out.println("    border-radius: 10px; /* Zaokrąglenie krawędzi kontenera */");
            out.println("    border: 2px solid #000; /* Obramowanie */");
            out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Cień kontenera */");
            out.println("    min-height: 60vh; /* Minimalna wysokość kontenera do 60% wysokości widoku */");
            out.println("    display: flex; /* Ustawienie kontenera na flexbox */");
            out.println("    flex-direction: column; /* Ustawienie kierunku zawartości na pionowy */");
            out.println("    text-align: center; /* Wyśrodkowanie zawartości tekstu */");
            out.println("}");
            out.println("h2 {");
            out.println("    color: #555; /* Kolor napisu */");
            out.println("}");
            out.println("form {");
            out.println("    margin-top: 20px; /* Odstęp między napisem a przyciskiem */");
            out.println("}");
            out.println("input[type=\"submit\"] {");
            out.println("    padding: 10px 20px; /* Wielkość przycisku */");
            out.println("    background-color: #e0e; /* Kolor tła przycisku */");
            out.println("    color: #fff; /* Kolor tekstu przycisku */");
            out.println("    border: 2px solid #000; /* Obramowanie */");
            out.println("    border-radius: 5px; /* Zaokrąglenie krawędzi przycisku */");
            out.println("    cursor: pointer; /* Zmiana kursora na wskaźnik */");
            out.println("    transition: background-color 0.3s; /* Płynne przejście koloru tła */");
            out.println("}");
            out.println("input[type=\"submit\"]:hover {");
            out.println("    background-color: #666; /* Kolor tła przycisku po najechaniu */");
            out.println("}");
            out.println("</style>");
            out.println("</head><body>");  
            out.println("<div class=\"container\">");
            out.println("<h2>DODAJ NOWY UTWÓR</h2>");
            out.println("<form action=\"IndexServlet\" method=\"POST\">");
            out.println("<input type=\"submit\" value=\"Powrót\"><br>");
            out.println("</form>");
            out.println("<form action=\"AddSong\" method=\"POST\">");
            out.println("<br><label for=\"id\">ID:</label>");
            out.println("<input type=\"number\" id=\"id\" name=\"id\"><br>");
            out.println("<label for=\"title\">Tytuł:</label>");
            out.println("<input type=\"text\" id=\"title\" name=\"title\"><br>");
            out.println("<label for=\"artist\">Artysta:</label>");
            out.println("<input type=\"text\" id=\"artist\" name=\"artist\"><br>");
            out.println("<label for=\"file\">Link:</label>");
            out.println("<input type=\"text\" id=\"file\" name=\"file\"><br>");
            out.println("<br><input type=\"submit\" value=\"Dodaj utwór\">");
            out.println("</form>");
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String idStr = request.getParameter("id");
                String title = request.getParameter("title");
                String artist = request.getParameter("artist");
                String file = request.getParameter("file");

                if (idStr != null && !idStr.isEmpty() && title != null && !title.isEmpty() && artist != null && !artist.isEmpty() && file != null && !file.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr);

                        // Insert new record into database
                        if(DbManager.connect()){

                            boolean success = DbManager.insertSong(id, title, artist, file);

                            if (success) {
                                out.println("<p>Dodano nowy utwór do bazy danych.</p>");
                            } else {
                                out.println("<p>Nie udało się dodać nowego utworu do bazy danych.</p>");
                            }
                        }
                    } catch (NumberFormatException e) {
                        out.println("<p>Podano nieprawidłowy format ID.</p>");
                    }
                } else {
                    out.println("<p>Proszę wypełnić wszystkie pola formularza.</p>");
                }
            }  
            out.println("</body></html>");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Wystąpił błąd podczas przetwarzania żądania.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
