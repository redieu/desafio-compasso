package com.example.desafio.rest.demo.service.impl;

import com.example.desafio.rest.demo.config.exceptions.ElementNotFoundException;
import com.example.desafio.rest.demo.config.exceptions.ValidationException;
import com.example.desafio.rest.demo.model.Produto;
import com.example.desafio.rest.demo.model.criteria.SearchCriteria;
import com.example.desafio.rest.demo.repository.IProdutoRepository;
import com.example.desafio.rest.demo.service.IProdutoService;
import com.example.desafio.rest.demo.specification.impl.ProdutoSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService {

    @Autowired
    private IProdutoRepository produtoRepository;

    @Override
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public List<Produto> search(String q, Double min_price, Double max_price) {

        SearchCriteria filter = new SearchCriteria();
        filter.setName(q);
        filter.setMinPrice(min_price);
        filter.setMaxPrice(max_price);

        Specification<Produto> spec = new ProdutoSpecification(filter);
        return produtoRepository.findAll(spec);
    }

    @Override
    public Produto save(Produto produto, BindingResult bindingResult) {
        if (!bindingResult.getAllErrors().isEmpty())
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Erro de validação.");

        return produtoRepository.save(produto);
    }

    @Override
    public Produto update(Long id, Produto produto, BindingResult bindingResult) {

        if (!bindingResult.getAllErrors().isEmpty())
            throw new ValidationException(Response.SC_BAD_REQUEST, "Erro de validação.");

        Optional<Produto> productById = this.findById(id);

        productById.ifPresent(value -> {
            produto.setId(productById.get().getId());
            produtoRepository.save(produto);

        });

        productById.orElseThrow( () -> new ElementNotFoundException("Produto não encontrado."));

        return produto;
    }

    @Override
    public void delete(Long id) {
        Optional<Produto> produto = this.findById(id);

        produto.ifPresent(value -> produtoRepository.delete(value));

        produto.orElseThrow( () -> new ElementNotFoundException("Produto não encontrado."));

    }
}
