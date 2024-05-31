package com.example.ass2_beta_mark2.controller;

import com.example.ass1_mark_2.controller.base.BaseController;
import com.example.ass1_mark_2.entity.account.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeControler extends BaseController {
    @GetMapping(value = {"/"})
    public String getIndex(ModelMap model){
        model.addAttribute("form",null);
        model.addAttribute("listCTSP",qlctsp.getAllProductAndQuantity());
        model.addAttribute("listS",qls.findAll());
        addAccout(model);
        return "homePage/Home";
    }
    @GetMapping(value = "/logout")
    public String getLogout(ModelMap model){
        Account.getAccount().setPassWord(null);
        Account.getAccount().setSdt(null);
        return "redirect:/";
    }
}
