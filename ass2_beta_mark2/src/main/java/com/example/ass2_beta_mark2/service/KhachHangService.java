package com.example.ass2_beta_mark2.service;

import com.example.ass2_beta_mark2.entity.model.KhachHang;

import java.util.ArrayList;
import java.util.Optional;

public interface KhachHangService {
    KhachHang save(KhachHang entity);

    ArrayList<KhachHang> saveAll(ArrayList<KhachHang> entities);

    Optional<KhachHang> findById(Integer integer);

    boolean existsById(Integer integer);

    ArrayList<KhachHang> findAll();

    ArrayList<KhachHang> findAllById(ArrayList<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(KhachHang entity);

    void deleteAllById(ArrayList<Integer> integers);

    void deleteAll(ArrayList<KhachHang> entities);

    void deleteAll();

    Optional<KhachHang> getKHBySDT(String sdt);
}
