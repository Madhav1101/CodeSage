package codesage.backend.controllers;

import codesage.backend.Dto.UserResponse;
import codesage.backend.Entity.User;
import codesage.backend.security.AppUserPrincipal;
import codesage.backend.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final CurrentUser currentUser;

    @GetMapping("/login-url")
    public Map<String, String> loginUrl() {
        return Map.of("url", "/oauth/authorization/github");
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me() {
        AppUserPrincipal principal = currentUser.require();
        User user = principal.getUser();
        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getGithubId(),
                user.getGithubUsername(),
                user.getDisplayName(),
                user.getAvatarUrl()
              ));
    }
}