package com.homelearn.back.house;


import com.homelearn.back.house.dto.ApartInfoParam;
import com.homelearn.back.house.dto.ApartListParam;
import com.homelearn.back.house.dto.DealListInputSpec;
import com.homelearn.back.house.dto.DealListOutputSpec;
import com.homelearn.back.house.entity.HouseJoinLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApartMapper {
    //동 코드별 아파트
    List<HouseJoinLike> getApartList(ApartListParam listParam);
    //아파트 개별 조회
    HouseJoinLike getApartInfoById(ApartInfoParam infoParam);
    //아파트 거래내역 조회
    List<DealListOutputSpec> getApartDealList(DealListInputSpec dealListInputSpec);
}
