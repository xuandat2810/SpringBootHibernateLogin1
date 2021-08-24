package com.example.springboothibernatelogin.controller;

import com.example.springboothibernatelogin.dao.PersonAccountDAO;
import com.example.springboothibernatelogin.entity.Person;
import com.example.springboothibernatelogin.exception.LoginTransactionException;
import com.example.springboothibernatelogin.form.PersonForm;
import com.example.springboothibernatelogin.form.PersonFormAccount;
import com.example.springboothibernatelogin.model.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonAccountDAO personAccountDAO;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAccounts(Model model){
        model.addAttribute("accountInfos", personAccountDAO.listPersonAccountInfo());
        return "accountsPage";
    }

    @GetMapping("/createPerson")
    public String showAddPerson(Model model){
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return "createPerson";
    }

    @PostMapping("/createPerson")
    public String AddPerson(Model model, @ModelAttribute PersonForm personForm){
        String userName = personForm.getUserName();
        String password = personForm.getPassword();
        String name = personForm.getName();
        int age = personForm.getAge();
        int phone = personForm.getPhone();

        if(userName != null && password != null &&
        name != null && age > 0){
            //personAccountDAO.addAccount(new Person(userName, password, name, age, phone));
            //return "redirect:/accountsPage";
        }

        //model.addAttribute("errorMessage", errorMessage);
        return "createPerson";
    }

    @GetMapping("/updatePerson")
    public String showUpdatePerson(Model model){

        return "updatePerson";
    }

    @GetMapping("/deletePerson")
    public String showDeletePerson(Model model){

        return "deletePerson";
    }
}