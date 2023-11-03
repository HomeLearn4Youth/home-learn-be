package com.homelearn.ddubeok2.dong;

import com.homelearn.ddubeok2.dong.dto.Dong;
import com.homelearn.ddubeok2.dong.dto.Gugun;
import com.homelearn.ddubeok2.dong.dto.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DongMapper {
    //시도 리스트
    List<Sido> getSidoList();
    //구,군 리스트
    List<Gugun> getGugunList(String code);
    //동 리스트
    List<Dong> getDongList(String code);
}
