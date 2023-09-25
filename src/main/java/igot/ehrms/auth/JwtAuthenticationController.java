package igot.ehrms.auth;

import igot.ehrms.log.LogModel;
import igot.ehrms.log.LogService;
import igot.ehrms.user.UserModel;
import igot.ehrms.user.UserService;
import igot.ehrms.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    LogService logService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());


        return ResponseEntity.ok(new AuthenticateResponseDto(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/user/create/{orgId}")
    public ResponseEntity<Map<String, Object>> createUser(@PathVariable("orgId") String orgId) throws Exception {

        String password = CommonUtil.generateRandomCharacters(12);
        String encPassword = CommonUtil.encrypt(password);


        UserModel user = new UserModel(orgId, encPassword);
        user = userService.createUser(user);

        LogModel logModel = new LogModel(user.getId(),orgId, "createUser", LocalDateTime.now());
        logService.createLog(logModel);

        Map<String, Object> response = new HashMap<>();
        if(user.getId() != null){
            response.put("msg", "success");
            response.put("id",user.getId());
            response.put("password",password);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else{
            response.put("msg", "failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}