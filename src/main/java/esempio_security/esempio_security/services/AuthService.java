package esempio_security.esempio_security.services;

import esempio_security.esempio_security.enums.Role;
import esempio_security.esempio_security.models.AuthenticationResponse;
import esempio_security.esempio_security.models.LoginModel;
import esempio_security.esempio_security.models.SignUpModel;
import esempio_security.esempio_security.models.UserModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final userService UserService;

    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService, PasswordEncoder passwordEncoder, userService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        UserService = userService;
    }

    public AuthenticationResponse login(LoginModel loginModel){
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginModel.getUsername(),
                        loginModel.getPassword()
                )
        );
        UserDetails userModel = this.UserService.loadUserByUsername(loginModel.getUsername());
        String jwtToken = this.jwtService.generateToken(userModel);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse signUp(SignUpModel signUpModel){
        UserModel userModel = new UserModel();
        userModel.setUsername(signUpModel.getUsername());
        userModel.setPassword(passwordEncoder.encode(signUpModel.getPassword()));
        userModel.setRole(Role.USER);

        UserModel userNew = this.UserService.saveUser(userModel);
        String jwtToken = this.jwtService.generateToken(userNew);
        return new AuthenticationResponse(jwtToken);
    }
}
