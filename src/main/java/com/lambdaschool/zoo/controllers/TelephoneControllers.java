package com.lambdaschool.zoo.controllers;

import com.lambdaschool.zoo.services.TelephonesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelephoneControllers
{
    @Autowired
    private TelephonesServices telephonesServices;
}
