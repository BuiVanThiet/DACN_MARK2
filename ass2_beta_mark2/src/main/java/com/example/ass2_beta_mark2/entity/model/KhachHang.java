package com.example.ass2_beta_mark2.entity.model;

import com.example.ass2_beta_mark2.entity.base.BaseUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "khach_hang")
@ToString
public class KhachHang extends BaseUser {

}
