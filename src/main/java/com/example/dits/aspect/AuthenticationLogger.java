package com.example.dits.aspect;

import com.example.dits.dto.UserInfoDTO;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

@Component
public class AuthenticationLogger {
    private static final String filePath = "log.txt";

    public void log(Date date, UserInfoDTO user) {
        try (Writer writer = new FileWriter(filePath, true)) {
            String event = "\n" +
                    date.toString() + "\n" +
                    user.toString() + "\n";
            writer.write(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
