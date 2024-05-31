package com.example.ass2_beta_mark2.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseUser extends BaseEntity{
    @Column
    @NotBlank(message = "Chua nhap ho va ten!")
    private String hoTen;
    @Column
    @NotBlank(message = "Chua nhap dia chi!")
    private String diaChi;
    @Column
    @NotBlank(message = "Chua nhap so dien thoai!")
    private String sdt;
}
