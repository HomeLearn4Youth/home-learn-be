package com.homelearn.back.qna;

import com.homelearn.back.qna.dto.*;
import com.homelearn.back.qna.entity.QuestionJoinUser;
import com.homelearn.back.user.entity.User;

import java.util.List;

public interface QuestionService {
    //질문 등록
    void addQuestion(QuestionInputSpec input, User user);
    //답변 등록
    void addAnswer(AnswerInputSpec input, User user);
    //질문 삭제
    void deleteQuestion(Long questionId, User user);
    //질문 리스트 찾기
    List<QuestionJoinUser> findQuestion(QuestionListInputSpec param);
    //질문 상세 보기
    QuestionJoinUser findQuestionById(Long questionId);
    Integer getTotalCount();
}
