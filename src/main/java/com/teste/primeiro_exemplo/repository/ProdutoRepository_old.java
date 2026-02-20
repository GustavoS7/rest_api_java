package com.teste.primeiro_exemplo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.teste.primeiro_exemplo.model.Produto;
import com.teste.primeiro_exemplo.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository_old {
  private List<Produto> produtos = new ArrayList<Produto>();
  private Integer ultimoId = 0;

  /**
   * Método para retornar uma lista de produtos
   * @return lista de produtos.
   */
  public List<Produto> obterTodos() {
    return produtos;
  }

  /**
   * Método para retornar produto pelo id
   * @param id id do produto a ser localizado
   * @return retorna um produto caso seja encontrado
   */
  public Optional<Produto> obterPorId(Integer id) {
    return produtos
      .stream()
      .filter(produto -> produto.getId() == id)
      .findFirst();
  }

  /**
   * Método para adicionar um produto
   * @param produto produto a ser adicionado
   * @return retorna o produto adicionado na lista
   */
  public Produto adicionar(Produto produto) {
    ultimoId++;
    produto.setId(ultimoId);
    produtos.add(produto);
    return produto;
  }

  /**
   * Método para deletar produto por id
   * @param id id do produto a ser deletado
   */
  public void deletar(Integer id) {
    produtos.removeIf(produto -> produto.getId() == id);
  }

  /**
   * Método para atualizar produto
   * @param produto produto a ser atualizado
   * @return retorna o produto atualizado
   */
  public Produto atualizar(Produto produto) {
    Optional<Produto> produtoEncontrato = obterPorId(produto.getId());
    if (produtoEncontrato.isEmpty()) {
      throw new ResourceNotFoundException("Produto não encontrado");
    }
    deletar(produto.getId());
    produtos.add(produto);
    return produto;
  }
}
