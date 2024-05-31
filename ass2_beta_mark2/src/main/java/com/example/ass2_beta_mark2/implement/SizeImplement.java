package com.example.ass2_beta_mark2.implement;

import com.example.ass2_beta_mark2.entity.model.Size;
import com.example.ass2_beta_mark2.respository.SizeRepository;
import com.example.ass2_beta_mark2.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SizeImplement implements SizeService {
    @Autowired
    SizeRepository qls;

    @Override
    public Size save(Size entity) {
        return qls.save(entity);
    }

    @Override
    public <S extends Size> Iterable<S> saveAll(Iterable<S> entities) {
        return qls.saveAll(entities);
    }

    @Override
    public Optional<Size> findById(Integer integer) {
        return qls.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return qls.existsById(integer);
    }

    @Override
    public ArrayList<Size> findAll() {
        return (ArrayList<Size>) qls.findAll();
    }

    @Override
    public ArrayList<Size> findAllById(ArrayList<Integer> integers) {
        return (ArrayList<Size>) qls.findAllById(integers);
    }

    @Override
    public long count() {
        return qls.count();
    }

    @Override
    public void deleteById(Integer integer) {
        qls.deleteById(integer);
    }

    @Override
    public void delete(Size entity) {
        qls.delete(entity);
    }

    @Override
    public void deleteAllById(ArrayList<Integer> integers) {
        qls.deleteAllById(integers);
    }

    @Override
    public void deleteAll(ArrayList<Size> entities) {
        qls.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        qls.deleteAll();
    }
}
