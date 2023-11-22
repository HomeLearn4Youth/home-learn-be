package com.homelearn.back.group.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GroupListCountParam {
    private Long userId;
    private Long groupId;
}
