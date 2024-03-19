package ru.infosec.serviceforstudy01.service.impl;

import org.springframework.stereotype.Service;
import ru.infosec.serviceforstudy01.modeldto.Answer;
import ru.infosec.serviceforstudy01.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

    /**
     * Возвращает ответ
     * @return ответ
     */
    @Override
    public String getAnswer() {
        Answer answer = new Answer();
        answer.setStatus("Hello world!");
        return "{\"status\":\"" + answer.getStatus() + "\"}";
    }
}
