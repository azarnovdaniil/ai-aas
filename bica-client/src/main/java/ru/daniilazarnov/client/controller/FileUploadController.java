package ru.daniilazarnov.client.controller;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.daniilazarnov.client.dto.Event;
import ru.daniilazarnov.client.dto.EventTO;
import ru.daniilazarnov.client.exception.ClientFileNotFoundException;
import ru.daniilazarnov.client.service.StorageService;

import javax.vecmath.Point3d;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {

        storageService.store(file);

        Reader reader = new InputStreamReader(file.getInputStream());

        List<Event> events = new CsvToBeanBuilder<Event>(reader)
                .withType(Event.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();

        List<Event> result = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8080/client";
        result.add(restTemplate.postForObject(resourceUrl, event_eventTO.apply(events.get(0)), Event.class));

        result.forEach(System.out::println);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(ClientFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(ClientFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    public static final Function<Event, EventTO> event_eventTO = event -> new EventTO(event.getAction(), event.getActor(), event.getTarget(), new Point3d(event.getX(), event.getY(), event.getZ()));


}
