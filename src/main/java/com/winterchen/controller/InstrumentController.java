package com.winterchen.controller;

import com.winterchen.service.user.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/instrument")
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;


}
