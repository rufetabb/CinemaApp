package com.example.tmsproject.controller;

import com.example.tmsproject.dto.CustomerRegistrationDto;
import com.example.tmsproject.service.CustomerService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
   private  CustomerService customerService;

    public UserRegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
      dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/")
    public String showRegistrationForm(Model model){
        model.addAttribute("customer",new CustomerRegistrationDto());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "/general/registration";
        }
        return "redirect:/";
    }


    @PostMapping("/add")
    public String registerUserAccount( @Valid @ModelAttribute("customer") CustomerRegistrationDto customerRegistrationDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(customerService.userExists(customerRegistrationDto.getEmail())){
            bindingResult.addError(new FieldError("customer","email","Email istifad?? edilmi??dir"));
        }
        if(customerRegistrationDto.getPassword()!=null && customerRegistrationDto.getrPassword()!=null){
            if(!customerRegistrationDto.getPassword().equals(customerRegistrationDto.getrPassword())){
             bindingResult.addError(new FieldError("customer","rPassword","??ifr?? eyni deyil"));
            }
        }
        if(bindingResult.hasErrors()){
            return "/general/registration";
        }else
            customerService.save(customerRegistrationDto);
        return "redirect:/registration/?success";
    }


}
