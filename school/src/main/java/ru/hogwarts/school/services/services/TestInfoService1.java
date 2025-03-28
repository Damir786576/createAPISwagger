package ru.hogwarts.school.services.services;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.services.impl.InfoServiceImpl;

@Service
@Profile("test1")
public class TestInfoService1 implements InfoServiceImpl {

    public String getPort() {
        String port = "4321";
        return port;
    }
}