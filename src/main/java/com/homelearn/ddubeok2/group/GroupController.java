package com.homelearn.ddubeok2.group;

import com.homelearn.ddubeok2.group.dto.GroupForm;
import com.homelearn.ddubeok2.group.dto.GroupItemInput;
import com.homelearn.ddubeok2.group.dto.GroupOutput;
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
    public ResponseEntity<List<GroupOutput>> showGroupList(
        @PathVariable("userId") Long userId
    ){
        return ResponseEntity.ok().body(groupService.findGroupListByUserId(userId));
    }

    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity<?> deleteGroup(
            @PathVariable("groupId") Long groupId
    ){
        groupService.deleteGroup(groupId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/item/delete")
    public ResponseEntity<?> deleteGroupItem(
        @RequestBody GroupItemInput groupItemInput
    ){
        groupService.deleteGroupItem(groupItemInput);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addGroup(
            @RequestBody GroupForm newGroup
            ){
        groupService.addGroup(newGroup);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/item/add")
    public ResponseEntity<?> addGroupItem(
            @RequestBody GroupItemInput newItem
            ){
        groupService.addGroupItem(newItem);
        return ResponseEntity.ok().build();
    }



}
