package com.homelearn.back.house;


import com.homelearn.back.house.dto.*;
import com.homelearn.back.house.entity.HouseJoinLike;
import com.homelearn.back.user.entity.User;


import java.util.List;


public interface ApartService {
    //동 코드별 아파트
    List<HouseJoinLike>  getApartList(ApartListInputSpec input, User user);
    //아파트 개별 조회
    HouseJoinLike getApartInfoById(Long apartCode, User user);
    //아파트 거래내역 조회
    List<DealListOutputSpec> getApartDealList(DealListInputSpec dealListInputSpec);

}
