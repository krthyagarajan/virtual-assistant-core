package com.trainings.virtual_assistant.cricket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TeamController {


    @GetMapping("/teams")
    public ResponseEntity<String> login(@RequestBody String userId) {


        return null;

    }




}
