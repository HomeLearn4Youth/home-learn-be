package com.homelearn.back.house;

import com.homelearn.back.house.dto.ApartInfoOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApartMapper {
    //동 코드별 아파트
    List<ApartInfoOutput> getApartListByDongCode(String dongCode);
    //아파트 개별 조회
    ApartInfoOutput getApartInfoById(String code);
}
