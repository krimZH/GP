package cn.krim.gp.front.micService;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("krim-gp-core")
public interface CrudService {
	@RequestMapping(value="/core/findById",method=RequestMethod.POST)
	public Object findById(@RequestBody Map<Object, Object> fieldMap,@RequestParam("entityPath") String entityPath,@RequestParam("entity")String entity);
}
