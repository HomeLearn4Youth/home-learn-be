package com.homelearn.back.house;

import com.homelearn.back.house.dto.*;
import com.homelearn.back.house.entity.HouseJoinLike;
import com.homelearn.back.house.exception.HouseErrorCode;
import com.homelearn.back.house.exception.HouseException;
import com.homelearn.back.news.CrawlerToObjectMaker;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.homelearn.back.house.exception.HouseErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApartServiceImpl implements ApartService{

    private final ApartMapper apartMapper;
    private final CrawlerToObjectMaker maker;
    @Override
    public List<HouseJoinLike> getApartList(ApartListInputSpec input, User user) {
        return apartMapper.getApartList(
                new ApartListParam().apartListInputSpecToApartListParam(input,user.getId()));
    }

    @Override
    public ApartOutputSpec getApartInfoById(Long apartCode, User user) {
        HouseJoinLike house = apartMapper.getApartInfoById(ApartInfoParam.builder()
                .aptCode(apartCode)
                .userId(user.getId())
                .build()).orElseThrow(() -> new HouseException(NOT_EXISTS_HOUSE));
        return new ApartOutputSpec().houseJoinLikeToApartOutputSpec(house, maker.getImg(house));
    }

    @Override
    public List<DealListOutputSpec> getApartDealList(DealListInputSpec dealListInputSpec) {
        return apartMapper.getApartDealList(dealListInputSpec);
    }

}
