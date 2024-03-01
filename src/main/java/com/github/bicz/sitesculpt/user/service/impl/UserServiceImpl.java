package com.github.bicz.sitesculpt.user.service.impl;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.user.dto.ResetPasswordRequest;
import com.github.bicz.sitesculpt.user.dto.UserResponse;
import com.github.bicz.sitesculpt.user.model.User;
import com.github.bicz.sitesculpt.user.model.password_reset.PasswordResetToken;
import com.github.bicz.sitesculpt.user.repository.PasswordTokenRepository;
import com.github.bicz.sitesculpt.user.repository.UserRepository;
import com.github.bicz.sitesculpt.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
//    private final JavaMailSender mailSender;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User with provided username was not found"));
            }
        };
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> result = new ArrayList<>();
        ArrayList<User> allUsers = (ArrayList<User>) userRepository.findAll();

        for (User user : allUsers) {
            result.add(new UserResponse(user.getUsername(), user.getEmail()));
        }

        return result;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            System.out.printf("user with provided id %d is empty\n", userId);
            return null;
        }
        User user = optionalUser.get();

        return new UserResponse(user.getUsername(), user.getEmail());
    }

//    @Override
//    public void resetPassword(HttpServletRequest servletRequest, ResetPasswordRequest request) throws RequestNotCorrectException, ResourceNotFoundException {
//        if (Objects.isNull(request)) {
//            throw new RequestNotCorrectException("Provided request is null");
//        }
//        if (Objects.isNull(request.getEmail())) {
//            throw new RequestNotCorrectException("Provided email is null");
//        }
//
//        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
//        if (optionalUser.isEmpty()) {
//            throw new ResourceNotFoundException(String.format("User with e-mail '%s' does not exist", request.getEmail()));
//        }
//        User user = optionalUser.get();
//
//        String token = UUID.randomUUID().toString();
//        PasswordResetToken passwordResetToken = new PasswordResetToken();
//        passwordResetToken.setUser(user);
//        passwordResetToken.setToken(token);
//        passwordTokenRepository.save(passwordResetToken);
//
//        mailSender.send(constructResetTokenEmail("http://localhost:8080/api/v1", token, user));
//    }

//    private SimpleMailMessage constructResetTokenEmail(String contextPath, String token, User user) {
//        String url = contextPath + "/user/changePassword?token=" + token;
//        String message = String.format("Drogi Użytkowniku,\n" +
//                "\n" +
//                "Otrzymaliśmy prośbę o zresetowanie hasła do Twojego konta. Jeśli nie wysyłałeś tej prośby, prosimy zignorować ten e-mail. Twoje hasło nie zostanie zmienione bez dodatkowych działań z Twojej strony.\n" +
//                "\n" +
//                "Aby zresetować hasło, kliknij poniższy link:\n" +
//                "%s\n" +
//                "\n" +
//                "Link do resetowania hasła będzie aktywny przez 24 godziny. Po tym czasie będzie wymagane wysłanie nowej prośby o reset hasła.\n" +
//                "\n" +
//                "Jeśli masz jakiekolwiek pytania lub napotykasz problemy, skontaktuj się z naszym działem wsparcia klienta.\n" +
//                "\n" +
//                "Dziękujemy za korzystanie z naszych usług.\n" +
//                "\n" +
//                "Z poważaniem,\n" +
//                "Zespół Wsparcia SiteSculpt CMS", url);
//        return constructEmail("Resetowanie Twojego Hasła", message, user);
//    }
//
//    private SimpleMailMessage constructEmail(String subject, String body, User user) {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject(subject);
//        email.setText(body);
//        email.setTo(user.getEmail());
////        email.setFrom(env.getProperty("support.email"));
//        return email;
//    }
}
