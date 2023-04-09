package kr.datasolution.kdigital2023.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.datasolution.kdigital2023.demo.model.Hobby;
import kr.datasolution.kdigital2023.demo.service.HobbyMgmtService;

@RestController
@RequestMapping("/")
public class LandingController {
	private static final Logger log = LoggerFactory.getLogger(LandingController.class);
	
	private final HobbyMgmtService hobbyMgmtService;

	LandingController(HobbyMgmtService hobbyMgmtService) {
		this.hobbyMgmtService = hobbyMgmtService;
	}

	@GetMapping("")
	public ModelAndView getIndex() {
		log.info("accessed index page");
		return new ModelAndView("/index");
	}

	@GetMapping("/hobbies")
	public ResponseEntity<Map<String, Object>> getHobbies() {
		Map<String, Object> body = new HashMap<>();

		List<Hobby> hobbies = hobbyMgmtService.getHobbies();
		body.put("hobbies", hobbies);

		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
	}

}
