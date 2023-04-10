package kr.datasolution.kdigital2023.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.datasolution.kdigital2023.demo.mapper.HobbiesMapper;
import kr.datasolution.kdigital2023.demo.model.Hobby;

@Service
public class HobbyMgmtService {
	private final HobbiesMapper hobbiesMapper;

	HobbyMgmtService(HobbiesMapper hobbiesRepository) {
		this.hobbiesMapper = hobbiesRepository;
	}

	public List<Hobby> getHobbies() {
		return hobbiesMapper.findAll();
	}

	public Integer addHobby(Hobby hobby) {
		return hobbiesMapper.save(hobby.getHobby());
	}

	public Integer deleteHobby(Integer hobbyId) {
		return hobbiesMapper.remove(hobbyId);
	}
}
