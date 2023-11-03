package com.homelearn.ddubeok2.user;

import com.homelearn.ddubeok2.user.dto.AddUserForm;
import com.homelearn.ddubeok2.user.dto.EditUserForm;
import com.homelearn.ddubeok2.user.dto.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> Login(
            @RequestBody LoginForm loginForm
    ){
        return ResponseEntity.ok().body(userService.login(loginForm));
    }

    @PostMapping("/add")
    public ResponseEntity addUser(
            @RequestBody AddUserForm userForm
            )
    {
        userService.addUser(userForm);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity editUser(
            @RequestBody EditUserForm userForm
            ){
        userService.editUser(userForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> findUsers(){
        return ResponseEntity.ok().body(userService.findByAllUsers());
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody LoginForm loginForm){
        userService.deleteUser(loginForm);
        return ResponseEntity.ok().build();
    }

}
