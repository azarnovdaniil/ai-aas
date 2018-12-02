package ru.daniilazarnov.client.storage;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.daniilazarnov.web.property.ClientProperties;
import ru.daniilazarnov.web.exception.ClientException;
import ru.daniilazarnov.web.service.FileSystemStorageService;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class FileSystemStorageServiceTests {

    private ClientProperties properties = new ClientProperties();
    private FileSystemStorageService service;

    @Before
    public void init() {
        properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
        service = new FileSystemStorageService(properties);
        service.init();
    }

    @Test
    public void loadNonExistent() {
        assertThat(service.load("foo.txt")).doesNotExist();
    }

    @Test
    public void saveAndLoad() {
        service.store(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes()));
        assertThat(service.load("foo.txt")).exists();
    }

    @Test(expected = ClientException.class)
    public void saveNotPermitted() {
        service.store(new MockMultipartFile("foo", "../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
    }

    @Test
    public void savePermitted() {
        service.store(new MockMultipartFile("foo", "bar/../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
    }

}
