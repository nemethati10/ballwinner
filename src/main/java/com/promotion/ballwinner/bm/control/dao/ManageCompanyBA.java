package com.promotion.ballwinner.bm.control.dao;

import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.repository.CompanyRepository;
import com.promotion.ballwinner.bm.entity.CompanyBE;

/**
 * Business activity for managing {@link CompanyBE}
 */
@Service
public class ManageCompanyBA {

    private CompanyRepository companyRepository;

    public ManageCompanyBA(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyBE create(final String name) {
        final CompanyBE company = new CompanyBE();
        company.setCompanyName(name);

        return companyRepository.save(company);
    }

}
