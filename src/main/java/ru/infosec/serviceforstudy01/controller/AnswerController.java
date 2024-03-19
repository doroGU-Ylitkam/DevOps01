package ru.infosec.serviceforstudy01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.infosec.serviceforstudy01.service.AnswerService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @GetMapping("${app.url}")
    public String getAnswer(){
        return answerService.getAnswer();
    }
}
