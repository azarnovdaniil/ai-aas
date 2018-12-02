package ru.daniilazarnov.cli.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

@ShellComponent
public class CliCommands {

    @ShellMethod("Start test.")
    public void test() {

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8080/cli";
        String game = restTemplate.postForObject(resourceUrl, null, String.class);

        System.out.println(game);
    }
}
