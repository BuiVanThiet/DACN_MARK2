package com.example.ass1_mark_2.entity.model;

import com.example.ass1_mark_2.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "danh_muc")
public class DanhMuc extends BaseEntity {
    @Column(name = "ma_danh_muc")
    @NotBlank(message = "Chua nhap ma danh muc")
    private String maDanhmuc;
    @Column(name = "ten_danh_muc")
    @NotBlank(message = "Chua nhap ten danh muc")
    private String tenDanhMuc;

}
