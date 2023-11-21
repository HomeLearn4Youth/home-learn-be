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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/find/{dongCode}")
    public ResponseEntity<MessageUtil<List<LApartListOutputSpec>>> findAptList(
            @ModelAttribute LApartListInputSpec inputSpec,
            @PathVariable("dongCode") String dongCode
            ){
        return null;
    }
    @GetMapping("/find/{aptCode}")
    public ResponseEntity<MessageUtil<List<ReportFindOutputSpec>>> getApartLeaseList(
            @ModelAttribute ReportFindInputSpec inputSpec,
            @PathVariable("apartCode") int apartCode
    ){
        return null;
    }
    @GetMapping("/avg/{dongCode}")
    public ResponseEntity<MessageUtil<List<PerLeaseDeal>>> getLeaseDealPercent(
            @PathVariable("apartCode") Long apartCode
    ){
        return null;
    }

    @GetMapping("/per/{dongCode}")
    public ResponseEntity<MessageUtil<List<AvgSquareMeter>>> getLeaseAvg(
            @ModelAttribute DealListInputSpec inputSpec
            ){
        System.out.println(inputSpec.toString());
        return null;

    }
}
