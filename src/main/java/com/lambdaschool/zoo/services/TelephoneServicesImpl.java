package com.lambdaschool.zoo.services;

import com.lambdaschool.zoo.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "telephoneServices")
public class TelephoneServicesImpl implements TelephonesServices
{
    @Autowired
    private TelephoneRepository telephoneRepository;
}
