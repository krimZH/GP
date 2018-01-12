package cn.krim.gp.front.model.questions;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 答案使用字符串保存的
 * @author krim
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommonQuestion {
	/**主键*/
	protected Integer questionId;
	/**类型*/
	private Integer type;
	/**题目内容*/
	
	protected String questionContent;
	/**答案*/
	
	protected String answer;
	/**答案解析*/
	
	protected String analysis;
	/**是否为核心题目：0=不是，1=是*/
	
	protected Integer core;
	/**题目被使用次数*/
	
	protected Integer questedCount;
	/**正确率*/
	
	protected Double correctRate;
	/**创建时间*/
	
	protected Long createTime;
	/**修改时间*/
	
	protected Long updateTime;
}
