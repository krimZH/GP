package cn.krim.gp.core.CRUDController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.krim.gp.core.modle.ReturnData;
import cn.krim.gp.core.repository.CommonQuestionRepository;
import cn.krim.gp.core.repository.PictureQuestionRepository;
import cn.krim.gp.core.repository.UserRespository;
import cn.krim.gp.entities.questions.CommonQuestion;
import cn.krim.gp.entities.questions.PictureQuestion;
import cn.krim.gp.entities.users.User;

@RestController("/CRUD")
public class CRUDController {
	@Autowired UserRespository	userRespository;
	@Autowired CommonQuestionRepository commonQuestionRepository;
	@Autowired PictureQuestionRepository pictureQuestionRepository;
	
	@RequestMapping(name="/create",method=RequestMethod.POST)
	public <S> ReturnData create(S s){
		try {
			Object data;
			if(s instanceof CommonQuestion){
				data =commonQuestionRepository.save((CommonQuestion)s);
			}else if(s instanceof PictureQuestion){
				data =pictureQuestionRepository.save((PictureQuestion)s);
			}else if(s instanceof User){
				data =userRespository.save((User)s);
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	@RequestMapping(name="/delete",method=RequestMethod.DELETE)
	public void delete(User user){
		userRespository.delete(user);
	}
}
