
package com.inventario.computo.service;

import com.inventario.computo.entity.Categoria;
import com.inventario.computo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Consultar todas las categorías
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Consultar una categoría por ID
    public Categoria obtenerCategoria(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    // Registrar una categoría
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Actualizar una categoría
    public Categoria actualizarCategoria(Long id, Categoria datos) {
        Categoria categoria = obtenerCategoria(id);

        categoria.setNombre(datos.getNombre());
        categoria.setDescripcion(datos.getDescripcion());

        return categoriaRepository.save(categoria);
    }

    // Eliminar una categoría
    public void eliminarCategoria(Long id) {
        Categoria categoria = obtenerCategoria(id);
        categoriaRepository.delete(categoria);
    }
}
