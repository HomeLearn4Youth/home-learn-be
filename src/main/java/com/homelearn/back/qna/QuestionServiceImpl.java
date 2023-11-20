package com.homelearn.back.qna;

import com.homelearn.back.qna.dto.*;
import com.homelearn.back.qna.entity.QuestionJoinUser;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionMapper questionMapper;

    @Override
    public void addQuestion(QuestionInputSpec input, User user) {

    }

    @Override
    public void addAnswer(AnswerInputSpec input, User user) {

    }

    @Override
    public void deleteQuestion(Long questionId, User user) {

    }

    @Override
    public List<QuestionJoinUser> findQuestion(QuestionListInputSpec param) {
        return null;
    }

    @Override
    public QuestionJoinUser findQuestionById(Long questionId) {
        return null;
    }
}
