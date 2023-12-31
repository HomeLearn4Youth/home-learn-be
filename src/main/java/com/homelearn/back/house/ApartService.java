package com.homelearn.back.house;


import com.homelearn.back.house.dto.*;
import com.homelearn.back.user.entity.User;


import java.util.List;


public interface ApartService {
    //동 코드별 아파트
    List<ApartOutputSpec> getApartList(ApartListParam listParam);
    //아파트 개별 조회
    ApartOutputSpec getApartInfoById(ApartInfoParam infoParam);
    //아파트 거래내역 조회
    List<DealListOutputSpec> getApartDealList(DealListInputSpec dealListInputSpec);
}
