package com.example.desafio.rest.demo.controller;

import com.example.desafio.rest.demo.model.Produto;
import com.example.desafio.rest.demo.service.IProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    private final IProdutoService produtoService;

    public ProdutoController(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @ApiOperation("Recupera o produto por id.")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @ApiOperation("Recupera toda a lista.")
    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @ApiOperation("Recupera uma lista de produtos mediante filtros definidos.")
    @GetMapping("/search")
    public ResponseEntity<List<Produto>> search(@RequestParam(value = "q", required = false) String q,
                                                @RequestParam(value = "min_price", required = false) Double min_price,
                                                @RequestParam(value = "max_price", required = false) Double max_price) {
        return ResponseEntity.ok(produtoService.search(q, min_price, max_price));
    }

    @ApiOperation("Salva um produto.")
    @PostMapping
    public ResponseEntity<Produto> save(@Valid @RequestBody Produto produto, BindingResult result) {
        return ResponseEntity.ok(produtoService.save(produto, result));
    }

    @ApiOperation("Atualizar um produto por id.")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") Long id,
                                          @Valid @RequestBody Produto produto, BindingResult result) {
        return ResponseEntity.ok(produtoService.update(id, produto,result));
    }

    @ApiOperation("Remover um produto por id.")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
