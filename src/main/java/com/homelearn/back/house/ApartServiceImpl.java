package com.homelearn.back.house;

import com.homelearn.back.house.dto.*;
import com.homelearn.back.house.entity.HouseJoinLike;
import com.homelearn.back.house.exception.HouseErrorCode;
import com.homelearn.back.house.exception.HouseException;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.homelearn.back.house.exception.HouseErrorCode.*;

@Service
@RequiredArgsConstructor
public class ApartServiceImpl implements ApartService{

    private final ApartMapper apartMapper;

    @Override
    public List<HouseJoinLike> getApartList(ApartListInputSpec input, User user) {
        return apartMapper.getApartList(
                new ApartListParam().apartListInputSpecToApartListParam(input,user.getId()));
    }

    @Override
    public ApartOutputSpec getApartInfoById(Long apartCode, User user) {
        return new ApartOutputSpec()
                .houseJoinLikeToApartOutputSpec(
                        apartMapper.getApartInfoById(ApartInfoParam.builder()
                                        .aptCode(apartCode)
                                        .userId(user.getId())
                                        .build())
                                .orElseThrow(()->new HouseException(NOT_EXISTS_HOUSE)));
    }

    @Override
    public List<DealListOutputSpec> getApartDealList(DealListInputSpec dealListInputSpec) {
        return apartMapper.getApartDealList(dealListInputSpec);
    }
}
