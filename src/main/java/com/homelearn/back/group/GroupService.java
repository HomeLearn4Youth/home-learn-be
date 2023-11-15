package com.homelearn.back.group;

import com.homelearn.back.group.entity.Group;
import com.homelearn.back.group.dto.GroupForm;
import com.homelearn.back.group.dto.GroupOutput;
import com.homelearn.back.group.dto.GroupItemInput;

import java.util.List;

public interface GroupService {
    void addGroup(GroupForm newGroup);
    void deleteGroup(Long groupId);
    List<GroupOutput> findGroupListByUserId(Long groupId);
    void addGroupItem(GroupItemInput groupItemInput);
    void deleteGroupItem(GroupItemInput groupItemInput);
    void editGroupName(Group editForm);
}
