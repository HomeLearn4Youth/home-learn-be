package com.homelearn.back.user;

import com.homelearn.back.user.dto.AddUserForm;
import com.homelearn.back.user.dto.LoginForm;
import com.homelearn.back.user.dto.EditUserForm;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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


}
