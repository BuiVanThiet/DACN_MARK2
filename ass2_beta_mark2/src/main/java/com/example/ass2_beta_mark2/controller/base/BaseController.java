package com.example.ass2_beta_mark2.controller.base;

import com.example.ass2_beta_mark2.entity.account.Account;
import com.example.ass2_beta_mark2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

public abstract class BaseController {
    @Autowired
    public ChucVuService qlcv;
    @Autowired
    public CtspService qlctsp;
    @Autowired
    public SanPhamService qlsp;
    @Autowired
    public DanhMucService qldm;
    @Autowired
    public KhachHangService qlkh;
    @Autowired
    public MauSacService qlm;
    @Autowired
    public NhanVienService qlnv;
    @Autowired
    public SizeService qls;
    @Autowired
    public HoaDonService qlhd;
    @Autowired
    public HDCTService qlhdct;

    public String addAccout(ModelMap model){
        if(Account.getAccount().getSdt() != null){
            model.addAttribute("login","Dang xuat");
            model.addAttribute("nameAccout", "Xin chào, " + Account.getAccount().getHoTen());
            model.addAttribute("chucVu", Account.getAccount().getChucVu());
            model.addAttribute("href","/logout");
        }else {
            model.addAttribute("login","Dang nhap");
            model.addAttribute("nameAccout", "Chào mừng đến trang web");
            model.addAttribute("href","/login");
        }
        if(Account.getAccount().getSdt() == null){
            return "redirect:/login";
        }
        if(!Account.getAccount().getChucVu().equals("CV00")){
            return "redirect:/";
        }
        return null;
    }

}
