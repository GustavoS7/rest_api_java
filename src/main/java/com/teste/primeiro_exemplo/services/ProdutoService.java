package com.teste.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiro_exemplo.model.Produto;
import com.teste.primeiro_exemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiro_exemplo.repository.ProdutoRepository;
import com.teste.primeiro_exemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  /**
   * Método para retornar uma lista de produtos
   * @return lista de produtos.
   */
  public List<ProdutoDTO> obterTodos() {
    List<Produto> produtos = produtoRepository.findAll();
    return produtos.stream()
      .map(el -> new ModelMapper().map(el, ProdutoDTO.class))
      .collect(Collectors.toList());
  }

  /**
   * Método para retornar produto pelo id
   * @param id id do produto a ser localizado
   * @return retorna um produto caso seja encontrado
   */
  public Optional<ProdutoDTO> obterPorId(Integer id) {
    Optional<Produto> produto = produtoRepository.findById(id);
    if (produto.isEmpty())
      throw new ResourceNotFoundException("Produto " + id + " não encontrado");
    ProdutoDTO dto = new ModelMapper()
      .map(produto.get(), ProdutoDTO.class);
    return Optional.of(dto);
  }

  /**
   * Método para adicionar um produto
   * @param produto produto a ser adicionado
   * @return retorna o produto adicionado na lista
   */
  public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
    produtoDto.setId(null);
    ModelMapper mapper = new ModelMapper();
    Produto produto = mapper.map(produtoDto, Produto.class);
    produto = produtoRepository.save(produto);
    produtoDto.setId(produto.getId());
    return produtoDto;
  }

  /**
   * Método para deletar produto por id
   * @param id id do produto a ser deletado
   */
  public void deletar(Integer id) {
    Optional<Produto> produto = produtoRepository.findById(id);
    if (produto.isEmpty())
      throw new ResourceNotFoundException("Produto " + id + " não encontrado");
    produtoRepository.deleteById(id);
  }

  /**
   * Método para atualizar produto
   * @param produto produto a ser atualizado
   * @param id id do produto a ser atualizado
   * @return retorna o produto atualizado
   */
  public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
    produtoDto.setId(id);
    ModelMapper mapper = new ModelMapper();
    Produto produto = mapper.map(produtoDto, Produto.class);
    produto = produtoRepository.save(produto);
    return produtoDto;
  }
}
