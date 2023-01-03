package com.peaksoft.springboot.entities.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;

    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id){
        return companyRepository.getById(id);
    }
    public void saveCompany(Company company){
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Company company) {
    companyRepository.save(company);
    }

    public void deleteCompanyById(Long id){
        companyRepository.deleteById(id);
    }



}
