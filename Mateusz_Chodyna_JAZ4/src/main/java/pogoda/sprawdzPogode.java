package pogoda;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;


@WebServlet("/sprawdzPogode")
public class sprawdzPogode extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public sprawdzPogode() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(418);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String wybraneMiasto = request.getParameter("wybraneMiasto");

        out.append("<title>Pogoda</title>\n" +
                "    <link href=\"mystyle.css\" rel=\"stylesheet\" type=\"text/css\"><body id='pogoda'><div class=\"myDiv\">");
        out.append("<center>");

        // declaring object of "OWM" class
        OWM owm = new OWM("ecf6049c8c9f4fd610f8cb21f424576a");



            // getting current weather data for the "Warszawa" city
            CurrentWeather currentWeather = null;
            try {
                if (wybraneMiasto.equals("warszawa")) {
                    currentWeather = owm.currentWeatherByCityName("Warsaw, PL");
                }
                else if (wybraneMiasto.equals("gdansk")) {
                    currentWeather = owm.currentWeatherByCityName("Gdansk, PL");
                }
                else if (wybraneMiasto.equals("krakow")) {
                    currentWeather = owm.currentWeatherByCityName("Krakow, PL");
                }
                else if (wybraneMiasto.equals("wroclaw")) {
                    currentWeather = owm.currentWeatherByCityName("Wroclaw, PL");
                }
                else if (wybraneMiasto.equals("poznan")) {
                    currentWeather = owm.currentWeatherByCityName("Poznan, PL");
                }
                else if (wybraneMiasto.equals("lodz")) {
                    currentWeather = owm.currentWeatherByCityName("Lodz, PL");
                }
                else if (wybraneMiasto.equals("katowice")) {
                    currentWeather = owm.currentWeatherByCityName("Katowice, PL");
                }
            } catch (APIException e) {
                e.printStackTrace();
            }


            // checking data retrieval was successful or not
            if (currentWeather.hasRespCode() && currentWeather.getRespCode() == 200) {

                // checking if city name is available
                if (currentWeather.hasCityName()) {
                    //printing city name from the retrieved data
                    out.append("<br><br>Miasto: " + currentWeather.getCityName());
                }

                // checking if max. temp. and min. temp. is available
                if (currentWeather.hasMainData() && currentWeather.getMainData().hasTempMax() && currentWeather.getMainData().hasTempMin()) {
                    // printing the max./min. temperature
                    out.append("<br>Temperatura: " + (currentWeather.getMainData().getTempMax() - 273.15)
                            + "/" + (currentWeather.getMainData().getTempMin() - 273.15) + "\'C");
                }

                if (currentWeather.hasMainData() && currentWeather.getMainData().hasPressure()) {
                    out.append("<br>Cisnienie: " + currentWeather.getMainData().getPressure() + " hektopaskali");
                }

                if (currentWeather.hasMainData() && currentWeather.hasCloudData()) {
                    out.append("<br>Zachmurzenie: " + currentWeather.getCloudData().getCloud() + " %");
                }

                if (currentWeather.hasMainData() && currentWeather.hasWindData()) {
                    out.append("<br>Wiatr: " + currentWeather.getWindData().getSpeed() + " m/s");
                }
                out.append("<br><br><br><br><br><br><br><br><br>");
                out.append("<a href='index.jsp'>Wroc do strony glownej</a>");
            }

        out.append("</center>");
    }
}
