package com.homelearn.back.report;

import com.homelearn.back.report.entity.AvgSquareMeter;
import com.homelearn.back.report.entity.ReportHouseLease;
import com.homelearn.back.report.entity.PerLeaseDeal;
import com.homelearn.back.report.entity.RentHouseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    //전세 아파트 리스트 조회
    List<RentHouseInfo> findApartList(String dongCode);
    //전세 아파트 거래 내역 및 상세 조회
    List<ReportHouseLease> getApartLeaseList(int ApartCode);
    //지역별 월별 매매가격대비 전세가격비율
    List<PerLeaseDeal> getLeaseDealPercent(String dongCode);
    //지역별 월별 ㎡당 전세평균가격
    List<AvgSquareMeter> getLeaseAvg(String dongCode);

}
