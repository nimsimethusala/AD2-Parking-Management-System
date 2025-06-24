package lk.ijse.userservice.controller;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.userservice.dto.AuthDTO;
import lk.ijse.userservice.dto.ResponseDTO;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
//import lk.ijse.userservice.util.JwtUtil;
import lk.ijse.userservice.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            int res = userService.saveUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        System.out.println("awooooooooooooooooo");
        try{
            List<UserDTO> allUsers = userService.getAllUsers();
            return ResponseEntity.ok(new ResponseDTO(VarList.OK, "User list fetched", allUsers));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable String id, @RequestBody UserDTO dto) {
        UserDTO updatedUser = userService.updateUser(UUID.fromString(id), dto);
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "User updated successfully", updatedUser));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String id) {
        userService.deleteUser(UUID.fromString(id));
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "User deleted successfully", null));
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDTO> getOwnProfile(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        UserDTO user = userService.loadUserDetailsByUsername(email);
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Current user profile", user));
    }
}*/

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public UserController(UserService userService) {
        this.userService = userService;
        this.encoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto) {
        userService.saveUser(dto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO dto) {
        UserDTO user = userService.loadUserDetailsByUsername(dto.getEmail());

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> allUsers() {
        try{
            return ResponseEntity.ok(userService.getAllUsers());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(UUID.fromString(id), dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        userService.deleteUser(UUID.fromString(id));
        return ResponseEntity.ok("Deleted");
    }
}