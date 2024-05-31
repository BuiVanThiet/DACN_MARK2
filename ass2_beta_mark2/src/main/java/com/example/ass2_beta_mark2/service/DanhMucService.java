package com.example.ass2_beta_mark2.service;

import com.example.ass2_beta_mark2.entity.model.DanhMuc;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Optional;

public interface DanhMucService {
    DanhMuc save(DanhMuc entity);

    ArrayList<DanhMuc> saveAll(ArrayList<DanhMuc> entities);

    Optional<DanhMuc> findById(Integer integer);

    boolean existsById(Integer integer);

    ArrayList<DanhMuc> findAll();

    ArrayList<DanhMuc> findAllById(ArrayList<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(DanhMuc entity);

    void deleteAllById(ArrayList<Integer> integers);

    void deleteAll(ArrayList<DanhMuc> entities);

    void deleteAll();
    //ngay1thang6
    ArrayList<DanhMuc> getDanhMucByName(String ten);

    Page<DanhMuc> getPhanTrang(int page, int pageSize, String ten);
}
