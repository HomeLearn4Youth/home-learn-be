package com.homelearn.back.report;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.house.dto.*;
import com.homelearn.back.report.dto.LApartListInputSpec;
import com.homelearn.back.report.dto.LApartListOutputSpec;
import com.homelearn.back.report.dto.ReportFindInputSpec;
import com.homelearn.back.report.dto.ReportFindOutputSpec;
import com.homelearn.back.report.entity.AvgSquareMeter;
import com.homelearn.back.report.entity.PerLeaseDeal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    /**
     * 아파트 리스트 조회
     * @param dongCode
     * @return
     */
    @GetMapping("/apartList/{dongCode}")
    public ResponseEntity<MessageUtil<List<LApartListOutputSpec>>> findAptList(
            @PathVariable("dongCode") String dongCode
            ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        reportService.findApartList(dongCode)));
    }

    /**
     * 전세 매물 상세조회
     * @param aptCode
     * @return
     */
    @GetMapping("/apartInfo/{aptCode}")
    public ResponseEntity<MessageUtil<List<ReportFindOutputSpec>>> getApartLeaseList(
        @PathVariable("aptCode") int aptCode
    ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        reportService.getApartLeaseList(aptCode)));
    }
    @GetMapping("/per/{dongCode}")
    public ResponseEntity<MessageUtil<List<PerLeaseDeal>>> getLeaseDealPercent(
            @PathVariable("dongCode") String dongCode
    ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        reportService.getLeaseDealPercent(dongCode)));
    }


    @GetMapping("/avg/{dongCode}")
    public ResponseEntity<MessageUtil<List<AvgSquareMeter>>> getLeaseAvg(
            @PathVariable("dongCode") String dongCode
            ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        reportService.getLeaseAvg(dongCode))
        );

    }
}
