package ru.seredavin.dbpersister.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ru.seredavin.dbpersister.dto.GitReposDTO;
import ru.seredavin.dbpersister.service.GitReposService;
import ru.seredavin.dbpersister.service.impl.EmitterService;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
