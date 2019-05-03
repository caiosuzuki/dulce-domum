package com.dulcedomum.infraestrutura;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepository extends CrudRepository<Familia, String> {
}
