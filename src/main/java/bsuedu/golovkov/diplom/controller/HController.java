package bsuedu.golovkov.diplom.controller;

import bsuedu.golovkov.diplom.service.HService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/h")
public class HController {

    private final HService hService;

    @GetMapping("/h")
    public String getH() {
        return hService.getH();
    }
}
