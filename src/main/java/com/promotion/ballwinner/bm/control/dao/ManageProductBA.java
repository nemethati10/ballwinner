package com.promotion.ballwinner.bm.control.dao;

import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.repository.ProductRepository;
import com.promotion.ballwinner.bm.entity.CompanyBE;
import com.promotion.ballwinner.bm.entity.ProductBE;
import com.promotion.ballwinner.bm.util.CouponUtil;

/**
 * Business activity for managing {@link ProductBE}
 */
@Service
public class ManageProductBA {

    private ProductRepository productRepository;

    public ManageProductBA(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductBE create(final String name, final CompanyBE company) {
        final ProductBE product = new ProductBE();
        product.setProductName(name);
        product.setCouponCode(CouponUtil.generateCouponCode());
        product.setCompany(company);

        return productRepository.save(product);
    }

    public ProductBE findByName(final String name) {
        return productRepository.findByProductName(name);
    }

    public void remove(final ProductBE product) {
        productRepository.delete(product);
    }

}
