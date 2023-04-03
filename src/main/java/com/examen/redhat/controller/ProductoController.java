package com.examen.redhat.controller;

import com.examen.redhat.exception.ResourceNotFoundException;
import com.examen.redhat.model.Producto;
import com.examen.redhat.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    ProductoRepository productoRepository;

    @GetMapping("/productos")
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @PostMapping("/productos")
    public Producto saveProducto(@Valid @RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping("/productos/{id}")
    public Producto getProductoById(@PathVariable(value = "id") Long productoId) {
        return productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productoId));
    }

    @PutMapping("/productos/{id}")
    public Producto updateProducto(@PathVariable(value = "id") Long productoId,
                                           @Valid @RequestBody Producto productoDetails) {

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productoId));

        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
      
        Producto updatedProducto = productoRepository.save(producto);
        return updatedProducto;
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable(value = "id") Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productoId));

        productoRepository.delete(producto);

        return ResponseEntity.ok().build();
    }
}
