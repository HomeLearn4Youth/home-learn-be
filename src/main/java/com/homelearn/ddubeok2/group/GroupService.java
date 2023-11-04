package com.homelearn.ddubeok2.group;

import com.homelearn.ddubeok2.group.dto.Group;
import com.homelearn.ddubeok2.group.dto.GroupForm;
import com.homelearn.ddubeok2.group.dto.GroupOutput;
import com.homelearn.ddubeok2.group.dto.GroupItemInput;

import java.util.List;

public interface GroupService {
    void addGroup(GroupForm newGroup);
    void deleteGroup(Long groupId);
    List<GroupOutput> findGroupListByUserId(Long groupId);
    void addGroupItem(GroupItemInput groupItemInput);
    void deleteGroupItem(GroupItemInput groupItemInput);
    void editGroupName(Group editForm);
}
