package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface DanhMucRepository extends CrudRepository<DanhMuc,Integer> {
    //ngay 1thang6
    Page<DanhMuc> findByTenDanhMucIsContainingIgnoreCase(Pageable pageable, String ten);
    @Query("select dm from DanhMuc dm where dm.tenDanhMuc like %:tenTim%")
    ArrayList<DanhMuc> getDanhMucByName(@Param("tenTim") String ten);
}
