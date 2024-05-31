package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.account.Account;
import com.example.ass2_beta_mark2.entity.model.*;
import com.example.ass2_beta_mark2.entity.sumMoney.TotalAmount;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = {"/BanHang"})
public class BanHangController extends BaseController {
    private Integer idKH = null;
    private Integer idHD = null;
    private Integer idCTHD = null;
    private ArrayList<TotalAmount> listTien = new ArrayList<>();
    @GetMapping("/hien-thi")
    public String getIndex(ModelMap model){
        String check = addAccout(model);

        this.addAtribute(model);
        if(check !=null){
            if(Account.getAccount().getChucVu() == null){
                return check;
            }else {
                return "BanHang/banHang";
            }
        }
        return "BanHang/banHang";
    }
    @GetMapping(value = {"/HDCT/{idHD}"})
    public String getHDCT(ModelMap model, @PathVariable(name = "idHD") Integer id){
        String check = addAccout(model);
        this.addAtribute(model);
        HoaDon hd = qlhd.findById(id).orElse(new HoaDon());
        System.out.println("day la id hd " + hd.getId());
        System.out.println("day la id kh " + hd.getKhachHang().getId());
        model.addAttribute("kh",hd.getKhachHang());
        model.addAttribute("hd",hd);
        TotalAmount totalAmount = this.getToTal(hd.getId());
        model.addAttribute("sumMoney",totalAmount);
        this.idHD = hd.getId();
        model.addAttribute("listHDCT",qlhdct.getHDCTByIdHD(id));
        if(check !=null){
            if(Account.getAccount().getChucVu() == null){
                return check;
            }else {
                return "BanHang/banHang";
            }
        }
        return "BanHang/banHang";
    }

    @GetMapping(value = {"/SDT"})
    public String getKHBySDT(ModelMap modelMap, @RequestParam("sdt") String sdt){
        String check = addAccout(modelMap);
        this.addAtribute(modelMap);
        KhachHang kh = this.qlkh.getKHBySDT(sdt).orElse(new KhachHang());
        modelMap.addAttribute("kh",kh);
        this.idKH = kh.getId();
        if(check !=null){
            if(Account.getAccount().getChucVu() == null){
                return check;
            }else {

                return "BanHang/banHang";
            }
        }
        return "BanHang/banHang";
    }

    @GetMapping(value = {"/clear"})
    public String getClear(ModelMap modelMap){
        this.addAtribute(modelMap);
        this.idKH = null;
        this.idHD = null;
        this.idCTHD = null;
        return "redirect:/BanHang/hien-thi";
    }

    @GetMapping(value = {"/addHoaDon"})
    public String getAddHD(ModelMap model){
        KhachHang kh = this.qlkh.findById(this.idKH).orElse(new KhachHang());
        NhanVien nv = new NhanVien();
        nv.setId(Account.getAccount().getId());
        HoaDon hd = new HoaDon();
        hd.setKhachHang(kh);
        hd.setNhanVien(nv);
        hd.setTrangThai("Chua thanh toan");
        hd.setDiaChi(kh.getDiaChi());
        hd.setSdt(kh.getSdt());
        this.qlhd.save(hd);
//        return "redirect:/BanHang/hien-thi";
            return "redirect:/BanHang/hien-thi";
    }

    @PostMapping(value = {"/Mua"})
    public String getMua(ModelMap modelMap,@RequestParam(name = "sl") Integer sl,@RequestParam(name = "idSPCT")Integer idSPCT){

        CTSP ctsp = this.qlctsp.findById(idSPCT).orElse(new CTSP());
        Integer slConLai = ctsp.getSoLuongTon() - sl;
        ctsp.setSoLuongTon(slConLai);
        this.qlctsp.save(ctsp);

        BigDecimal tongTien = this.getTongTien(sl,ctsp.getGiaBan());

        HoaDon hd = new HoaDon();
        hd.setId(idHD);

        boolean check = this.checkTrungSPCT(idSPCT);
        if(check == false){
            System.out.println("Chua ton tai");
            HDCT hdct = new HDCT();
            hdct.setCtsp(ctsp);
            hdct.setHoaDon(hd);
            hdct.setSoLuongMua(sl);
            hdct.setTongTien(tongTien);
            hdct.setGiaBan(ctsp.getGiaBan());
            hdct.setTrangThai("Chua thanh toan");
            this.qlhdct.save(hdct);
        }else {
            System.out.println("Da ton tai " + idCTHD);
            HDCT hdct =this.qlhdct.findById(idCTHD).orElse(new HDCT());
            hdct.setSoLuongMua(hdct.getSoLuongMua() + sl);
            hdct.setTongTien(hdct.getTongTien().add(tongTien));
            hdct.setTrangThai("Chua thanh toan");
            this.qlhdct.save(hdct);
        }

        return "redirect:/BanHang/HDCT/" + idHD;
    }

    @GetMapping("/DeleteCTHD/{idCTHD}")
    public String getDeleteCTHD(ModelMap modelMap,@PathVariable("idCTHD") Integer idCTHD){
        HDCT hdct = this.qlhdct.findById(idCTHD).orElse(new HDCT());
        CTSP ctsp = this.qlctsp.findById(hdct.getCtsp().getId()).orElse(new CTSP());
        ctsp.setSoLuongTon(ctsp.getSoLuongTon() + hdct.getSoLuongMua());
        this.qlctsp.save(ctsp);
        this.qlhdct.delete(hdct);
        return "redirect:/BanHang/HDCT/"+hdct.getHoaDon().getId();
    }

    @GetMapping("/pay")
    public String getPayProduct(){
        HoaDon hd = this.qlhd.findById(idHD).orElse(new HoaDon());
        hd.setTrangThai("Da thanh toan");
        for (HDCT hdct: this.qlhdct.findAll()){
            if(hdct.getHoaDon().getId() == hd.getId()){
                hdct.setTrangThai("Da thanh toan");
                this.qlhdct.save(hdct);
            }
        }
        this.qlhd.save(hd);
        return "redirect:/BanHang/HDCT/"+idHD;
    }
    public void addAtribute(ModelMap model){

        KhachHang kh = new KhachHang();

        HoaDon hd = new HoaDon();

        TotalAmount totalAmount = new TotalAmount();

        this.listTien = qlhdct.getTien();
        for(HoaDon hdDon : this.qlhd.findAll()){
            int id = hdDon.getId();
            boolean found = false;
            for (TotalAmount tt : listTien) {
                if (tt.getId() == id) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                listTien.add(new TotalAmount(id, new BigDecimal(0.00)));
            }
        }
        for (TotalAmount tt : listTien){
            System.out.println("id la " + tt.getId());
            System.out.println("tien la " + tt.getTongTien());
        }

        model.addAttribute("sumMoney",totalAmount);

        model.addAttribute("hd",hd);
        model.addAttribute("kh",kh);
        model.addAttribute("listHD",qlhd.getALLHDByTT());
        model.addAttribute("listTien",this.listTien);
        model.addAttribute("listSPCT",qlctsp.getCTSPExist());
    }

    public TotalAmount getToTal(Integer id){
        for (TotalAmount tt: this.listTien){
            if(tt.getId() == id){
                return tt;
            }
        }
        return null;
    }

    public BigDecimal getTongTien(Integer intValue, BigDecimal bigDecimalValue) {
        BigDecimal intToBigDecimal = new BigDecimal(intValue);
        return intToBigDecimal.multiply(bigDecimalValue);
    }

    public boolean checkTrungSPCT(Integer id){
        for (HDCT hdct: this.qlhdct.getHDCTByIdHD(idHD)){
            if(hdct.getCtsp().getId() == id){
                idCTHD = hdct.getId();
                return true;
            }
        }
        return false;
    }


}
