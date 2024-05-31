package com.example.ass2_beta_mark2.service;

import com.example.ass2_beta_mark2.entity.model.SanPham;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Optional;

public interface SanPhamService {
    SanPham save(SanPham entity);

    ArrayList<SanPham> saveAll(ArrayList<SanPham> entities);

    Optional<SanPham> findById(Integer integer);

    boolean existsById(Integer integer);

    ArrayList<SanPham> findAll();

    ArrayList<SanPham> findAllById(ArrayList<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(SanPham entity);

    void deleteAllById(ArrayList<Integer> integers);

    void deleteAll(ArrayList<SanPham> entities);

    void deleteAll();

    SanPham getSPByMa(String ma);
    //ngay1thang6
    ArrayList<SanPham> getSPByName(String name);

    Page<SanPham> getPhanTrang(int page, int pageSize, String name);
}
