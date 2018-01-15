package cn.krim.gp.front.model.questions;

import lombok.Data;

/**
 * 答案使用图片保存的
 * @author krim
 *
 */
@Data
public class PictureQuestion{
	/**主键*/
	protected Integer questionId;
	/**类型*/
	
	private Integer type;
	/**科目id*/
	private Integer subjectId;
	/**题目内容*/
	
	protected String questionContent;
	/**答案保存路径*/
	
	protected String answer_address;
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
