
package com.inventario.computo.service;

import com.inventario.computo.entity.Categoria;
import com.inventario.computo.entity.Producto;
import com.inventario.computo.repository.CategoriaRepository;
import com.inventario.computo.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository,
                           CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Consultar todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Consultar un producto por ID
    public Producto obtenerProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Registrar un producto
    public Producto guardarProducto(Producto producto) {

        Long categoriaId = producto.getCategoria().getId();

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() ->
                        new RuntimeException("Categoría no encontrada con ID: " + categoriaId));

        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    // Actualizar un producto
    public Producto actualizarProducto(Long id, Producto datos) {

        Producto producto = obtenerProducto(id);

        Long categoriaId = datos.getCategoria().getId();

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() ->
                        new RuntimeException("Categoría no encontrada con ID: " + categoriaId));

        producto.setNombre(datos.getNombre());
        producto.setMarca(datos.getMarca());
        producto.setModelo(datos.getModelo());
        producto.setPrecio(datos.getPrecio());
        producto.setStock(datos.getStock());
        producto.setDescripcion(datos.getDescripcion());
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    // Eliminar un producto
    public void eliminarProducto(Long id) {
        Producto producto = obtenerProducto(id);
        productoRepository.delete(producto);
    }
}
