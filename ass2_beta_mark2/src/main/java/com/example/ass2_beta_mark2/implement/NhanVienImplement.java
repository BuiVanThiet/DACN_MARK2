package com.example.ass2_beta_mark2.implement;

import com.example.ass2_beta_mark2.entity.model.NhanVien;
import com.example.ass2_beta_mark2.respository.NhanVienRepository;
import com.example.ass2_beta_mark2.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class NhanVienImplement implements NhanVienService {
    @Autowired
    NhanVienRepository qlnv;

    @Override
    public NhanVien save(NhanVien entity) {
        return qlnv.save(entity);
    }

    @Override
    public ArrayList<NhanVien> saveAll(ArrayList<NhanVien> entities) {
        return (ArrayList<NhanVien>) qlnv.saveAll(entities);
    }

    @Override
    public Optional<NhanVien> findById(Integer integer) {
        return qlnv.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return qlnv.existsById(integer);
    }

    @Override
    public ArrayList<NhanVien> findAll() {
        return (ArrayList<NhanVien>) qlnv.findAll();
    }

    @Override
    public ArrayList<NhanVien> findAllById(ArrayList<Integer> integers) {
        return (ArrayList<NhanVien>) qlnv.findAllById(integers);
    }

    @Override
    public long count() {
        return qlnv.count();
    }

    @Override
    public void deleteById(Integer integer) {
        qlnv.deleteById(integer);
    }

    @Override
    public void delete(NhanVien entity) {
        qlnv.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        qlnv.deleteAllById(integers);
    }

    @Override
    public void deleteAll(ArrayList<NhanVien> entities) {
        qlnv.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        qlnv.deleteAll();
    }
    @Override
    public  Optional<NhanVien> getNVBySdt(String sdt){
        return qlnv.getNVBySDT(sdt);
    }
}
