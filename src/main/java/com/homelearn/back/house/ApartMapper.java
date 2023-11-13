package com.homelearn.back.house;

import com.homelearn.back.house.dto.ApartInfoOutput;
import com.homelearn.back.house.dto.ApartListOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApartMapper {
    //아파트 전체 조회
    List<ApartListOutput> getTotalApart();
    //아파트 상세 검색
    //아파트 이름으로 조회
    List<ApartListOutput> getApartListByName(String name);
    //아파트 동 이름으로 조회
    List<ApartListOutput> getApartListByDongName(String name);
    //동 코드별 아파트
    List<ApartListOutput> getApartListByDongCode(String dongCode);
    //아파트 개별 조회
    ApartInfoOutput getApartInfoById(String code);
}
