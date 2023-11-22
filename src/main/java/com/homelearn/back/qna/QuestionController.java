package com.homelearn.back.qna;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.qna.dto.*;
import com.homelearn.back.user.UserMapper;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final UserMapper userMapper;
    @PostMapping("/add")
    public ResponseEntity<MessageUtil> addQuestion(
            @RequestBody QuestionInputSpec inputSpec,
            @AuthenticationPrincipal User loginUser
            ){
        questionService.addQuestion(inputSpec,loginUser);
        return ResponseEntity.ok().body(MessageUtil.success());
    }


    @PutMapping("/answer")
    public ResponseEntity<MessageUtil> addAnswer(
            @RequestBody AnswerInputSpec inputSpec,
            @AuthenticationPrincipal User loginUser
            ){
        questionService.addAnswer(inputSpec, loginUser);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<MessageUtil> deleteQuestion(
            @PathVariable("questionId") Long questionId,
            @AuthenticationPrincipal User loginUser
    ){
        questionService.deleteQuestion(questionId,loginUser);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @GetMapping("/findlist")
    public ResponseEntity<MessageUtil<QuestionListOutputSpec>> findQuestionList(
            @ModelAttribute QuestionListInputSpec inputSpec
    ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        QuestionListOutputSpec.builder()
                                .requestStartIndex(inputSpec.getStartIndex())
                                .requestCount(inputSpec.getCount())
                                .totalCount(questionService.getTotalCount())
                                .items(questionService.findQuestion(inputSpec)
                                        .stream()
                                        .map(
                                                m-> new QuestionOutputSpec().questionJoinUserToOutputSpec(m))
                                        .collect(Collectors.toList()))
                                .build())
                        );
    }

    @GetMapping("/find/{questionId}")
    public ResponseEntity<MessageUtil<QuestionOutputSpec>> findQuestion(
            @PathVariable("questionId") Long questionId
    ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        new QuestionOutputSpec()
                                .questionJoinUserToOutputSpec(
                                        questionService.findQuestionById(questionId))));
    }

}
