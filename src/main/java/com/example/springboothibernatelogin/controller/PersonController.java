package com.example.springboothibernatelogin.controller;

import com.example.springboothibernatelogin.dao.PersonAccountDAO;
import com.example.springboothibernatelogin.entity.Person;
import com.example.springboothibernatelogin.exception.LoginTransactionException;
import com.example.springboothibernatelogin.form.PersonDeleteForm;
import com.example.springboothibernatelogin.form.PersonForm;
import com.example.springboothibernatelogin.form.PersonFormAccount;
import com.example.springboothibernatelogin.form.PersonUpdateForm;
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
    public String addPerson(Model model, @ModelAttribute PersonForm personForm){
        if(personForm.getUserName() != null && personForm.getUserName().length() > 0
                && personForm.getPassword() != null && personForm.getPassword().length() > 0
                && personForm.getName() != null && personForm.getName().length() > 0){
            personAccountDAO.addPerson(personForm);
            //personAccountDAO.addAccount(new Person(userName, password, name, age, phone));
            return "redirect:/";
        }
        //model.addAttribute("errorMessage", errorMessage);
        return "createPerson";
    }

    @GetMapping("/updatePerson")
    public String showUpdatePerson(Model model){
        PersonUpdateForm personUpdateForm = new PersonUpdateForm();
        model.addAttribute("personUpdateForm", personUpdateForm);
        return "updatePerson";
    }

    @PostMapping("/updatePerson")
    public String updatePerson(Model model, @ModelAttribute PersonUpdateForm personUpdateForm){
        if(personUpdateForm.getUserName() != null && personUpdateForm.getUserName().length() > 0
                && personUpdateForm.getPassword() != null && personUpdateForm.getPassword().length() > 0
                && personUpdateForm.getName() != null && personUpdateForm.getName().length() > 0){
            personAccountDAO.updatePerson(personUpdateForm);
            return "redirect:/";
        }
        return "updatePerson";
    }

    @GetMapping("/deletePerson")
    public String showDeletePerson(Model model){
        PersonDeleteForm personDeleteForm = new PersonDeleteForm();
        model.addAttribute("personDeleteForm", personDeleteForm);
        return "deletePerson";
    }

    @PostMapping("/deletePerson")
    public String deletePerson(Model model, @ModelAttribute PersonDeleteForm personDeleteForm){
        if(personDeleteForm.getId() > 0){
            personAccountDAO.deletePerson(personDeleteForm.getId());
            return "redirect:/";
        }
        return "deletePerson";
    }
}