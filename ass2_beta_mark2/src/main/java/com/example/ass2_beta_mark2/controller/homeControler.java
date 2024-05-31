package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.account.Account;
import com.example.ass2_beta_mark2.entity.statistical.Statistical;
import com.example.ass2_beta_mark2.service.CtspService;
import com.example.ass2_beta_mark2.service.MauSacService;
import com.example.ass2_beta_mark2.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeControler extends BaseController {
    @GetMapping(value = {"/"})
    public String getIndex(ModelMap model){
        model.addAttribute("listCTSP",qlctsp.getAllProductAndQuantity());
        addAccout(model);
        return "html/Home";
    }
    @GetMapping(value = "/logout")
    public String getLogout(ModelMap model){
        Account.getAccount().setPassWord(null);
        Account.getAccount().setSdt(null);
        return "redirect:/";
    }
}
