package com.homelearn.back.user;

import com.homelearn.back.common.util.JwtUtils;
import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.user.dto.AddUserForm;
import com.homelearn.back.user.dto.EditUserForm;
import com.homelearn.back.user.dto.LoginForm;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final RefreshService refreshService;

    @PostMapping("/login")
    public ResponseEntity<MessageUtil> login(
            @RequestBody LoginForm loginForm,
            @RequestHeader HttpHeaders headers
    ){
        log.debug("login process");
        try {
            User user=userService.login(loginForm);
            String accessToken = jwtUtils.issueAccessToken(user);
            String refreshToken = jwtUtils.issueRefreshToken(user);
            refreshService.save(refreshToken, user.getId());
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .header("refresh_token", "Bearer " + refreshToken)
                    .body(MessageUtil.success());
        }catch (Exception e){
            return ResponseEntity
                    .ok()
                    .body(MessageUtil.error(HttpStatus.UNAUTHORIZED, e.getMessage()));
        }
    }
    @GetMapping("/refresh")
    public ResponseEntity<MessageUtil> refresh(@RequestHeader HttpHeaders headers){
        List<String> refreshTokenList = headers.get("refresh_token");

        if (refreshTokenList != null && !refreshTokenList.isEmpty()) {
            try {
                String refreshToken = refreshTokenList.get(0); // 리스트의 첫 번째 요소를 가져옵니다.

                if (refreshToken.startsWith("Bearer ")) {
                    refreshToken = refreshToken.substring(7); // "Bearer "를 제거
                }

                log.info("refresh token : " + refreshToken);
                String accessToken = refreshService.match(refreshToken);

                return ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                        .header("refresh_token", "Bearer " + refreshToken)
                        .body(MessageUtil.success());
            }catch (Exception e){
                return ResponseEntity.ok()
                        .body(MessageUtil.error(HttpStatus.UNAUTHORIZED,e.getMessage()));
            }
        }

        return ResponseEntity
                .ok()
                .body(MessageUtil.error(HttpStatus.BAD_REQUEST,"리프레시 토큰이 없습니다."));
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
    public ResponseEntity<MessageUtil> addUser(
            @RequestBody AddUserForm userForm
    )
    {
        log.debug("add user");
        try {
            userService.addUser(userForm);
            return ResponseEntity.ok().body(MessageUtil.success());
        } catch (Exception e){
            return ResponseEntity.ok()
                    .body(MessageUtil.error(HttpStatus.UNAUTHORIZED, e.getMessage()));
        }
    }

    /**
     * 회원 조회
     * @return
     */
    @GetMapping("/find")
    public ResponseEntity<MessageUtil<User>> findUser(@AuthenticationPrincipal User user){
        User foundUser=userService.findByIdUser(user.getId());
        log.debug(foundUser.toString());
        return ResponseEntity.ok().body(MessageUtil.success(foundUser));
    }

    /**
     * 회원 조회
     * @return
     */
    @GetMapping("/find/{userId}")
    public ResponseEntity<MessageUtil<User>> findUser(@PathVariable Long userId){
        User foundUser=userService.findByIdUser(userId);
        log.debug(foundUser.toString());
        return ResponseEntity.ok().body(MessageUtil.success(foundUser));
    }

    /**
     * 회원수정
     * @param userForm
     * @return
     */
    @PutMapping("/edit")
    public ResponseEntity<MessageUtil> editUser(
            @RequestBody EditUserForm userForm,
            @AuthenticationPrincipal User user
    ){
        userForm.setId(user.getId());
        userService.editUser(userForm);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    /**
     * 회원 리스트 조회
     * @return
     */
    @GetMapping("/findlist")
    public ResponseEntity<MessageUtil<List<User>>> findUsers(){
        return ResponseEntity.ok().body(MessageUtil.success(userService.findByAllUsers()));
    }

    /**
     * 회원 탈퇴
     * @param loginForm
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<MessageUtil> deleteUser(@RequestBody LoginForm loginForm){
        userService.deleteUser(loginForm);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

}
