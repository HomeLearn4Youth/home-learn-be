package com.homelearn.ddubeok2.house;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apart")
@RequiredArgsConstructor
public class ApartController {
    private final ApartService apartService;

    @GetMapping("/all")
    public ResponseEntity<List<Apart>> getTotalApart(){
        return ResponseEntity.ok().body(apartService.getTotalApart());
    }

    @GetMapping("/search_name")
    public ResponseEntity<List<Apart>> getApartListByName(@RequestParam("name") String name){
        return ResponseEntity.ok().body(apartService.getApartListByName(name));
    }

    @GetMapping("/search_dong")
    public ResponseEntity<List<Apart>> getApartListByDongName(@RequestParam("name") String name){
        return ResponseEntity.ok().body(apartService.getApartListByDongName(name));
    }

    @GetMapping("/dong")
    public ResponseEntity<List<Apart>> getApartListByDongCode(@RequestParam("code") String code){
        return ResponseEntity.ok().body(apartService.getApartListByDongCode(code));
    }

    @GetMapping("/detail")
    public ResponseEntity<Apart> getApartById(@RequestParam("id") String id){
        return ResponseEntity.ok().body(apartService.getApartById(id));
    }

}
