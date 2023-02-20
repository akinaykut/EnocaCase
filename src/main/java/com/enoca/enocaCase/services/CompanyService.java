package com.enoca.enocaCase.services;

import com.enoca.enocaCase.dto.request.SaveCompanyRequestDto;
import com.enoca.enocaCase.dto.request.UpdateCompanyRequestDto;
import com.enoca.enocaCase.dto.response.CompanyResponseDto;
import com.enoca.enocaCase.entities.Company;
import com.enoca.enocaCase.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ModelMapper modelMapper;


    public List<CompanyResponseDto> getCompanies(){
        List<Company> companies = companyRepository.findAll();
        List<CompanyResponseDto> companyResponseDtos = companies.stream().map(company -> modelMapper.map(company, CompanyResponseDto.class)).collect(Collectors.toList());
        return companyResponseDtos;
    }


    public CompanyResponseDto saveCompany(@RequestBody SaveCompanyRequestDto saveCompanyRequestDto){
        Company company = modelMapper.map(saveCompanyRequestDto, Company.class);
        companyRepository.save(company);
        return modelMapper.map(saveCompanyRequestDto, CompanyResponseDto.class);
    }


    public boolean deleteCompanyById(@RequestParam("companyId") Long companyId){
        companyRepository.deleteById(companyId);
        return true;
    }

    public CompanyResponseDto updateCompanyById(Long companyId, UpdateCompanyRequestDto updateCompanyRequestDto) {
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isPresent()){
            Company foundCompany = company.get();
            foundCompany.setName(updateCompanyRequestDto.getName());
            foundCompany.setFounder(updateCompanyRequestDto.getFounder());
            companyRepository.save(foundCompany);
            return modelMapper.map(foundCompany, CompanyResponseDto.class);
        }else {
            return null;
        }

    }

    public Company findCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
}
