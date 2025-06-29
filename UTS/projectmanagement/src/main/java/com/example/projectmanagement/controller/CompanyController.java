package com.example.projectmanagement.controller;

import com.example.projectmanagement.model.Company;
import com.example.projectmanagement.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    // Get all 
    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Get company by ID
    @GetMapping("/by-id/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    // Create new company
    @PostMapping("/create")
    public Company createCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    // Update company
    @PutMapping("/update/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            company.setName(companyDetails.getName());
            company.setAddress(companyDetails.getAddress());
            return companyRepository.save(company);
        }
        return null;
    }

    // Delete company
    @DeleteMapping("/delete/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyRepository.deleteById(id);
    }

    //  Search companies by name
    @GetMapping("/search")
    public ResponseEntity<List<Company>> searchCompanies(@RequestParam String name) {
        List<Company> companies = companyRepository.findByNameContainingIgnoreCase(name);
        if (companies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(companies);
    }

    //  Sort companies by name (asc or desc)
    @GetMapping("/sort")
    public ResponseEntity<List<Company>> sortCompanies(@RequestParam(defaultValue = "asc") String direction) {
        List<Company> companies;
        if ("desc".equalsIgnoreCase(direction)) {
            companies = companyRepository.findAllOrderByNameDesc();
        } else {
            companies = companyRepository.findAllOrderByNameAsc();
        }
        return ResponseEntity.ok(companies);
    }
}
