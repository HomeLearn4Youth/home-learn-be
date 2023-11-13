package com.homelearn.back.house;

import com.homelearn.back.house.dto.ApartDealOutput;
import com.homelearn.back.house.dto.ApartInfoOutput;

import java.util.List;

public interface ApartService {
    //동 코드별 아파트
    List<ApartInfoOutput> getApartListByDongCode(Long dongCode);
    //아파트 개별 조회
    ApartInfoOutput getApartInfoById(Long aptCode);
    //아파트 거래내역 조회
    List<ApartDealOutput> getApartDealById(Long aptCode);
}
