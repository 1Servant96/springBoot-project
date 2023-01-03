package com.peaksoft.springboot.entities.company;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getAllCompanies")
    public String getCompanies(Model model) {
        List<Company> companies = companyService.getCompanies();
        model.addAttribute("companies", companies);
        return "/companies/companies";
    }
    @GetMapping("/newCompany")
    public String newCompany(Model model) {
        model.addAttribute("company", new Company());
        return "/companies/newCompany";
    }

    @PostMapping("/saveCompany")
    public String create(@ModelAttribute("company") @Valid Company company, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/companies/newCompany";
        }
        companyService.saveCompany(company);
        return "redirect:/getAllCompanies";
    }

    @GetMapping("editCompany")
    public String edit(@RequestParam("companyId") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "/companies/editCompany";
    }

    @PostMapping("/editCompany")
    public String updateCompany(@ModelAttribute("company") @Valid Company company, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "/companies/editCompany";
        }
        companyService.updateCompany(company);
        return "redirect:/getAllCompanies";
    }

    @RequestMapping("/deleteCompany")
    public String delete(@RequestParam ("companyId") Long id) {
        companyService.deleteCompanyById(id);
        return "redirect:/getAllCompanies";
    }
}
