package com.example.ass2_beta_mark2.entity.model;

import com.example.ass2_beta_mark2.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hdct")
public class HDCT extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;
    @ManyToOne
    @JoinColumn(name = "id_ctsp")
    private CTSP ctsp;
    @Column(name = "so_luong_mua")
    private int soLuongMua;
    @Column(name = "gia_ban")
    private BigDecimal giaBan;
    @Column(name = "tong_tien")
    private BigDecimal tongTien;
}
