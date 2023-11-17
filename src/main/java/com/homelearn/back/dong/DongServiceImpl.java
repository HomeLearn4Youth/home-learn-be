package com.homelearn.back.dong;

import com.homelearn.back.dong.dto.Dong;
import com.homelearn.back.dong.dto.Gugun;
import com.homelearn.back.dong.dto.Sido;
import com.homelearn.back.dong.exception.DongErrorCode;
import com.homelearn.back.dong.exception.DongException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.homelearn.back.dong.exception.DongErrorCode.*;


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
        List<Gugun> gugunList = dongMapper.getGugunList(code);
        if (gugunList==null||gugunList.isEmpty()){
            throw new DongException(NOT_EXISTS_CODE);
        }
        return gugunList;
    }

    @Override
    public List<Dong> getDongList(String code) {
        List<Dong> dongList = dongMapper.getDongList(code);
        if (dongList==null||dongList.isEmpty()){
            throw new DongException(NOT_EXISTS_CODE);
        }
        return dongList;
    }
}
