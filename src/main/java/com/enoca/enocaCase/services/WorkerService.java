package com.enoca.enocaCase.services;

import com.enoca.enocaCase.dto.request.SaveWorkerRequestDto;
import com.enoca.enocaCase.dto.request.UpdateWorkerRequestDto;
import com.enoca.enocaCase.entities.Company;
import com.enoca.enocaCase.entities.Worker;
import com.enoca.enocaCase.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    CompanyService companyService;

    public List<Worker> getWorkersList(){
        return workerRepository.findAll();
    }


    public Worker saveWorker(SaveWorkerRequestDto saveWorkerRequestDto){
        Company company = companyService.findCompanyById(saveWorkerRequestDto.getCompanyId());

        if (company == null){
            return null;
        }
        Worker worker = new Worker();
        worker.setName(saveWorkerRequestDto.getName());
        worker.setCompany(company);
        workerRepository.save(worker);
        return worker;
    }


    public Worker updateWorkerById(Long workerId, UpdateWorkerRequestDto updateWorkerRequestDto){

            Optional<Worker> worker = workerRepository.findById(workerId);
            if(worker.isPresent()){
                Worker foundWorker = worker.get();
                foundWorker.setName(updateWorkerRequestDto.getName());
//                foundWorker.setFounder(workerUpdateRequestDto.getCompanyId());
                workerRepository.save(foundWorker);
                return foundWorker;
            }else {
                return null;
            }
    }

    public boolean deleteWorkerById(Long workerId){
        workerRepository.deleteById(workerId);
        return true;
    }
}

