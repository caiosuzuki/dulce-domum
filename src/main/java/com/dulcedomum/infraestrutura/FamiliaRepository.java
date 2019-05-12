package com.dulcedomum.infraestrutura;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepository extends CrudRepository<Familia, String> {

    @Query("from Familia familia where familia.familiaId = :familiaId")
    Familia obterPorFamiliaId(@Param("familiaId") String familiaId);
}
