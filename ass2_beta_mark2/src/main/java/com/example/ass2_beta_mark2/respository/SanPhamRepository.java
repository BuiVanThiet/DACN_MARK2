package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.SanPham;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SanPhamRepository extends CrudRepository<SanPham,Integer> {
    @Query("select sp from SanPham sp where sp.maSanPham = :maCheck")
    public Optional<SanPham> getSPByMa(@Param("maCheck") String ma);
}
