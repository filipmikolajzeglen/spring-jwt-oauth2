package pl.filipzeglen.springjwtoauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @GetMapping("/api/test")
    public String test() {
        return "ok, it works!";
    }
}
