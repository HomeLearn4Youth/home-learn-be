package com.homelearn.ddubeok2.group.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GroupOutput {
    private Long groupId;
    private String name;
    List<GroupItemOutput> userLikeGroups;
}
