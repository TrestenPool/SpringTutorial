package com.tpool.base.controller;

import com.tpool.base.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @GetMapping("/")
    public String showForm(Model model){
        // create the customer and give to the model to render on the view
        model.addAttribute("customer", new Customer());

        // render the form
        return "customer/form";
    }

    @PostMapping("/processForm")
    public String processForm(
            // tells Spring MVC to perform validation
            @Valid @ModelAttribute("customer") Customer customer,
            // holds the results of the validation
            BindingResult bindingResult
    ){

        System.out.println("---------------");

        System.out.println("Last name: |" + customer.getLastName() + "|");

        System.out.println("Binding result: " + bindingResult.toString());

        System.out.println("---------------");

        // return back to form
        if(bindingResult.hasErrors()){
            return "customer/form";
        }
        // go to the processed form
        else{
            return "customer/processed";
        }

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        System.out.println("in initbinder");

        // creating the string trimmer
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // saying that for all Strings that come in, register the stringtrimmereditor to be ran on it
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}
