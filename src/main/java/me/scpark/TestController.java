package me.scpark;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/hi")
    public String hi() {
        return "Hello World";
    }


    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @PostMapping("/test")
    public String posttest() {
        return "Hello post";
    }


    @PutMapping("/test")
    public String puttest() {
        return "Hello put";
    }

    @DeleteMapping("/test")
    public String deleteTest() {
        return "Hello delete";
    }

}
