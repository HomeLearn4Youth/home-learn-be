package com.homelearn.ddubeok2.dong;

import com.homelearn.ddubeok2.dong.dto.Dong;
import com.homelearn.ddubeok2.dong.dto.Gugun;
import com.homelearn.ddubeok2.dong.dto.Sido;

import java.util.List;

public interface DongService {
    //시도 리스트
    List<Sido> getSidoList();
    //구,군 리스트
    List<Gugun> getGugunList(String code);
    //동 리스트
    List<Dong> getDongList(String code);
}
