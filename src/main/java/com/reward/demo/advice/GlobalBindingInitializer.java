package com.reward.demo.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@ControllerAdvice
public class GlobalBindingInitializer {


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Register a custom editor for LocalDate
        binder.registerCustomEditor(LocalDate.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
