package com.example.ass2_beta_mark2.service;

import com.example.ass2_beta_mark2.entity.model.NhanVien;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Optional;

public interface NhanVienService {
    NhanVien save(NhanVien entity);

    ArrayList<NhanVien> saveAll(ArrayList<NhanVien> entities);

    Optional<NhanVien> findById(Integer integer);

    boolean existsById(Integer integer);

    ArrayList<NhanVien> findAll();

    ArrayList<NhanVien> findAllById(ArrayList<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(NhanVien entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(ArrayList<NhanVien> entities);

    void deleteAll();

    Optional<NhanVien> getNVBySdt(String sdt);
    //ngay1thang6
    ArrayList<NhanVien> getNVByName(String ten);

    Page<NhanVien> getPhanTrang(int page, int pageSize, String ten);
}
