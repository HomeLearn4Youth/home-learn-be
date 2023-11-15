package com.homelearn.back.group;

import com.homelearn.back.group.dto.GroupInputSpec;
import com.homelearn.back.group.dto.GroupItemInputSpec;
import com.homelearn.back.group.dto.GroupParam;
import com.homelearn.back.group.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Group>> showGroupList(
        @PathVariable("userId") Long userId
    ){
        return ResponseEntity.ok().body(groupService.findGroupListByUserId(userId));
    }

    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity deleteGroup(
            @PathVariable("groupId") Long groupId
    ){
        groupService.deleteGroup(groupId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/item/delete")
    public ResponseEntity deleteGroupItem(
        @ModelAttribute GroupItemInputSpec groupItemInputSpec
    ){
        groupService.deleteGroupItem(groupItemInputSpec);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity addGroup(
            @PathVariable("userId") Long userId,
            @RequestBody GroupInputSpec input
            ){
        groupService.addGroup(
                GroupParam.builder()
                    .groupName(input.getGroupName())
                    .userId(userId)
                    .build()
                );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/item/add")
    public ResponseEntity addGroupItem(
            @RequestBody GroupItemInputSpec newItem
            ){
        groupService.addGroupItem(newItem);
        return ResponseEntity.ok().build();
    }

}
