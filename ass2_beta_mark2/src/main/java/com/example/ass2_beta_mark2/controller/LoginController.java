package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.account.Account;
import com.example.ass2_beta_mark2.entity.model.NhanVien;
import com.example.ass2_beta_mark2.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "html/login";
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
            Account.getAccount().setSdt(acc.getSdt());
            Account.getAccount().setPassWord(acc.getPassWord());
            return "redirect:/login";
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
