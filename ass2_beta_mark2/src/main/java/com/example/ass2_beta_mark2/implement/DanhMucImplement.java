package com.example.ass2_beta_mark2.implement;

import com.example.ass2_beta_mark2.entity.model.DanhMuc;
import com.example.ass2_beta_mark2.respository.DanhMucRepository;
import com.example.ass2_beta_mark2.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DanhMucImplement implements DanhMucService {
    @Autowired
    DanhMucRepository qldm;

    @Override
    public DanhMuc save(DanhMuc entity) {
        return qldm.save(entity);
    }

    @Override
    public ArrayList<DanhMuc> saveAll(ArrayList<DanhMuc> entities) {
        return (ArrayList<DanhMuc>) qldm.saveAll(entities);
    }

    @Override
    public Optional<DanhMuc> findById(Integer integer) {
        return qldm.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return qldm.existsById(integer);
    }

    @Override
    public ArrayList<DanhMuc> findAll() {
        return (ArrayList<DanhMuc>) qldm.findAll();
    }

    @Override
    public ArrayList<DanhMuc> findAllById(ArrayList<Integer> integers) {
        return (ArrayList<DanhMuc>) qldm.findAllById(integers);
    }

    @Override
    public long count() {
        return qldm.count();
    }

    @Override
    public void deleteById(Integer integer) {
        qldm.deleteById(integer);
    }

    @Override
    public void delete(DanhMuc entity) {
        qldm.delete(entity);
    }

    @Override
    public void deleteAllById(ArrayList<Integer> integers) {
        qldm.deleteAllById(integers);
    }

    @Override
    public void deleteAll(ArrayList<DanhMuc> entities) {
        qldm.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        qldm.deleteAll();
    }
}
