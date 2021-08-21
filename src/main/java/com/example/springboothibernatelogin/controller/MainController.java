package com.example.springboothibernatelogin.controller;

import com.example.springboothibernatelogin.dao.PersonAccountDAO;
import com.example.springboothibernatelogin.model.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private PersonAccountDAO personAccountDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBankAccounts(Model model){
        List<PersonInfo> list = personAccountDAO.listPersonAccountInfo();

        model.addAttribute("accountInfos", list);

        return "accountsPage";
    }

    public String loginAccount(Model model){
        return "sdf";
    }
}