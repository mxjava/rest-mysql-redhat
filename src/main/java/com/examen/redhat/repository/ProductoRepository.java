package com.examen.redhat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.redhat.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
