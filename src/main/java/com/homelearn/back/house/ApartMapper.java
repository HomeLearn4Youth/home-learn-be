package com.homelearn.back.house;


import com.homelearn.back.house.dto.*;
import com.homelearn.back.house.entity.HouseInfo;
import com.homelearn.back.house.entity.HouseJoinLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ApartMapper {
    //동 코드별 아파트
    List<HouseJoinLike> getApartList(ApartListParam listParam);
    //아파트 개별 조회
    Optional<HouseJoinLike> getApartInfoById(ApartInfoParam infoParam);
    //아파트 거래내역 조회
    List<DealListOutputSpec> getApartDealList(DealListInputParam inputParam);
    Optional<HouseInfo> findApartByApartCode(Long ApartCode);
    void addAptImg(AddApartImgParam param);
}
