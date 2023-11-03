package com.homelearn.ddubeok2.house;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartServiceImpl implements ApartService{
    private final ApartMapper apartMapper;

    @Override
    public List<Apart> getTotalApart() {
        System.out.println(apartMapper.getTotalApart());
        return apartMapper.getTotalApart();
    }

    @Override
    public List<Apart> getApartListByName(String name) {
        return apartMapper.getApartListByName(name);
    }

    @Override
    public List<Apart> getApartListByDongName(String name) {
        return apartMapper.getApartListByDongName(name);
    }

    @Override
    public List<Apart> getApartListByDongCode(String dongCode) {
        return apartMapper.getApartListByDongCode(dongCode);
    }

    @Override
    public Apart getApartById(String code) {
        return apartMapper.getApartById(code);
    }
}
