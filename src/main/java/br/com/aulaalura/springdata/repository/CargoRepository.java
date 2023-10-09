package br.com.aulaalura.springdata.repository;

import br.com.aulaalura.springdata.orm.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}