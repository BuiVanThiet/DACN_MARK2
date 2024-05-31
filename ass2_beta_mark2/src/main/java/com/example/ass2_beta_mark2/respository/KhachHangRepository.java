package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.KhachHang;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KhachHangRepository extends CrudRepository<KhachHang,Integer> {
    @Query("select kh from KhachHang kh where kh.sdt = :sdtCheck")
    Optional<KhachHang> getKHBySDT(@Param("sdtCheck") String sdt);
}
