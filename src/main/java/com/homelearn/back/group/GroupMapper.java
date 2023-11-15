package com.homelearn.back.group;

import com.homelearn.back.group.entity.Group;
import com.homelearn.back.group.dto.GroupForm;
import com.homelearn.back.group.dto.GroupItemInput;
import com.homelearn.back.group.dto.GroupItemOutput;
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
