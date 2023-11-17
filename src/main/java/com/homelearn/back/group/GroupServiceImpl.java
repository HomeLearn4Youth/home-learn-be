package com.homelearn.back.group;

import com.homelearn.back.group.dto.*;
import com.homelearn.back.group.entity.Group;
import com.homelearn.back.group.entity.GroupItem;
import com.homelearn.back.group.exception.GroupErrorCode;
import com.homelearn.back.group.exception.GroupException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.homelearn.back.group.exception.GroupErrorCode.*;

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
        Group group = groupMapper.findGroup(groupId);
        if (group==null){
            throw new GroupException(NOT_EXISTS_GROUP);
        }
        groupMapper.deleteGroup(groupId);
    }

    @Override
    public List<Group> findGroupListByUserId(Long userId) {
        return groupMapper.findGroupListByUserId(userId);
    }

    @Override
    public void addGroupItem(GroupItemInputSpec groupItemInputSpec) {
        GroupItem groupItem = groupMapper.findGroupItem(groupItemInputSpec);
        if (groupItem!=null){
            throw new GroupException(ALREADY_IN_GROUP_ITEM);
        }
        groupMapper.addGroupItem(groupItemInputSpec);
    }

    @Override
    public void deleteGroupItem(GroupItemInputSpec groupItemInputSpec) {
        GroupItem groupItem = groupMapper.findGroupItem(groupItemInputSpec);
        if (groupItem==null){
            throw new GroupException(NOT_EXISTS_GROUP_ITEM);
        }
        groupMapper.deleteGroupItem(groupItemInputSpec);
    }

}
