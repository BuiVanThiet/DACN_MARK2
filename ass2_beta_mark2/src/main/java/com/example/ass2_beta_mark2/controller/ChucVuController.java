package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.ChucVu;
import com.example.ass2_beta_mark2.service.ChucVuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/ChucVu"})
public class ChucVuController extends BaseController {
    boolean checkNut;
    int page = 0;
    String tenSert = "";
    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int pagetNumber = (int) qlcv.getCVByName(tenSert).size()/2;
        if(qlcv.getCVByName(tenSert).size() % 2 != 0){
            pagetNumber++;
        }
        model.addAttribute("trang",pagetNumber);

        model.addAttribute("listCV",qlcv.getPhanTrang(page,2,tenSert).getContent());
        model.addAttribute("form","../chucVu/Table.jsp");
        return "homePage/Home";
    }

    @GetMapping(value = {"/trang/{trang}"})
    public String getPage(ModelMap model,@PathVariable("trang") int pageNumber){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        page = pageNumber-1;
        return "forward:/ChucVu/hien-thi";
    }
    @GetMapping("/tim-kiem")
    public String getSert(@RequestParam("tenTim") String tenTim,ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        tenSert = tenTim.trim().toLowerCase();
        page = 0;
        return "forward:/ChucVu/hien-thi";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        checkNut = true;
        ChucVu cv = new ChucVu();
        model.addAttribute("cv",cv);
        model.addAttribute("check",checkNut);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");

        model.addAttribute("form","../chucVu/AddOrUpdate.jsp");
        return "homePage/Home";
    }
    @GetMapping(value = {"/delete/{idCV}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idCV") int idCV){
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        this.qlcv.deleteById(idCV);
        page = 0;
        return "redirect:/ChucVu/hien-thi";
    }
    @GetMapping(value = {"/detail/{idCV}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idCV") int idCV){
        checkNut = false;
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        ChucVu cv = this.qlcv.findById(idCV).orElse(new ChucVu());
        model.addAttribute("cv",cv);
        model.addAttribute("check",checkNut);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");

        model.addAttribute("form","../chucVu/AddOrUpdate.jsp");
        return "homePage/Home";    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @Valid @ModelAttribute("cv") ChucVu cv, BindingResult bindingResult){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("cv",cv);
            model.addAttribute("check",checkNut);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("value",checkNut ? "Them" : "Sua");
            model.addAttribute("form","../chucVu/AddOrUpdate.jsp");
            return "homePage/Home";
        }

        if(cv.getId() != null){
            ChucVu cvUpdate = this.qlcv.findById(cv.getId()).orElse(new ChucVu());
            cv.setNgayTao(cvUpdate.getNgayTao());
        }

        this.qlcv.save(cv);
        return "redirect:/ChucVu/hien-thi";
    }
}
