package com.example.ass2_beta_mark2.entity.model;

import com.example.ass2_beta_mark2.entity.base.BaseUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "nhan_vien")
public class NhanVien extends BaseUser {
    @Column(name = "ma_nv")
    @NotBlank(message = "Chua nhap ma nhan vien!")
    private String maNhanVien;
    @Column(name = "mat_khau")
    @NotBlank(message = "Chua dat mat khau!")
    private String matKhau;
    @ManyToOne
    @JoinColumn(name = "id_chuc_vu")
    @NotNull(message = "Chua chon chuc vu!")
    private ChucVu chucVu;
}
