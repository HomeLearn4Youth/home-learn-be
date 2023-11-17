package com.homelearn.back.group;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.group.dto.GroupInputSpec;
import com.homelearn.back.group.dto.GroupItemInputSpec;
import com.homelearn.back.group.dto.GroupParam;
import com.homelearn.back.group.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/findlist/{userId}")
    public ResponseEntity<MessageUtil<List<Group>>> showGroupList(
        @PathVariable("userId") Long userId
    ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        groupService.findGroupListByUserId(userId)
                ));
    }

    @DeleteMapping("/delete/{groupId}/{userId}")
    public ResponseEntity<MessageUtil> deleteGroup(
            @PathVariable("groupId") Long groupId,
            @PathVariable("userId") Long userId
    ){
        groupService.deleteGroup(groupId, userId);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @DeleteMapping("/item/delete/{userId}")
    public ResponseEntity<MessageUtil> deleteGroupItem(
        @ModelAttribute GroupItemInputSpec groupItemInputSpec,
        @PathVariable("userId") Long userId
    ){
        groupService.deleteGroupItem(groupItemInputSpec, userId);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<MessageUtil> addGroup(
            @PathVariable("userId") Long userId,
            @RequestBody GroupInputSpec input
            ){
        groupService.addGroup(
                GroupParam.builder()
                    .groupName(input.getGroupName())
                    .userId(userId)
                    .build()
                );
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @PostMapping("/item/add/{userId}")
    public ResponseEntity<MessageUtil> addGroupItem(
            @RequestBody GroupItemInputSpec newItem,
            @PathVariable Long userId
            ){
        groupService.addGroupItem(newItem, userId);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

}
