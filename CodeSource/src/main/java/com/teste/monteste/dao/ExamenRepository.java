package com.teste.monteste.dao;

import com.teste.monteste.bo.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
}
