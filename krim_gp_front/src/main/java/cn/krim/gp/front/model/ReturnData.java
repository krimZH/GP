package cn.krim.gp.front.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * 响应的数据
 * @author krim
 *
 */
@Data
@Builder
public class ReturnData implements Serializable{


	private static final long serialVersionUID = 3297290853515315722L;

	//数据体
	private Object data;

	//附加消息文本
	private String msg;
	
	//状态码
	private Integer code;
	
		
}
