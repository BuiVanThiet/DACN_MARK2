package com.example.ass2_beta_mark2.implement;

import com.example.ass2_beta_mark2.entity.model.ChucVu;
import com.example.ass2_beta_mark2.respository.ChucVuRepository;
import com.example.ass2_beta_mark2.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ChucVuImplement implements ChucVuService {
    @Autowired
    ChucVuRepository qlcv;

    @Override
    public ChucVu save(ChucVu entity) {
        return qlcv.save(entity);
    }

    @Override
    public ArrayList<ChucVu> saveAll(ArrayList<ChucVu> entities) {
        return (ArrayList<ChucVu>) qlcv.saveAll(entities);
    }

    @Override
    public Optional<ChucVu> findById(Integer integer) {
        return qlcv.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return qlcv.existsById(integer);
    }

    @Override
    public ArrayList<ChucVu>  findAll() {
        return (ArrayList<ChucVu>) qlcv.findAll();
    }

    @Override
    public ArrayList<ChucVu>  findAllById(ArrayList<Integer> integers) {
        return (ArrayList<ChucVu>) qlcv.findAllById(integers);
    }

    @Override
    public long count() {
        return qlcv.count();
    }

    @Override
    public void deleteById(Integer integer) {
        qlcv.deleteById(integer);
    }

    @Override
    public void delete(ChucVu entity) {
        qlcv.delete(entity);
    }

    @Override
    public void deleteAllById(ArrayList<Integer> integers) {
        qlcv.deleteAllById(integers);
    }

    @Override
    public void deleteAll(ArrayList<ChucVu> entities) {
        qlcv.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        qlcv.deleteAll();
    }
}
