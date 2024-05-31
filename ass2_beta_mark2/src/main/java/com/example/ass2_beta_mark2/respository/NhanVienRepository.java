package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.NhanVien;
import com.example.ass2_beta_mark2.entity.statistical.Statistical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NhanVienRepository extends CrudRepository<NhanVien,Integer> {
    @Query("select nv from NhanVien nv where nv.sdt = :sdtCheck")
    Optional<NhanVien> getNVBySDT(@Param("sdtCheck") String sdt);
}
