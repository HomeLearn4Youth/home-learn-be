package com.homelearn.back.dong;

import com.homelearn.back.dong.dto.Dong;
import com.homelearn.back.dong.dto.Gugun;
import com.homelearn.back.dong.dto.Sido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DongServiceImpl implements DongService{
    private final DongMapper dongMapper;

    @Override
    public List<Sido> getSidoList() {
        return dongMapper.getSidoList();
    }

    @Override
    public List<Gugun> getGugunList(String code) {
        return dongMapper.getGugunList(code);
    }

    @Override
    public List<Dong> getDongList(String code) {
        return dongMapper.getDongList(code);
    }
}
