package com.example.desafio.rest.demo.service;

import com.example.desafio.rest.demo.model.Produto;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface IProdutoService {

    Optional<Produto> findById(Long id);

    List<Produto> findAll();

    List<Produto> search(String q, Double min_price, Double max_price);

    Produto save(Produto produto, BindingResult bindingResult);

    Produto update(Long id, Produto produto, BindingResult bindingResult);

    void delete(Long id);
}
