package com.example.ass1_mark_2.entity.account;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    private static Account account;
    private Integer id;
    private String hoTen;
    private String chucVu;
    private String sdt;
    private String passWord;
    public static Account getAccount(){
        if(account == null){
           account = new Account();
        }
        return account;
    }
}
