package cn.krim.gp.front.model.http;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;
/**
 * 邮件模板
 * @author krim
 *
 */
@Data
public class MailTemplate {
	public static final String SIMPLE_TEMPLATE="您有新的任务，请注意查收。来自%s";
	/**内容*/
	@NotNull @Valid
	private String content;
	/**发布者*/
	@NotNull @Valid
	private String publisher;
	/**接收者*/
	@NotNull @Valid
	private String accepter;
	/**标题*/
	@NotNull @Valid
	private String title;
}
