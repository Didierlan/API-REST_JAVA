package com.example.apirest.apirest.controller;

import com.example.apirest.apirest.entity.Producto;
import com.example.apirest.apirest.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/listar/todos")
    private List<Producto> getAllProduct(){
        return productoRepository.findAll();
    }

    @GetMapping("/listar/{id}")
    private Producto getOneProduct(@PathVariable Long id){
        return productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró el producto con el ID " + id)
        );

    }


    @PostMapping("/crear")
    private Producto saveProduct(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/actualizar/{id}")
    private Producto updatProduct(@PathVariable Long id, @RequestBody Producto producto ){
        Producto newProduct = productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró el producto con el ID " + id)
        );

        newProduct.setNombre(producto.getNombre());
        newProduct.setPrecio(producto.getPrecio());
        return productoRepository.save(newProduct);

    }

    @DeleteMapping("/eliminar/{id}")
    private String deleteProduct(@PathVariable Long id){
        Producto producto = productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró el producto con el ID " + id)
        );

        productoRepository.delete(producto);
        return "El producto ( " + id +", "+ producto.getNombre() + "," +
                producto.getPrecio() + ") se ha eliminado";
    }



}
