package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.ArrayList;

public interface ChucVuRepository extends CrudRepository<ChucVu,Integer> {
    //ngay1thang6
    //cach moi hoc
    Page<ChucVu> findByTenChucVuContainingIgnoreCase(String tenCV, Pageable pageable);
    ArrayList<ChucVu> findByTenChucVuContainingIgnoreCase(String tenCV);
    //cach hay dung
    @Query("select cv from ChucVu cv where cv.tenChucVu like %:tenTim%")
    ArrayList<ChucVu> getAllChucVuByName(@Param("tenTim") String ten);
}
