package com.homelearn.back.user;

import com.homelearn.back.common.util.JwtUtils;
import com.homelearn.back.oauth.dto.OAuthDto;
import com.homelearn.back.user.dto.AddUserForm;
import com.homelearn.back.user.dto.LoginForm;
import com.homelearn.back.user.dto.EditUserForm;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    @Value(value = "${dummy.adminPassword}")
    private String adminPassword;
    /**
     * 회원가입
     * @param addForm 회원가입 폼
     * @return 추가된 회원 ID
     */
    @Override
    public Long addUser(AddUserForm addForm) {
        User newUser=User.builder()
                .name(addForm.getName())
                .email(addForm.getEmail())
                .password(addForm.getPassword())
                .role(UserRole.DDUBEOKY)
                .build();
        if(addForm.getAdminPassword()!=null&&addForm.getAdminPassword().equals(adminPassword)){//관리자 비밀번호와 일치
            newUser=newUser.toBuilder()
                    .role(UserRole.ADMIN)
                    .build();
        }
        return userMapper.addUser(newUser);
    }

    /**
     * 회원조회
     * @param id 조회할 회원 ID
     * @return 회원 정보
     */
    @Override
    public User findByIdUser(Long id) {
        return userMapper.findByIdUser(id);
    }

    @Override
    public User findByEmail(String email) {
        log.info(email);
        return userMapper.findByEmail(email);
    }

    /**
     * 회원 리스트 조회
     * @return 회원 리스트
     */
    @Override
    public List<User> findByAllUsers() {
        return userMapper.findByAllUsers();
    }

    /**
     * 로그인
     * @param loginForm 로그인 폼
     * @return 로그인한 회원정보
     */
    @Override
    public User login(LoginForm loginForm) {
        Optional<User> user= Optional.ofNullable(userMapper.login(loginForm));
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    /**
     * 회원 수정
     * @param editForm 회원수정 폼
     * @return 수정한 회원 ID
     */
    @Override
    public void editUser(EditUserForm editForm) {
        userMapper.editUser(editForm);
    }

    /**
     * 회원 탈퇴
     * @param deleteForm 삭제 인증 폼
     * @return 삭제한 회원 ID
     */
    @Override
    public void deleteUser(LoginForm deleteForm) {
        userMapper.deleteUser(deleteForm);
    }

    @Override
    public User findOrCreate(OAuthDto oAuthDto) {
        User user=userMapper.findByEmail(oAuthDto.getEmail());
//        log.debug(user.toString());
        if(user==null){//기존에 존재하지 않는 사용자
            user=oAuthDto.toEntity();
            user.toBuilder().id(userMapper.addUser(user));
        }
        return user;
    }

}
