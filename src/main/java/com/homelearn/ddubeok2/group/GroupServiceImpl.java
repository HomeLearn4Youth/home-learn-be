package com.homelearn.ddubeok2.group;

import com.homelearn.ddubeok2.group.dto.GroupForm;
import com.homelearn.ddubeok2.group.dto.GroupItemInput;
import com.homelearn.ddubeok2.group.dto.GroupOutput;
import com.homelearn.ddubeok2.group.dto.GroupItemOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupMapper groupMapper;

    @Override
    public void addGroup(GroupForm newGroup) {
        groupMapper.addGroup(newGroup);
    }

    @Override
    public void deleteGroup(Long groupId) {
        List<GroupItemOutput> apartIsInGroups = groupMapper.findGroupListByGroupId(groupId);
        // cascade로 수정하기 귀찮아서 이렇게 씀.. 나중에 바꿔야함..
        for (GroupItemOutput apartIsInGroup : apartIsInGroups) {
            GroupItemInput groupItemInput = new GroupItemInput();
            groupItemInput.setGroupId(groupId);
            groupItemInput.setLikeId(apartIsInGroup.getLikeId());
            groupMapper.deleteGroupItem(groupItemInput);
        }
        groupMapper.deleteGroup(groupId);
    }



    @Override
    public List<GroupOutput> findGroupListByUserId(Long userId) {
        List<Group> groupListByUserId = groupMapper.findGroupListByUserId(userId);
        List<GroupOutput> list = new ArrayList<>();
        for (Group group : groupListByUserId) {
            GroupOutput groupOutput = new GroupOutput();
            groupOutput.setGroupId(group.getId());
            groupOutput.setName(group.getName());
            groupOutput.setUserLikeGroups(groupMapper.findGroupListByGroupId(group.getUserId()));
            list.add(groupOutput);
        }
        return list;
    }

    @Override
    public void addGroupItem(GroupItemInput groupItemInput) {
        groupMapper.addGroupItem(groupItemInput);
    }

    @Override
    public void deleteGroupItem(GroupItemInput groupItemInput) {
        groupMapper.deleteGroupItem(groupItemInput);
    }
}
