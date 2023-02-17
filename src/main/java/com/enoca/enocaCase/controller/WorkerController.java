package com.enoca.enocaCase.controller;

import com.enoca.enocaCase.dto.request.SaveWorkerRequestDto;
import com.enoca.enocaCase.dto.request.UpdateWorkerRequestDto;
import com.enoca.enocaCase.entities.Worker;
import com.enoca.enocaCase.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;


    @GetMapping
    public ResponseEntity<List<Worker>> getWorkersList(){
        return new ResponseEntity<>(workerService.getWorkersList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Worker> saveWorker(@RequestBody SaveWorkerRequestDto saveWorkerRequestDto){
        return new ResponseEntity<>(workerService.saveWorker(saveWorkerRequestDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Worker> updateWorkerById(@RequestParam("workerId") Long workerId, @RequestBody UpdateWorkerRequestDto updateWorkerRequestDto){
        return new ResponseEntity<>(workerService.updateWorkerById(workerId, updateWorkerRequestDto), HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteWorkerById(@RequestParam("workerId") Long workerId){
        return new ResponseEntity<>(workerService.deleteWorkerById(workerId), HttpStatus.OK);
    }
}
