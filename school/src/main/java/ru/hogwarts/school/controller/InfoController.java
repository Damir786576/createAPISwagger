package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.services.impl.InfoServiceImpl;
import ru.hogwarts.school.services.services.InfoService;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoServiceImpl infoServiceImpl;

    public InfoController(InfoService infoService) {
        this.infoServiceImpl = infoService;
    }

    @GetMapping("/port")
    public ResponseEntity<String> getPort() {
        return ResponseEntity.ok(infoServiceImpl.getPort());
    }
}
