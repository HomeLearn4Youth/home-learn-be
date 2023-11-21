package com.homelearn.back.report;


import com.homelearn.back.report.dto.LApartListOutputSpec;
import com.homelearn.back.report.dto.ReportFindOutputSpec;
import com.homelearn.back.report.entity.AvgSquareMeter;
import com.homelearn.back.report.entity.PerLeaseDeal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportMapper reportMapper;
    @Override
    public List<LApartListOutputSpec> findApartList(String dongCode) {
        return reportMapper.findApartList(dongCode)
                .stream()
                .map(m-> new LApartListOutputSpec()
                        .rentHouseInfoToLApartListOutputSpec(m))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportFindOutputSpec> getApartLeaseList(int apartCode) {
        return reportMapper.getApartLeaseList(apartCode)
                .stream()
                .map(m-> new ReportFindOutputSpec()
                        .reportFindInputSpecToReportFindParam(m))
                .collect(Collectors.toList());
    }

    @Override
    public List<PerLeaseDeal> getLeaseDealPercent(String dongCode) {
        return reportMapper.getLeaseDealPercent(dongCode);
    }

    @Override
    public List<AvgSquareMeter> getLeaseAvg(String dongCode) {
        return reportMapper.getLeaseAvg(dongCode);
    }

}
