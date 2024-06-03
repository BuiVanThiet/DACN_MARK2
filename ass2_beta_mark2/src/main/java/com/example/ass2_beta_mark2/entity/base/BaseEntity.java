package com.example.ass2_beta_mark2.entity.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@MappedSuperclass
@ToString
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @Column(name = "trang_thai")
    @NotBlank(message = "Chua chon trang thai")
    private String trangThai;
    @PrePersist
    public void setDate(){
        this.ngayTao = new Date();
        this.ngaySua = new Date();
    }
    @PreUpdate
    public void setDoiDate(){
        this.ngaySua = new Date();
    }

}
