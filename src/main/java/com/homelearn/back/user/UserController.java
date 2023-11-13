package com.homelearn.back.user;

import com.homelearn.back.user.dto.AddUserForm;
import com.homelearn.back.user.dto.EditUserForm;
import com.homelearn.back.user.dto.LoginForm;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 로그인 기능 추후 수정 필요함
     * @param loginForm
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody LoginForm loginForm,
            HttpSession session
    ){
        User loginUser = userService.login(loginForm);
        if (loginUser!=null){
            session.setAttribute("loginUser", loginUser);
        }
        return ResponseEntity.ok().build();
    }
    /**
     * 로그아웃 기능 추후 수정 필요함
     * @return
     */
    @GetMapping("/logout")
    public ResponseEntity logout(
            HttpSession session
    ){
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    /**
     * 회원가입
     * @param userForm
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity addUser(
            @RequestBody AddUserForm userForm
            )
    {
        userService.addUser(userForm);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 조회
     * @param userId
     * @return
     */
    @GetMapping("/find/{userId}")
    public ResponseEntity<User> findUser(
            @PathVariable("userId") Long userId
            )
    {
        return ResponseEntity.ok().body(userService.findByIdUser(userId));
    }

    /**
     * 회원수정
     * @param userForm
     * @return
     */
    @PutMapping("/edit")
    public ResponseEntity editUser(
            @RequestBody EditUserForm userForm
            ){
        userService.editUser(userForm);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 리스트 조회
     * @return
     */
    @GetMapping("/findlist")
    public ResponseEntity<List<User>> findUsers(){
        return ResponseEntity.ok().body(userService.findByAllUsers());
    }

    /**
     * 회원 탈퇴
     * @param loginForm
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody LoginForm loginForm){
        userService.deleteUser(loginForm);
        return ResponseEntity.ok().build();
    }

}
