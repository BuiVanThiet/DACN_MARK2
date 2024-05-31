package com.example.ass2_beta_mark2.service;

import com.example.ass2_beta_mark2.entity.model.MauSac;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Optional;

public interface MauSacService {
    MauSac save(MauSac entity);

    ArrayList<MauSac> saveAll(ArrayList<MauSac> entities);

    Optional<MauSac> findById(Integer integer);

    boolean existsById(Integer integer);

    ArrayList<MauSac> findAll();

    ArrayList<MauSac> findAllById(ArrayList<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(MauSac entity);

    void deleteAllById(ArrayList<Integer> integers);

    void deleteAll(ArrayList<MauSac> entities);

    void deleteAll();
    //nagy1thang6
    ArrayList<MauSac> getMSByName(String ten);

    Page<MauSac> getPhanTrang(int page, int pageSize, String ten);
}
