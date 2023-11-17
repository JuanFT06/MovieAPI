/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digis01.JFigueroa_MovieAPI.controller;

import digis01.JFigueroa_MovieAPI.entities.Movie;
import digis01.JFigueroa_MovieAPI.entities.Result;
import digis01.JFigueroa_MovieAPI.entities.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author digis
 */
@Controller
public class MoviesController {

    @GetMapping("popular")
    public String popular(Model model, HttpSession session) {

        RestTemplate restTemplate = new RestTemplate();
        Result response = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/popular" + "?api_key=29ffbfc8417ce43b494ff1a5a6abe5e7",
                Result.class);
        List<Movie> movies = response.getResults();

        User usuario = (User) session.getAttribute("usuario");

        // get favorite movies
        Result responseFavorites = restTemplate.getForObject("https://api.themoviedb.org/3/account/" + usuario.getId()
                + "/favorite/movies" + "?session_id=" + usuario.getSession_id()
                + "&api_key=29ffbfc8417ce43b494ff1a5a6abe5e7", Result.class);
        List<Movie> favoritesMovies = responseFavorites.getResults();

        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < favoritesMovies.size(); j++) {
                if (favoritesMovies.get(j).getId() == movies.get(i).getId()) {
                    movies.get(i).setFavorite(true);
                }
            }
        }

        model.addAttribute("cuentaid", usuario.getId());
        model.addAttribute("sessionid", usuario.getSession_id());
        model.addAttribute("movies", movies);
        return "popular";
    }

    @GetMapping("top-rated")
    public String topRated(Model model, HttpSession session) {

        RestTemplate restTemplate = new RestTemplate();
        Result response = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/top_rated" + "?api_key=29ffbfc8417ce43b494ff1a5a6abe5e7",
                Result.class);
        List<Movie> movies = response.getResults();

        User usuario = (User) session.getAttribute("usuario");

        // get favorite movies
        Result responseFavorites = restTemplate.getForObject("https://api.themoviedb.org/3/account/" + usuario.getId()
                + "/favorite/movies" + "?session_id=" + usuario.getSession_id()
                + "&api_key=29ffbfc8417ce43b494ff1a5a6abe5e7", Result.class);
        List<Movie> favoritesMovies = responseFavorites.getResults();

        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < favoritesMovies.size(); j++) {
                if (favoritesMovies.get(j).getId() == movies.get(i).getId()) {
                    movies.get(i).setFavorite(true);
                }
            }
        }
        model.addAttribute("cuentaid", usuario.getId());
        model.addAttribute("sessionid", usuario.getSession_id());
        model.addAttribute("movies", movies);
        return "popular";
    }

    @GetMapping("on-tv")
    public String onTv(Model model) {

        RestTemplate restTemplate = new RestTemplate();
        Result response = restTemplate.getForObject(
                "https://api.themoviedb.org/3/tv/popular" + "?api_key=29ffbfc8417ce43b494ff1a5a6abe5e7",
                Result.class);
        List<Movie> movies = response.getResults();

        model.addAttribute("movies", movies);
        return "popular";
    }

    @GetMapping("airing-today")
    public String airing(Model model) {

        RestTemplate restTemplate = new RestTemplate();
        Result response = restTemplate.getForObject(
                "https://api.themoviedb.org/3/tv/airing_today" + "?api_key=29ffbfc8417ce43b494ff1a5a6abe5e7",
                Result.class);
        List<Movie> movies = response.getResults();

        model.addAttribute("movies", movies);
        return "popular";
    }

    @GetMapping("detalle/{idmovie}")
    public String delatte(@PathVariable("idmovie") int idmovie, Model model, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        Movie movie = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/" + idmovie + "?api_key=29ffbfc8417ce43b494ff1a5a6abe5e7",
                Movie.class);

        User usuario = (User) session.getAttribute("usuario");

        // get favorite movies
        Result responseFavorites = restTemplate.getForObject("https://api.themoviedb.org/3/account/" + usuario.getId()
                + "/favorite/movies" + "?session_id=" + usuario.getSession_id()
                + "&api_key=29ffbfc8417ce43b494ff1a5a6abe5e7", Result.class);
        List<Movie> favoritesMovies = responseFavorites.getResults();

        for (int j = 0; j < favoritesMovies.size(); j++) {
            if (favoritesMovies.get(j).getId() == movie.getId()) {
                movie.setFavorite(true);
                break;
            }
        }

        model.addAttribute("cuentaid", usuario.getId());
        model.addAttribute("sessionid", usuario.getSession_id());
        model.addAttribute(movie);
        return "detalle";
    }

    @GetMapping("detalle-tv/{idmovie}")
    public String delatteTV(@PathVariable("idmovie") int idmovie, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Movie movie = restTemplate.getForObject(
                "https://api.themoviedb.org/3/tv/" + idmovie + "?api_key=29ffbfc8417ce43b494ff1a5a6abe5e7",
                Movie.class);

        model.addAttribute(movie);
        return "detalle";
    }
}
