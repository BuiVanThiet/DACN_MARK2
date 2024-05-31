package com.example.ass1_mark_2.entity.model;

import com.example.ass1_mark_2.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "size")
public class Size extends BaseEntity {
    @Column(name = "ma_size")
    @NotBlank(message = "Chua nhap ma size!")
    private String ma;
    @Column(name = "ten_size")
    @NotBlank(message = "Chua nhap ten size!")
    private String ten;
}
