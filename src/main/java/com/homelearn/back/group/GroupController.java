package com.homelearn.back.group;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.group.dto.GroupInputSpec;
import com.homelearn.back.group.dto.GroupItemInputSpec;
import com.homelearn.back.group.dto.GroupItemListOutputSpec;
import com.homelearn.back.group.dto.GroupParam;
import com.homelearn.back.group.entity.Group;
import com.homelearn.back.house.dto.ApartListInputSpec;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/findlist")
    public ResponseEntity<MessageUtil<List<Group>>> showGroupList(
            @AuthenticationPrincipal User user
            ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        groupService.findGroupListByUserId(user.getId())
                ));
    }

    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity<MessageUtil> deleteGroup(
            @PathVariable("groupId") Long groupId,
            @AuthenticationPrincipal User user
    ){
        groupService.deleteGroup(groupId, user.getId());
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @DeleteMapping("/item/delete")
    public ResponseEntity<MessageUtil> deleteGroupItem(
        @ModelAttribute GroupItemInputSpec groupItemInputSpec,
        @AuthenticationPrincipal User user
    ){
        groupService.deleteGroupItem(groupItemInputSpec, user.getId());
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @PostMapping("/add")
    public ResponseEntity<MessageUtil> addGroup(
            @AuthenticationPrincipal User user,
            @RequestBody GroupInputSpec input
            ){
        groupService.addGroup(
                GroupParam.builder()
                    .groupName(input.getGroupName())
                    .userId(user.getId())
                    .build()
                );
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @PostMapping("/item/add")
    public ResponseEntity<MessageUtil> addGroupItem(
            @RequestBody GroupItemInputSpec newItem,
            @AuthenticationPrincipal User user
            ){
        groupService.addGroupItem(newItem, user.getId());
        return ResponseEntity.ok().body(MessageUtil.success());
    }
    @GetMapping("/grouplist")
    public ResponseEntity<MessageUtil<GroupItemListOutputSpec>> findGroupItemList(
            @ModelAttribute ApartListInputSpec inputSpec,
            @AuthenticationPrincipal User user
    ){
        return ResponseEntity.ok().body(MessageUtil.success(groupService.findGroupItemList(inputSpec, user)));
    }
}
