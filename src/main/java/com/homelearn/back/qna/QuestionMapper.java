package com.homelearn.back.qna;

import com.homelearn.back.qna.dto.*;
import com.homelearn.back.qna.entity.QuestionJoinUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface QuestionMapper {
    //질문 등록
    void addQuestion(QuestionInputParam param);
    //답변 등록
    void addAnswer(AnswerInputSpec inputSpec);
    //질문 삭제
    void deleteQuestion(Long id);
    //질문 리스트 찾기
    List<QuestionJoinUser> findQuestion(QuestionListInputSpec inputSpec);
    //질문 상세 보기
    Optional<QuestionJoinUser> findQuestionById(Long id);

    Integer getTotalCount();
}
