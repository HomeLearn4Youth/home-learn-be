package com.homelearn.back.qna;

import com.homelearn.back.qna.dto.*;
import com.homelearn.back.qna.entity.QuestionJoinUser;
import com.homelearn.back.qna.exception.QuestionException;
import com.homelearn.back.user.UserRole;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.homelearn.back.qna.exception.QuestionErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionMapper questionMapper;

    @Override
    public void addQuestion(QuestionInputSpec input, User user) {
        questionMapper.addQuestion(QuestionInputParam.builder()
                        .title(input.getTitle())
                        .question(input.getQuestion())
                        .loginUserId(user.getId())
                        .build());
    }

    @Override
    public void addAnswer(AnswerInputSpec input, User user) {
        if (user.getRole()!=UserRole.ADMIN) throw new QuestionException(FORBIDDEN_QUESTION);
        questionMapper.addAnswer(input);
    }

    @Override
    public void deleteQuestion(Long questionId, User user) {
        if (user.getRole()==UserRole.ADMIN
                || user.getId()==questionMapper.findQuestionById(questionId)
                        .orElseThrow(()->new QuestionException(NOT_EXISTS_QUESTION)).getUserId()){
            questionMapper.deleteQuestion(questionId);
            return;
        }
        throw new QuestionException(FORBIDDEN_QUESTION);
    }

    @Override
    public List<QuestionJoinUser> findQuestion(QuestionListInputSpec inputSpec) {
        return questionMapper.findQuestion(inputSpec);
    }

    @Override
    public QuestionJoinUser findQuestionById(Long questionId) {
        return questionMapper.findQuestionById(questionId).orElseThrow(()->new QuestionException(NOT_EXISTS_QUESTION));
    }

    @Override
    public Integer getTotalCount() {
        return questionMapper.getTotalCount();
    }
}
