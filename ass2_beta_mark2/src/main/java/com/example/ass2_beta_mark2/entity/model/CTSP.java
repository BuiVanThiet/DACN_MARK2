package com.example.ass1_mark_2.entity.model;

import com.example.ass1_mark_2.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ctsp")
@ToString
public class CTSP extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_sp")
    private SanPham sanPham;
    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    @NotNull(message = "Chua chon mau sac!")
    private MauSac mauSac;
    @ManyToOne
    @JoinColumn(name ="id_size")
    @NotNull(message = "Chua chon size!")
    private Size size;
    @Column(name = "gia_ban")
    @NotNull(message = "Chua nhap gia ban!")
    @DecimalMax(message = "Gia phai thap hon 500 000 000!",value = "500000000")
    @DecimalMin(message = "Gia khong duoc thap hon 50 000!",value = "50000")
    private BigDecimal giaBan;
    @Column(name = "so_luong_ton")
    @NotNull(message = "Khong duoc de trong so luong")
    @DecimalMax(value = "100000",message = "So luong khong duoc qua 100 000 chiec!")
    @DecimalMin(value = "1",message = "So luong it nhat phai co 1 chiec!")
    private int soLuongTon;
}
