package us.siriusteam.springboot.datajpa.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.siriusteam.springboot.datajpa.app.models.service.IUploadFileService;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {

    private IUploadFileService uploadFileService;

    public SpringBootDataJpaApplication(IUploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJpaApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        uploadFileService.deleteAll();
        uploadFileService.init();
    }
}
