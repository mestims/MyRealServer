package br.com.server.auth;

import br.com.server.auth.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private User currentUser;

    @RequestMapping("/auth")
    public String auth() {
        return "AuthController...";
    }

    @RequestMapping("/auth/user")
    public User authUser() {
        return currentUser;
    }

    @RequestMapping("/auth/new")
    public ResponseEntity newUserWithBody(
            @RequestBody User user
    ) {
        currentUser = user;
        ResponseEntity entity = ResponseEntity.ok().build();
        entity.getStatusCode();
        return ResponseEntity.ok(currentUser);
    }

}
