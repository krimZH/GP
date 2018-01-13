package cn.krim.gp.front.micService;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.krim.gp.front.model.ReturnData;

@Component
@FeignClient("krim-gp-core")
public interface CrudService {
	
	@RequestMapping(value="/core/findByConditions",method=RequestMethod.POST)
	public ReturnData findByContitions(@RequestBody Map<String, Object[]> fieldMap,@RequestParam("entityPath")String path,@RequestParam("entity")String entity);
}
