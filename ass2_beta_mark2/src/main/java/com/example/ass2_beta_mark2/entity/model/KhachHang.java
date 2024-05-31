package com.example.ass1_mark_2.entity.model;

import com.example.ass1_mark_2.entity.base.BaseUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "khach_hang")
@ToString
public class KhachHang extends BaseUser {

}
