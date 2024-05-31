package com.example.ass1_mark_2.entity.model;

import com.example.ass1_mark_2.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mau_sac")
@ToString
public class MauSac extends BaseEntity {
    @Column(name = "ma_mau")
    @NotBlank(message = "Chua nhap ma mau sac!")
    private String maMau;
    @Column(name = "ten_mau")
    @NotBlank(message = "Chua nhap ten mau sac!")
    private String tenMau;
}
