package ru.seredavin.dbpersister.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ru.seredavin.dbpersister.dto.GitReposDTO;
import ru.seredavin.dbpersister.service.GitReposService;
import ru.seredavin.dbpersister.service.impl.EmitterService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class GitReposController {
    private final GitReposService gitReposService;
    private final EmitterService emitterService;



    @GetMapping("/all")
    private ResponseEntity<List<GitReposDTO>> findAll() {
        return new ResponseEntity(gitReposService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/emitter")
    public SseEmitter subsribe() {
        log.info("subscribing...");

        SseEmitter sseEmitter = new SseEmitter(24 * 60 * 60 * 1000l);
        emitterService.addEmitter(sseEmitter);

        log.info("subscribed");
        return sseEmitter;
    }

}
