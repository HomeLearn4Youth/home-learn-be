package com.homelearn.back.house;

import com.homelearn.back.house.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartServiceImpl implements ApartService{

    private final ApartMapper apartMapper;

    @Override
    public List<ApartOutputSpec> getApartList(ApartListParam listParam) {
        return apartMapper.getApartList(listParam)
                .stream()
                .map(m-> new ApartOutputSpec()
                        .houseJoinLikeToApartOutputSpec(m))
                .collect(Collectors.toList());
    }

    @Override
    public ApartOutputSpec getApartInfoById(ApartInfoParam infoParam) {
        return new ApartOutputSpec()
                .houseJoinLikeToApartOutputSpec(apartMapper.getApartInfoById(infoParam));
    }

    @Override
    public List<DealListOutputSpec> getApartDealList(DealListInputSpec dealListInputSpec) {
        return apartMapper.getApartDealList(dealListInputSpec);
    }
}
