package com.peaksoft.springboot.entities.company;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanies();
    Company getCompanyById(Long id);
    void saveCompany(Company company);
    void updateCompany(Company company);
    void deleteCompanyById(Long id);
}
