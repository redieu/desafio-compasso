package com.example.desafio.rest.demo.repository;

import com.example.desafio.rest.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

}
