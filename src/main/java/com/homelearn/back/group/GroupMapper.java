package com.homelearn.back.group;

import com.homelearn.back.group.dto.GroupListCountParam;
import com.homelearn.back.group.dto.GroupParam;
import com.homelearn.back.group.dto.GroupItemInputSpec;
import com.homelearn.back.group.entity.Group;
import com.homelearn.back.group.entity.GroupItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GroupMapper {

    void addGroup(GroupParam param);
    void deleteGroup(Long groupId);
    Optional<Group> findGroup(Long groupId);
    List<Group> findGroupListByUserId(Long groupId);
    void addGroupItem(GroupItemInputSpec groupItemInputSpec);
    Optional<GroupItem> findGroupItem(GroupItemInputSpec groupItemInputSpec);
    void deleteGroupItem(GroupItemInputSpec groupItemInputSpec);

    Integer findGroupCount(GroupListCountParam param);
}
