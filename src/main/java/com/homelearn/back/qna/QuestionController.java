package com.homelearn.back.qna;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.qna.dto.*;
import com.homelearn.back.user.UserMapper;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final UserMapper userMapper;
    @PostMapping("/add/{userId}")
    public ResponseEntity<MessageUtil> addQuestion(
            @RequestBody QuestionInputSpec inputSpec,
            @PathVariable("userId") Long userId
            ){
        User loginUser = userMapper.findByIdUser(userId);
        questionService.addQuestion(inputSpec,loginUser);
        return ResponseEntity.ok().body(MessageUtil.success());
    }


    @PutMapping("/answer/{userId}")
    public ResponseEntity<MessageUtil> addAnswer(
            @RequestBody AnswerInputSpec inputSpec,
            @PathVariable("userId") Long userId
            ){
        User loginUser = userMapper.findByIdUser(userId);
        questionService.addAnswer(inputSpec, loginUser);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @DeleteMapping("/delete/{questionId}/{userId}")
    public ResponseEntity<MessageUtil> deleteQuestion(
            @PathVariable("questionId") Long questionId,
            @PathVariable("userId") Long userId
    ){
        User loginUser = userMapper.findByIdUser(userId);
        questionService.deleteQuestion(questionId,loginUser);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @GetMapping("/findlist")
    public ResponseEntity<MessageUtil<List<QuestionOutputSpec>>> findQuestionList(
            @ModelAttribute QuestionListInputSpec inputSpec
    ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        questionService.findQuestion(inputSpec)
                                .stream()
                                .map(
                                        m-> new QuestionOutputSpec().questionJoinUserToOutputSpec(m))
                                .collect(Collectors.toList())));
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
