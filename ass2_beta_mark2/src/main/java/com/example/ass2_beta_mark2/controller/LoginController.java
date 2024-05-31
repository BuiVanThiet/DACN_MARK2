package com.example.ass2_beta_mark2.controller;

import com.example.ass1_mark_2.controller.base.BaseController;
import com.example.ass1_mark_2.entity.account.Account;
import com.example.ass1_mark_2.entity.model.NhanVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController extends BaseController {

    @GetMapping(value = {"/login"})
    public String getForm(ModelMap model){
        Account acc = new Account();
        if(Account.getAccount() != null){
            acc = Account.getAccount();
        }
        model.addAttribute("action","/scanAccount");
        model.addAttribute("acc",acc);
        return "homePage/Login";
    }
    @PostMapping(value = {"/scanAccount"})
    public String getScan(ModelMap model, @ModelAttribute Account acc){
        boolean check = this.getCheck(acc.getSdt(),acc.getPassWord());
        if(check == true){
            NhanVien nv = this.qlnv.getNVBySdt(acc.getSdt()).orElse(new NhanVien());
            Account.getAccount().setId(nv.getId());
            Account.getAccount().setHoTen(nv.getHoTen());
            Account.getAccount().setChucVu(nv.getChucVu().getMaChucVu());
            Account.getAccount().setSdt(nv.getSdt());
            Account.getAccount().setPassWord(nv.getMatKhau());
            System.out.println(Account.getAccount().toString());
            return "redirect:/";
        }else {
            model.addAttribute("mess","Sai tai khoan hoac mat khau, moi ban dien lai!");
            Account.getAccount().setSdt(acc.getSdt());
            Account.getAccount().setPassWord(acc.getPassWord());
            Account acc2 = new Account();
            if(Account.getAccount() != null){
                acc2 = Account.getAccount();
            }
            model.addAttribute("action","/scanAccount");
            model.addAttribute("acc",acc2);
            return "homePage/Login";
        }
    }

    public boolean getCheck(String sdt,String pass){
        for (NhanVien nv: this.qlnv.findAll()){
            if(sdt.equals(nv.getSdt()) && pass.equals(nv.getMatKhau())){
                return true;
            }
        }
        return false;
    }
}
