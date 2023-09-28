package com.example.AlmanUsuluProjesi.controller;

import com.example.AlmanUsuluProjesi.service.DovizService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doviz")
@CrossOrigin(origins = "http://localhost:4200")
public class DovizController {

    private final DovizService dovizService;

    public DovizController(DovizService dovizService) {
        this.dovizService = dovizService;
    }

    @GetMapping("/getDovizBuying/{dovizType}")
    public float getDovizBuying(@PathVariable String dovizType) {
        return dovizService.getDovizBuying(dovizType);
    }

    @GetMapping("/getDovizSelling/{dovizType}")
    public float getDovizSelling(@PathVariable String dovizType) {
        return dovizService.getDovizSelling(dovizType);
    }
}
