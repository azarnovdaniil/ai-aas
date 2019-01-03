package ru.daniilazarnov.cli.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.common.model.data.Operation;
import ru.daniilazarnov.common.model.serialization.json.serializers.OperationSerializer;

@RestController
public class ClientController {

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    public ClientController() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Operation.class, new OperationSerializer());

        mapper.registerModule(module);
    }

    @PostMapping(value = "/teleport", params = {"sessionId", "botName"})
    public void teleportController(@RequestBody Operation operation,
                                   @RequestParam("sessionId") String sessionId,
                                   @RequestParam("botName") String botName) throws JsonProcessingException {

        logger.info(sessionId + " " + botName + " " + mapper.writeValueAsString(operation));
    }
}
