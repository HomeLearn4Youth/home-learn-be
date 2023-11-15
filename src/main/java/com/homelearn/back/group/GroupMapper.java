package com.homelearn.back.group;

import com.homelearn.back.group.dto.GroupParam;
import com.homelearn.back.group.dto.GroupItemInputSpec;
import com.homelearn.back.group.entity.Group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

    void addGroup(GroupParam param);
    void deleteGroup(Long groupId);
    List<Group> findGroupListByUserId(Long groupId);
    void addGroupItem(GroupItemInputSpec groupItemInputSpec);
    void deleteGroupItem(GroupItemInputSpec groupItemInputSpec);
}
