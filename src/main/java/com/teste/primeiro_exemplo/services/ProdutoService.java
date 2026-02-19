package com.teste.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiro_exemplo.model.Produto;
import com.teste.primeiro_exemplo.repository.ProdutoRepository;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  /**
   * Método para retornar uma lista de produtos
   * @return lista de produtos.
   */
  public List<Produto> obterTodos() {
    return produtoRepository.obterTodos();
  }

  /**
   * Método para retornar produto pelo id
   * @param id id do produto a ser localizado
   * @return retorna um produto caso seja encontrado
   */
  public Optional<Produto> obterPorId(Integer id) {
    return produtoRepository.obterPorId(id);
  }

  /**
   * Método para adicionar um produto
   * @param produto produto a ser adicionado
   * @return retorna o produto adicionado na lista
   */
  public Produto adicionar(Produto produto) {
    return produtoRepository.adicionar(produto);
  }

  /**
   * Método para deletar produto por id
   * @param id id do produto a ser deletado
   */
  public void deletar(Integer id) {
    produtoRepository.deletar(id);
  }

  /**
   * Método para atualizar produto
   * @param produto produto a ser atualizado
   * @param id id do produto a ser atualizado
   * @return retorna o produto atualizado
   */
  public Produto atualizar(Integer id, Produto produto) {
    produto.setId(id);
    return produtoRepository.atualizar(produto);
  }
}
