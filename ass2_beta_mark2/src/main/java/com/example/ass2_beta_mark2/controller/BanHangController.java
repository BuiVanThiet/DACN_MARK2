package com.example.ass2_beta_mark2.controller;

import com.example.ass1_mark_2.controller.base.BaseController;
import com.example.ass1_mark_2.entity.account.Account;
import com.example.ass1_mark_2.entity.model.*;
import com.example.ass1_mark_2.entity.sumMoney.TotalAmount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@Controller
@RequestMapping(value = {"/BanHang"})
public class BanHangController extends BaseController {
    private Integer idKH = null;
    private Integer idHD = null;
    private Integer idCTHD = null;
    private Integer pageNumBerHD = 0;
    private Integer pageNumBerHDCT = 0;
    private String pageNumBerSPCT;
    Integer mauSearch = null;
    Integer sizeSearch = null;
    String page;
    String tenSP = "";

    private ArrayList<TotalAmount> listTien = new ArrayList<>();
    @GetMapping("/hien-thi")
    public String getIndex(ModelMap model){
        String check = addAccout(model);

        this.addAtribute(model);
        System.out.println("id size la " + sizeSearch);
        if(check !=null){
            if(Account.getAccount().getChucVu() == null){
                return check;
            }else {
                model.addAttribute("form","../banHang/BanHang.jsp");
                return "homePage/Home";
            }
        }
        model.addAttribute("form","../banHang/BanHang.jsp");
        return "homePage/Home";
    }
    @GetMapping("/trang-hd/{trang}")
    public String getPageHD(@PathVariable("trang") Integer trang){
        pageNumBerHD = trang - 1;
        return "forward:/BanHang/hien-thi";
    }
    @GetMapping("/trang-hdct/{trang}")
    public String getPageHDCT(@PathVariable("trang") Integer trang){
        pageNumBerHDCT = trang - 1;
        return "forward:/BanHang/HDCT/"+idHD;
    }
    @GetMapping("/trang-ctsp/{trang}")
    public String getPageHDCT(@PathVariable("trang") String trang){
        pageNumBerSPCT = trang;
        if(idHD != null){
            return "forward:/BanHang/HDCT/"+idHD;
        }else {
            return "forward:/BanHang/hien-thi";
        }
    }

    @GetMapping("/tim-kiem-ctsp")
    public String getSearch(@RequestParam("tenTim")String ten ,@RequestParam("mauTim") String idMau,@RequestParam("sizeTim")String idS){
        tenSP = ten.trim().toLowerCase();
        if(idS.trim().isEmpty()){
            this.sizeSearch = null;
        }else {
            this.sizeSearch = Integer.parseInt(idS);
        }

        if(idMau.trim().isEmpty()){
            this.mauSearch = null;
        }else {
            this.mauSearch = Integer.parseInt(idMau);
        }
        pageNumBerSPCT = null;
        if(idHD != null){
            return "forward:/BanHang/HDCT/"+idHD;
        }else {

            return "forward:/BanHang/hien-thi";
        }
    }

    @GetMapping(value = {"/HDCT/{idHD}"})
    public String getHDCT(ModelMap model, @PathVariable(name = "idHD") Integer id){
        String check = addAccout(model);
        this.idHD = id;
        this.addAtribute(model);
        HoaDon hd = qlhd.findById(id).orElse(new HoaDon());
        System.out.println("day la id hd " + hd.getId());
        System.out.println("day la id kh " + hd.getKhachHang().getId());
        idKH = hd.getKhachHang().getId();
        model.addAttribute("kh",hd.getKhachHang());
        model.addAttribute("hd",hd);
        TotalAmount totalAmount = this.getToTal(hd.getId());
        model.addAttribute("sumMoney",totalAmount);

        int pageHDCT = (int) this.qlhdct.getHDCTByIdHD(idHD).size() /2;
        if(this.qlhdct.getHDCTByIdHD(idHD).size() % 2 != 0){
            pageHDCT++;
        }
        model.addAttribute("pageHDCT",pageHDCT);

        model.addAttribute("listHDCT",qlhdct.getHDCTByIdHD_Page(idHD,pageNumBerHDCT,2).getContent());
        if(check !=null){
            if(Account.getAccount().getChucVu() == null){
                return check;
            }else {
                model.addAttribute("form","../banHang/BanHang.jsp");
                return "homePage/Home";
            }
        }
        model.addAttribute("form","../banHang/BanHang.jsp");
        return "homePage/Home";
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
                modelMap.addAttribute("form","../banHang/BanHang.jsp");
                return "homePage/Home";
            }
        }
        modelMap.addAttribute("form","../banHang/BanHang.jsp");
        return "homePage/Home";
    }

    @GetMapping(value = {"/clear"})
    public String getClear(ModelMap modelMap){
        this.addAtribute(modelMap);
        this.idKH = null;
        this.idHD = null;
        this.idCTHD = null;
        this.pageNumBerHD = 0;
        this.pageNumBerHDCT = 0;
        this.pageNumBerSPCT = null;
        this.mauSearch = null;
        this.sizeSearch = null;
        this.page = null;
        this.tenSP = "";
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
        String checkAccout = addAccout(modelMap);

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

    @GetMapping("/delete-hd/{id}")
    public String getDeleteHD(@PathVariable("id") Integer id){
        HoaDon hd = this.qlhd.findById(id).orElse(new HoaDon());
        for (HDCT hdct: this.qlhdct.findAll()){
            if(hdct.getHoaDon().getId() == id){
                HDCT hdctDelete = this.qlhdct.findById(hdct.getId()).orElse(new HDCT());
                CTSP ctspUpdate = this.qlctsp.findById(hdct.getCtsp().getId()).orElse(new CTSP());
                ctspUpdate.setSoLuongTon(ctspUpdate.getSoLuongTon() + hdctDelete.getSoLuongMua());
                this.qlctsp.save(ctspUpdate);
                this.qlhdct.delete(hdctDelete);
            }
        }
        this.qlhd.delete(hd);
        return "redirect:/BanHang/clear";
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
        return "redirect:/BanHang/clear";
    }
    public void addAtribute(ModelMap model){

        KhachHang kh = new KhachHang();

        HoaDon hd = new HoaDon();

        TotalAmount totalAmount = new TotalAmount();

        int pageHD = (int) this.qlhd.getALLHDByTT().size() /2;
        if(this.qlhd.getALLHDByTT().size() % 2 != 0){
            pageHD++;
        }

        int pageSPCT = (int) this.qlctsp.getCTSPByNameSP_IdMS_IdS(tenSP,mauSearch,sizeSearch).size() / 2;
        if(this.qlctsp.getCTSPByNameSP_IdMS_IdS(tenSP,mauSearch,sizeSearch).size() % 2 != 0){
            pageSPCT++;
        }

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
        model.addAttribute("idHD",idHD);
        System.out.println("so trang la " + pageHD);
        System.out.println("so trang SPCT  la " + pageSPCT);
        model.addAttribute("pageHD",pageHD);
        model.addAttribute("pageSPCT",pageSPCT);
        model.addAttribute("hd",hd);
        model.addAttribute("kh",kh);
        model.addAttribute("listMS",qlm.findAll());
        model.addAttribute("listS",qls.findAll());
        model.addAttribute("listHD",qlhd.getPhanTrang(pageNumBerHD,2).getContent());
        model.addAttribute("listTien",this.listTien);
        model.addAttribute("listSPCT",this.getPhanTrangSPCT(pageNumBerSPCT));
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

    public ArrayList<CTSP> getPhanTrangSPCT(String pageNumber){
        int page = 1;
        if(pageNumber != null){
            page = Integer.parseInt(pageNumber);
        }
        int start = (page -1) *2;
        int end = Math.min((start + 2),this.qlctsp.getCTSPByNameSP_IdMS_IdS(tenSP,mauSearch,sizeSearch).size());
        ArrayList<CTSP> list = new ArrayList<>(this.qlctsp.getCTSPByNameSP_IdMS_IdS(tenSP,mauSearch,sizeSearch).subList(start,end));
        return list;
    }


}
