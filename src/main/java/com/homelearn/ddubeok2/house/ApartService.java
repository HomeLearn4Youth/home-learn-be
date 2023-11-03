package com.homelearn.ddubeok2.house;

import com.homelearn.ddubeok2.house.dto.ApartInfo;

import java.util.List;

public interface ApartService {
    //아파트 전체 조회
    List<Apart> getTotalApart();
    //아파트 상세 검색
    //아파트 이름으로 조회
    List<Apart> getApartListByName(String name);
    //아파트 동 이름으로 조회
    List<Apart> getApartListByDongName(String name);
    //동 코드별 아파트
    List<Apart> getApartListByDongCode(String dongCode);
    //아파트 개별 조회
    ApartInfo getApartInfoById(String code);
}
