package com.example.ass2_beta_mark2.implement;

import com.example.ass2_beta_mark2.entity.model.SanPham;
import com.example.ass2_beta_mark2.respository.SanPhamRepository;
import com.example.ass2_beta_mark2.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SanPhamImplement implements SanPhamService {
    @Autowired
    SanPhamRepository qlsp;

    @Override
    public SanPham save(SanPham entity) {
        return qlsp.save(entity);
    }

    @Override
    public ArrayList<SanPham> saveAll(ArrayList<SanPham> entities) {
        return (ArrayList<SanPham>) qlsp.saveAll(entities);
    }

    @Override
    public Optional<SanPham> findById(Integer integer) {
        return qlsp.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return qlsp.existsById(integer);
    }

    @Override
    public ArrayList<SanPham> findAll() {
        return (ArrayList<SanPham>) qlsp.findAll();
    }

    @Override
    public ArrayList<SanPham> findAllById(ArrayList<Integer> integers) {
        return (ArrayList<SanPham>) qlsp.findAllById(integers);
    }

    @Override
    public long count() {
        return qlsp.count();
    }

    @Override
    public void deleteById(Integer integer) {
        qlsp.deleteById(integer);
    }

    @Override
    public void delete(SanPham entity) {
        qlsp.delete(entity);
    }

    @Override
    public void deleteAllById(ArrayList<Integer> integers) {
        qlsp.deleteAllById(integers);
    }

    @Override
    public void deleteAll(ArrayList<SanPham> entities) {
        qlsp.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        qlsp.deleteAll();
    }
    @Override
    public SanPham getSPByMa(String ma){
        return this.qlsp.getSPByMa(ma).orElse(new SanPham());
    }

    @Override
    public ArrayList<SanPham> getSPByName(String name) {
        return qlsp.getSPByName(name);
    }

    @Override
    public Page<SanPham> getPhanTrang(int page, int pageSize, String name) {
        Pageable pageable = PageRequest.of(page,pageSize);
        if(name.trim().equals("")||name == null){
            return qlsp.findAll(pageable);
        }else {
            return qlsp.findByTenSanPhamContainsIgnoreCase(name,pageable);
        }
    }
}
