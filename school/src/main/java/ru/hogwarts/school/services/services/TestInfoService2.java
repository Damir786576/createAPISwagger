package ru.hogwarts.school.services.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.services.impl.InfoServiceImpl;

@Service
@Profile("test2")
public class TestInfoService2 implements InfoServiceImpl {

    public String getPort() {
        String port = "1234";
        return port;
    }
}