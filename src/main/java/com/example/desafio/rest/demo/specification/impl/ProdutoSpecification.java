package com.example.desafio.rest.demo.specification.impl;

import com.example.desafio.rest.demo.model.Produto;
import com.example.desafio.rest.demo.model.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProdutoSpecification implements Specification<Produto> {

    private SearchCriteria filter;

    public ProdutoSpecification(SearchCriteria filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Produto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.disjunction();

        if (filter.getName() != null) {
            p.getExpressions().add(
                    criteriaBuilder.or(criteriaBuilder.equal(root.get("name"), filter.getName()),
                            criteriaBuilder.equal(root.get("description"), filter.getName())));
        }

        if (filter.getMinPrice() != null) {
            p.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
        }

        if (filter.getMaxPrice() != null) {
            p.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
        }

        return p;
    }
}
