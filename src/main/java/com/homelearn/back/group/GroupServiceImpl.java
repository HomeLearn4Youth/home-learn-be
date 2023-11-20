package com.homelearn.back.group;

import com.homelearn.back.group.dto.*;
import com.homelearn.back.group.entity.Group;
import com.homelearn.back.group.entity.GroupItem;
import com.homelearn.back.group.exception.GroupErrorCode;
import com.homelearn.back.group.exception.GroupException;
import com.homelearn.back.like.LikeMapper;
import com.homelearn.back.like.dto.LikeParam;
import com.homelearn.back.like.exception.LikeErrorCode;
import com.homelearn.back.like.exception.LikeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.homelearn.back.group.exception.GroupErrorCode.*;
import static com.homelearn.back.like.exception.LikeErrorCode.*;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupMapper groupMapper;
    private final LikeMapper likeMapper;

    @Override
    public void addGroup(GroupParam param) {
        groupMapper.addGroup(param);
    }


    @Override
    public void deleteGroup(Long groupId, Long loginUserId) {
        if (groupMapper.findGroup(groupId)
                .orElseThrow(()->new GroupException(NOT_EXISTS_GROUP))
                .getUserId() != loginUserId) throw new GroupException(FORBIDDEN_GROUP_ITEM);
        groupMapper.deleteGroup(groupId);
    }

    @Override
    public List<Group> findGroupListByUserId(Long userId) {
        return groupMapper.findGroupListByUserId(userId);
    }

    @Override
    public void addGroupItem(GroupItemInputSpec groupItemInputSpec, Long loginUserId) {
        if(likeMapper.findLikeByLikeId(groupItemInputSpec.getLikeId())
                .orElseThrow(()-> new LikeException(NOT_EXISTS_LIKE_APART))
                .getUserId()!=loginUserId) throw new LikeException(FORBIDDEN_LIKE_APART);
        groupMapper.findGroup(groupItemInputSpec.getGroupId())
                .orElseThrow(()->new GroupException(NOT_EXISTS_GROUP));
        groupMapper.findGroupItem(groupItemInputSpec)
                .ifPresent(groupItem -> {throw new GroupException(ALREADY_IN_GROUP_ITEM);});
        groupMapper.addGroupItem(groupItemInputSpec);
    }

    @Override
    public void deleteGroupItem(GroupItemInputSpec groupItemInputSpec, Long loginUserId) {
        if(likeMapper.findLikeByLikeId(groupItemInputSpec.getLikeId())
                .orElseThrow(()-> new LikeException(NOT_EXISTS_LIKE_APART))
                .getUserId()!=loginUserId) throw new LikeException(FORBIDDEN_LIKE_APART);
        if(groupMapper.findGroup(groupItemInputSpec.getGroupId())
                .orElseThrow(()->new GroupException(NOT_EXISTS_GROUP))
                .getUserId()!=loginUserId) throw new GroupException(FORBIDDEN_GROUP_ITEM);
        groupMapper.findGroupItem(groupItemInputSpec)
                .orElseThrow(()->new GroupException(NOT_EXISTS_GROUP_ITEM));
        groupMapper.deleteGroupItem(groupItemInputSpec);
    }

}
