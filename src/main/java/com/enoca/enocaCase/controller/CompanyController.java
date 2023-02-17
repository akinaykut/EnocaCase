package com.enoca.enocaCase.controller;

import com.enoca.enocaCase.dto.request.UpdateCompanyRequestDto;
import com.enoca.enocaCase.entities.Company;
import com.enoca.enocaCase.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;


    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Company> saveCompany(@RequestBody Company company){
       return new ResponseEntity<>(companyService.saveCompany(company), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Company> updateCompanyById(@RequestParam("companyId") Long companyId, @RequestBody UpdateCompanyRequestDto updateCompanyRequestDto){
       return new ResponseEntity<>(companyService.updateCompanyById(companyId, updateCompanyRequestDto), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteCompanyById(@RequestParam("companyId") Long companyId){
        return new ResponseEntity<>(companyService.deleteCompanyById(companyId), HttpStatus.OK);
    }

}
