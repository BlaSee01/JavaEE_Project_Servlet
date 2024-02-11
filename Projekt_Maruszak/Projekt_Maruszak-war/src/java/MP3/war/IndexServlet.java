package MP3.war;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp3.ejb.MP3Remote;

@WebServlet(name = "IndexServlet", urlPatterns = {"/IndexServlet"})
public class IndexServlet extends HttpServlet {

    @EJB
    private MP3Remote mP3;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projekt_Maruszak-warPU");
    EntityManager em = emf.createEntityManager();

    TypedQuery<MP3.war.Songs> query = em.createQuery("SELECT s FROM Songs s", MP3.war.Songs.class);

    private String extractVideoId(String videoUrl) {
        String videoId = null;
        if (videoUrl != null && videoUrl.trim().length() > 0) {

            if (videoUrl.contains("/watch?v=")) {   // format - check
                String[] split = videoUrl.split("=");
                if (split.length == 2) {
                    videoId = split[1];
                }
            } else if (videoUrl.contains("youtu.be/")) {    // formatv2 - check (druga możliwosc zapisu)

                String[] split = videoUrl.split("/");
                if (split.length == 4) {
                    videoId = split[3];
                }
            }
        }
        return videoId;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Odtwarzacz MP3</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    background-color: #f0f0f0; /* Kolor tła */");
            out.println("    margin: 0; /* Usunięcie marginesów */");
            out.println("    padding: 0; /* Usunięcie wypełnienia */");
            out.println("    height: 10vh; /* Wysokość kontenera do wysokości widoku */");
            out.println("}");
            out.println(".container {");
            out.println("    width: 30%; /* Szerokość kontenera */");
            out.println("    background-color: #fff; /* Kolor tła kontenera */");
            out.println("    padding: 15px; /* Wypełnienie kontenera */");
            out.println("    border-radius: 10px; /* Zaokrąglenie krawędzi kontenera */");
            out.println("    border: 2px solid #000; /* Obramowanie */");
            out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Cień kontenera */");
            out.println("    margin: 20px auto; /* Automatyczne wyśrodkowanie kontenera */");
            out.println("    text-align: center; /* Wyśrodkowanie zawartości tekstu */");
            out.println("}");
            out.println("h1 {");
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
            out.println("<h1>MP3 PLAYER</h1>");
            out.println("<form action=\"index.html\" method=\"GET\">");
            out.println("<input type=\"submit\" value=\"Powrót\">");
            out.println("</form><br>");
            out.println("<form action=\"AddSong\" method=\"GET\">");
            out.println("<input type=\"submit\" value=\"Dodaj utwór\">");
            out.println("</form><br>");
            out.println("<form action=\"DeleteSong\" method=\"GET\">");
            out.println("<input type=\"submit\" value=\"Usuń utwór\">");
            out.println("</form><br>");

            List<MP3.war.Songs> results = query.getResultList();
            int pom = 0;

            for (MP3.war.Songs song : results) {
                pom++;
                mP3.set(song.getTitle());
                mP3.set_art(song.getArtist());
                mP3.set_id(song.getId());
                song.setFile(song.getFile());

                out.println("<center><b style=\"color: #e0e;\">UTWÓR " + pom + "</b></center><br>");
                out.println("Tytuł: " + mP3.print() + "<br>");
                out.println("Artysta: " + mP3.print_art() + "<br>");
                out.println("ID: " + mP3.print_id() + "<br>");
                out.println("<center><iframe width=\"300\" height=\"150\" src=\"https://www.youtube.com/embed/" + extractVideoId(song.getFile()) + "\" frameborder=\"0\" allowfullscreen style=\"border: 3px solid #e0e;\"></iframe></center><br><br>");
           }
            out.println("</div>");
            out.println("</body></html>");
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
