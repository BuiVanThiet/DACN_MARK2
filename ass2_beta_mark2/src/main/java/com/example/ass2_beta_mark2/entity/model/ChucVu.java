package com.example.ass2_beta_mark2.entity.model;

import com.example.ass2_beta_mark2.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chuc_vu")
@ToString
public class ChucVu extends BaseEntity {
    @Column(name = "ma_chuc_vu")
    @NotBlank(message = "Chua dien ma chuc vu!")
    private String maChucVu;
    @Column(name = "ten_chuc_vu")
    @NotBlank(message = "Chua dien ten chuc vu!")
    private String tenChucVu;
}
