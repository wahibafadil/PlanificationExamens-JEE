package com.teste.monteste.dao;
import com.teste.monteste.bo.ElementPedagogique;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IModuleRepository extends JpaRepository<ElementPedagogique,Long> {
    @Query("SELECT e FROM ElementPedagogique e JOIN e.niveaux n JOIN e.filieres f WHERE f.id = :filiereId AND n.id = :niveauId")
    List<ElementPedagogique> findModulesByFiliereAndNiveau(@Param("filiereId") Long filiereId, @Param("niveauId") Long niveauId);
}
