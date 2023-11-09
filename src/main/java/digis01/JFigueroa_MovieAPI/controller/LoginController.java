/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digis01.JFigueroa_MovieAPI.controller;

import digis01.JFigueroa_MovieAPI.entities.Session;
import digis01.JFigueroa_MovieAPI.entities.User;
import digis01.JFigueroa_MovieAPI.entities.UsuarioLogin;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author digis
 */
@Controller
public class LoginController {

    @GetMapping("/")
    public String Login(Model model) {
        model.addAttribute("usuarioLogin", new UsuarioLogin());
        return "Login";
    }


    @PostMapping("/")
    public String Login(@Valid @ModelAttribute("usuarioLogin") UsuarioLogin usuarioLogin, BindingResult bindingResult,
            HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuarioLogin", usuarioLogin);
            return "Login";
        }
        RestTemplate restTemplate = new RestTemplate();
        // generar request token
        Session usuarioSession = restTemplate.getForObject(
                "https://api.themoviedb.org/3/authentication/token/new" + "?api_key=29ffbfc8417ce43b494ff1a5a6abe5e7",
                Session.class);
        if (usuarioSession.isSuccess()) {
            usuarioLogin.setRequest_token(usuarioSession.getRequest_token());
            HttpEntity<Object> entity = new HttpEntity<Object>(usuarioLogin);
            ResponseEntity<Session> response = restTemplate.exchange(
                    "https://api.themoviedb.org/3/authentication/token/validate_with_login"
                            + "?api_key=29ffbfc8417ce43b494ff1a5a6abe5e7",
                    HttpMethod.POST, entity, Session.class);

            if (response.getStatusCode().value() == 200) {
                usuarioSession = response.getBody();
                // generar sesion id
                Session sessionVrified = restTemplate
                        .postForObject("https://api.themoviedb.org/3/authentication/session/new" +
                                "?api_key=29ffbfc8417ce43b494ff1a5a6abe5e7", usuarioLogin, Session.class);
                usuarioSession.setSession_id(sessionVrified.getSession_id());

                // Guardar el usuario
                // Guardar el usuario
                try {
                    User user = restTemplate.getForObject(
                            "https://api.themoviedb.org/3/account" + "?session_id=" + usuarioSession.getSession_id() +
                                    "&api_key=29ffbfc8417ce43b494ff1a5a6abe5e7",
                            User.class);
                    user.setSession_id(usuarioSession.getSession_id());
                    session.setAttribute("usuario", user);
                    return "redirect:/popular";
                } catch (Exception e) {
                    return "redirect:/popular";
                }
            } else {
                model.addAttribute("error", true);
                return "Login";
            }
        } else {
            return "login";
        }

    }

    @GetMapping("/logout")
    public String Logout(Model model, HttpSession session) {
        session.removeAttribute("usuario");
        return "redirect:/";
    }
}
