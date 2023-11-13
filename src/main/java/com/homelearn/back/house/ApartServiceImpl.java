package com.homelearn.back.house;

import com.homelearn.back.house.dto.ApartDealOutput;
import com.homelearn.back.house.dto.ApartInfoOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartServiceImpl implements ApartService{
    private final ApartMapper apartMapper;

    @Override
    public List<ApartInfoOutput> getApartListByDongCode(Long dongCode) {
        return null;
    }

    @Override
    public ApartInfoOutput getApartInfoById(Long aptCode) {
        return null;
    }

    @Override
    public List<ApartDealOutput> getApartDealById(Long aptCode) {
        return null;
    }
}
