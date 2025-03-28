package ru.hogwarts.school.services.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.services.impl.InfoServiceImpl;

@Service
@Profile("production")
public class InfoService implements InfoServiceImpl {

    @Value("${server.port}")
    private String port;

    public String getPort() {
        return port;
    }
}