package com.dulcedomum.infraestrutura.familia;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamiliaRepository extends CrudRepository<Familia, String> {

    @Query("from Familia familia where familia.familiaId = :familiaId")
    Familia obterPorFamiliaId(@Param("familiaId") String familiaId);

    @Query("from Familia familia where familia.familiaId in :idsDasFamilias")
    List<Familia> obterPorIdsDasFamilias(@Param("idsDasFamilias") List<String> idsDasFamilias);
}
