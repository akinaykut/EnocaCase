package com.enoca.enocaCase.services;

import com.enoca.enocaCase.dto.request.UpdateCompanyRequestDto;
import com.enoca.enocaCase.entities.Company;
import com.enoca.enocaCase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;



    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }


    public Company saveCompany(@RequestBody Company company){
        companyRepository.save(company);
        return company;
    }


    public boolean deleteCompanyById(@RequestParam("companyId") Long companyId){
        companyRepository.deleteById(companyId);
        return true;
    }

    public Company updateCompanyById(Long companyId, UpdateCompanyRequestDto updateCompanyRequestDto) {
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isPresent()){
            Company foundCompany = company.get();
            foundCompany.setName(updateCompanyRequestDto.getName());
            foundCompany.setFounder(updateCompanyRequestDto.getFounder());
            companyRepository.save(foundCompany);
            return foundCompany;
        }else {
            return null;
        }

    }

    public Company findCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
}
