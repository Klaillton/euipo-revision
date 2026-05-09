package com.klaillton.virtualthreads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class VirtualthreadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualthreadsApplication.class, args);
	}

}

@RestController
@RequestMapping("/demo")
class DemoController {

	@GetMapping("path")
	public String get() throws InterruptedException {
		Thread.sleep(1000);
		return "Hello World!";
	}
	

}
