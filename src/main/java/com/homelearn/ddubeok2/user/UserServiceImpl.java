package com.homelearn.ddubeok2.user;

import com.homelearn.ddubeok2.user.dto.AddUserForm;
import com.homelearn.ddubeok2.user.dto.EditUserForm;
import com.homelearn.ddubeok2.user.dto.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    /**
     * 회원가입
     * @param addForm 회원가입 폼
     * @return 추가된 회원 ID
     */
    @Override
    public void addUser(AddUserForm addForm) {
        userMapper.addUser(addForm);
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
        return userMapper.login(loginForm);
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
}
