package com.gas.vesubio.DAO;

import com.gas.vesubio.Model.Inf_Valores;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class Inf_ValoresDAO implements CrudRepository<Inf_Valores, Long> {

    @Override
    public <S extends Inf_Valores> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Inf_Valores> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Inf_Valores> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Inf_Valores> findAll() {
        return null;
    }

    @Override
    public Iterable<Inf_Valores> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Inf_Valores entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Inf_Valores> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
