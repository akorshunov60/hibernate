package com.example.orm.model.repository;

import com.example.orm.model.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    List<Product> findAllSortedByName();

    Product findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(Product product);
}
