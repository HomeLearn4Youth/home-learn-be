package com.homelearn.ddubeok2.group;

import com.homelearn.ddubeok2.group.dto.Group;
import com.homelearn.ddubeok2.group.dto.GroupForm;
import com.homelearn.ddubeok2.group.dto.GroupItemInput;
import com.homelearn.ddubeok2.group.dto.GroupItemOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

    void addGroup(GroupForm newGroup);
    void deleteGroup(Long groupId);
    List<Group> findGroupListByUserId(Long userId);
    List<GroupItemOutput> findGroupListByGroupId(Long groupId);
    void addGroupItem(GroupItemInput groupItemInput);
    void deleteGroupItem(GroupItemInput groupItemInput);
    void editGroupName(Group editForm);
}
