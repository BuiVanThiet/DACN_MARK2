package com.example.ass2_beta_mark2.implement;

import com.example.ass2_beta_mark2.entity.model.MauSac;
import com.example.ass2_beta_mark2.respository.MauSacRepository;
import com.example.ass2_beta_mark2.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MauSacImplement implements MauSacService {
    @Autowired
    MauSacRepository qlm;

    @Override
    public MauSac save(MauSac entity) {
        return qlm.save(entity);
    }

    @Override
    public ArrayList<MauSac> saveAll(ArrayList<MauSac> entities) {
        return (ArrayList<MauSac>) qlm.saveAll(entities);
    }

    @Override
    public Optional<MauSac> findById(Integer integer) {
        return qlm.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return qlm.existsById(integer);
    }

    @Override
    public ArrayList<MauSac> findAll() {
        return (ArrayList<MauSac>) qlm.findAll();
    }

    @Override
    public ArrayList<MauSac> findAllById(ArrayList<Integer> integers) {
        return (ArrayList<MauSac>) qlm.findAllById(integers);
    }

    @Override
    public long count() {
        return qlm.count();
    }

    @Override
    public void deleteById(Integer integer) {
        qlm.deleteById(integer);
    }

    @Override
    public void delete(MauSac entity) {
        qlm.delete(entity);
    }

    @Override
    public void deleteAllById(ArrayList<Integer> integers) {
        qlm.deleteAllById(integers);
    }

    @Override
    public void deleteAll(ArrayList<MauSac> entities) {
        qlm.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        qlm.deleteAll();
    }
}
