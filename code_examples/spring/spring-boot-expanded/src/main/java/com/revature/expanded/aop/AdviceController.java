package com.revature.expanded.aop;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
@AllArgsConstructor
public class AdviceController {

    private final AdviceService adviceService;

    @GetMapping("process/{data}")
    public ResponseEntity<String> processData(@PathVariable Double data) {
        return ResponseEntity.ok( adviceService.processData(data) );
    }
}
