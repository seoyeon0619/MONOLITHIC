package kr.datasolution.kdigital2023.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.datasolution.kdigital2023.demo.model.Department;
import kr.datasolution.kdigital2023.demo.model.Hobby;
import kr.datasolution.kdigital2023.demo.model.User;
import kr.datasolution.kdigital2023.demo.model.UserStatus;
import kr.datasolution.kdigital2023.demo.service.DepartmentMgmtService;
import kr.datasolution.kdigital2023.demo.service.HobbyMgmtService;
import kr.datasolution.kdigital2023.demo.service.UserMgmtService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserMgmtService userMgmtService;
	@Autowired
	private HobbyMgmtService hobbyMgmtService;
	@Autowired
	private DepartmentMgmtService departmentMgmtService;
	
	@GetMapping("")
	public ModelAndView getIndex(User userInfo) {
		log.debug("accessed admin index page");
		
		List<User> users = userMgmtService.getAllUsers();
		List<Hobby> hobbies = hobbyMgmtService.getHobbies();
		List<Department> departments = departmentMgmtService.getAllDepartment();
		List<UserStatus> userStatus = userMgmtService.getAllUserStatus();
		
		ModelAndView mv = new ModelAndView("/admin/index");
		mv.addObject("userList", users);
		mv.addObject("userInfo", userInfo);
		mv.addObject("allHobbies", hobbies);
		mv.addObject("allDepartment", departments);
		mv.addObject("allUserStatus", userStatus);
		mv.setStatus(HttpStatus.OK);
		
		return mv;
	}
	
	@GetMapping("/user/{userId}")
	public User getUserInfo(@PathVariable("userId") String userId) {
		return userMgmtService.getUser(userId);
	}
	
	@PutMapping("/user/{userId}")
	public ModelAndView updateUser(@PathVariable("userId") String userId, @ModelAttribute User userInfo) {
		
		User user = userMgmtService.UpdateUser(userId, userInfo);
		
		List<User> users = userMgmtService.getAllUsers();
		List<Hobby> hobbies = hobbyMgmtService.getHobbies();
		List<Department> departments = departmentMgmtService.getAllDepartment();
		List<UserStatus> userStatus = userMgmtService.getAllUserStatus();
		
		ModelAndView mv = new ModelAndView("/admin/index");
		mv.addObject("userList", users);
		mv.addObject("userInfo", user);
		mv.addObject("allHobbies", hobbies);
		mv.addObject("allDepartment", departments);
		mv.addObject("allUserStatus", userStatus);
		mv.setStatus(HttpStatus.OK);
		
		return mv;
	}
	
	@DeleteMapping("/user/{userId}")
	public ModelAndView deleteUser(@PathVariable("userId") String userId) {
		
		userMgmtService.deleteUser(userId);
		
		
		List<User> users = userMgmtService.getAllUsers();
		List<Hobby> hobbies = hobbyMgmtService.getHobbies();
		List<Department> departments = departmentMgmtService.getAllDepartment();
		List<UserStatus> userStatus = userMgmtService.getAllUserStatus();
		
		ModelAndView mv = new ModelAndView("/admin/index");
		mv.addObject("userList", users);
		mv.addObject("userInfo", new User());
		mv.addObject("allHobbies", hobbies);
		mv.addObject("allDepartment", departments);
		mv.addObject("allUserStatus", userStatus);
		mv.setStatus(HttpStatus.OK);
		
		return mv;
	}
	
	@GetMapping("/hobby")
	public ModelAndView getHobbyMgmt() {
		
		List<Hobby> hobbies = hobbyMgmtService.getHobbies();

		ModelAndView mv = new ModelAndView("/admin/hobbyMgmt");
		mv.addObject("allHobbies", hobbies);
		mv.addObject("hobby", new Hobby());
		mv.setStatus(HttpStatus.OK);
		
		return mv;
	}
	
	@PostMapping("/hobby")
	public ModelAndView addHobby(@ModelAttribute Hobby hobby) {
		
		hobbyMgmtService.addHobby(hobby);
		
		List<Hobby> hobbies = hobbyMgmtService.getHobbies();
		ModelAndView mv = new ModelAndView("/admin/hobbyMgmt");
		mv.addObject("allHobbies", hobbies);
		mv.addObject("hobby", new Hobby());
		mv.setStatus(HttpStatus.CREATED);
		
		return mv;
	}
	
	@DeleteMapping("/hobby/{hobbyId}")
	public ModelAndView deleteHobby(@PathVariable("hobbyId") Integer hobbyId) {
		hobbyMgmtService.deleteHobby(hobbyId);
		
		List<Hobby> hobbies = hobbyMgmtService.getHobbies();
		ModelAndView mv = new ModelAndView("/admin/hobbyMgmt");
		mv.addObject("allHobbies", hobbies);
		mv.addObject("hobby", new Hobby());
		mv.setStatus(HttpStatus.OK);
		
		return mv;
	}
	
	@GetMapping("/department")
	public ModelAndView getDepartmentMgmt() {
		List<Department> departments = departmentMgmtService.getAllDepartment();
		
		ModelAndView mv = new ModelAndView("/admin/departmentMgmt");
		mv.addObject("allDepartment", departments);
		mv.addObject("department", new Department());
		mv.setStatus(HttpStatus.OK);
		
		return mv;
	}
	
	@PostMapping("/department")
	public ModelAndView addDepartment(@ModelAttribute Department department) {
		departmentMgmtService.addDepartment(department);
		
		List<Department> departments = departmentMgmtService.getAllDepartment();
		
		ModelAndView mv = new ModelAndView("/admin/departmentMgmt");
		mv.addObject("allDepartment", departments);
		mv.addObject("department", new Department());
		mv.setStatus(HttpStatus.OK);
		
		return mv;
	}
	
	@DeleteMapping("/department/{departmentId}")
	public ModelAndView deleteDepartment(@PathVariable Integer departmentId) {
		departmentMgmtService.delDepartment(departmentId);
		
		List<Department> departments = departmentMgmtService.getAllDepartment();
		
		ModelAndView mv = new ModelAndView("/admin/departmentMgmt");
		mv.addObject("allDepartment", departments);
		mv.addObject("department", new Department());
		mv.setStatus(HttpStatus.OK);
		
		return mv;
	}


}
