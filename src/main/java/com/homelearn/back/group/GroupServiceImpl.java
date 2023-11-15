package com.homelearn.back.group;

import com.homelearn.back.group.dto.*;
import com.homelearn.back.group.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupMapper groupMapper;


    @Override
    public void addGroup(GroupParam param) {
        groupMapper.addGroup(param);
    }

    @Override
    public void deleteGroup(Long groupId) {
        groupMapper.deleteGroup(groupId);
    }

    @Override
    public List<Group> findGroupListByUserId(Long groupId) {
        return groupMapper.findGroupListByUserId(groupId);
    }

    @Override
    public void addGroupItem(GroupItemInputSpec groupItemInputSpec) {
        groupMapper.addGroupItem(groupItemInputSpec);
    }

    @Override
    public void deleteGroupItem(GroupItemInputSpec groupItemInputSpec) {
        groupMapper.deleteGroupItem(groupItemInputSpec);
    }

}
