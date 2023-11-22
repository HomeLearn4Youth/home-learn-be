package com.homelearn.back.group.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.homelearn.back.house.dto.ApartOutputSpec;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupItemListOutputSpec {
    private String startX;
    private String startY;
    private String endX;
    private String endY;
    private String passList;
    private List<ApartOutputSpec> items;
}
