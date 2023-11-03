package com.homelearn.ddubeok2.house;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search_name/{name}")
    public ResponseEntity<List<Apart>> getApartListByName(@PathVariable("name") String name){
        return ResponseEntity.ok().body(apartService.getApartListByName(name));
    }

    @GetMapping("/search_dong/{name}")
    public ResponseEntity<List<Apart>> getApartListByDongName(@PathVariable("name") String name){
        return ResponseEntity.ok().body(apartService.getApartListByDongName(name));
    }

    @GetMapping("/dong/{code}")
    public ResponseEntity<List<Apart>> getApartListByDongCode(@PathVariable("code") String code){
        return ResponseEntity.ok().body(apartService.getApartListByDongCode(code));
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Apart> getApartById(@PathVariable("id") String id){
        return ResponseEntity.ok().body(apartService.getApartById(id));
    }

}
