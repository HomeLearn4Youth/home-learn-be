package com.homelearn.back.group;

import com.homelearn.back.group.dto.GroupParam;
import com.homelearn.back.group.entity.Group;
import com.homelearn.back.group.dto.GroupItemInputSpec;

import java.util.List;

public interface GroupService {
    void addGroup(GroupParam param);
    void deleteGroup(Long groupId);
    List<Group> findGroupListByUserId(Long userId);
    void addGroupItem(GroupItemInputSpec groupItemInputSpec);
    void deleteGroupItem(GroupItemInputSpec groupItemInputSpec);
}
