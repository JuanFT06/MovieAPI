package digis01.JFigueroa_MovieAPI.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import digis01.JFigueroa_MovieAPI.entities.Movie;
import digis01.JFigueroa_MovieAPI.entities.Result;
import digis01.JFigueroa_MovieAPI.entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String Profile(Model model, HttpSession session) {
        User usuario = (User) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        RestTemplate restTemplate = new RestTemplate();

        //get favorite movies
         Result response = restTemplate.getForObject("https://api.themoviedb.org/3/account/"+usuario.getId()+"/favorite/movies" +"?session_id=" + usuario.getSession_id() +
                                    "&api_key=29ffbfc8417ce43b494ff1a5a6abe5e7", Result.class);
        List<Movie> movies = response.getResults();

        model.addAttribute("movies", movies);
        return "profile";
    }
    
}
