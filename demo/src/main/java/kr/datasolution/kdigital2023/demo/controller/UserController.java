package kr.datasolution.kdigital2023.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.datasolution.kdigital2023.demo.model.Hobby;
import kr.datasolution.kdigital2023.demo.model.User;
import kr.datasolution.kdigital2023.demo.service.HobbyMgmtService;
import kr.datasolution.kdigital2023.demo.service.UserMgmtService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserMgmtService userMgmtService;
	@Autowired
	private HobbyMgmtService hobbyMgmtService;

	@GetMapping("")
	public ModelAndView getEnrollForm(User userInfo) {
		ModelAndView mv = new ModelAndView("/user/enrollForm");
		List<Hobby> hobbies = hobbyMgmtService.getHobbies();
		mv.addObject("allHobbies", hobbies);
		mv.addObject("userInfo", userInfo);
		log.debug(userInfo.toString());
		return mv;
	}
	
	
	@PostMapping(value="")
	public ModelAndView saveEnrollForm(@ModelAttribute User userInfo) {
		log.debug(userInfo.toString());
		ModelAndView mv = new ModelAndView();
		
		try {
			User enrolled = userMgmtService.enrollUser(userInfo);
			
			mv.setViewName("/user/enrollDone");
			List<Hobby> hobbies = hobbyMgmtService.getHobbies();
			mv.addObject("allHobbies", hobbies);
			mv.addObject("userInfo", enrolled);
			mv.setStatus(HttpStatus.CREATED);
		} catch(RuntimeException re) {
			log.error("RuntimeException message: {}", re.getMessage());
			
			if(re.getMessage().contains("users_pkey")) {
				mv.setViewName("/user/enrollDup");
				mv.addObject("userInfo", userInfo);
				mv.setStatus(HttpStatus.CONFLICT);
			} else {
				throw re;
			}
		}
		
		return mv;
	}
}
