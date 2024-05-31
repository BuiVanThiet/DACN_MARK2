package com.example.ass1_mark_2.entity.model;

import com.example.ass1_mark_2.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "san_pham")
@ToString
public class SanPham extends BaseEntity {
    @Column(name = "ma_san_pham")
    @NotBlank(message = "Chua nhap ma san pham!")
    private String maSanPham;
    @Column(name = "ten_san_pham")
    @NotBlank(message = "Chua nhap ten san pham!")
    private String tenSanPham;
    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    @NotNull(message = "Chua chon danh muc san pham!")
    private DanhMuc danhMuc;
}
