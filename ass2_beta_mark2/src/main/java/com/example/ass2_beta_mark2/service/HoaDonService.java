package com.example.ass2_beta_mark2.service;

import com.example.ass2_beta_mark2.entity.model.HoaDon;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Optional;

public interface HoaDonService {
    HoaDon save(HoaDon entity);

    ArrayList<HoaDon> saveAll(ArrayList<HoaDon> entities);

    Optional<HoaDon> findById(Integer integer);

    boolean existsById(Integer integer);

    ArrayList<HoaDon> findAll();

    ArrayList<HoaDon>  findAllById(ArrayList<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(HoaDon entity);

    void deleteAllById(ArrayList<Integer> integers);

    void deleteAll(ArrayList<HoaDon> entities);

    void deleteAll();

    ArrayList<HoaDon> getALLHDByTT();
    //ngay1thang6
    Page<HoaDon> getPhanTrang(int page, int pageSize);

//    Optional<HoaDon> getHDByID(Integer id);
}
