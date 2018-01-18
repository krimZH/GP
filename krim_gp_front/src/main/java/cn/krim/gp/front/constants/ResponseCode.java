package cn.krim.gp.front.constants;

public class ResponseCode {
	/***/
	public static final Integer SUCCESS_CODE=200;
	/**邮件发送错误*/
	public static final Integer MAIL_SEND_ERROR=700;
	/**方法调用错误*/
	public static final Integer METHOD_INVOKED_ERROR=701;
	/**验证错误*/
	public static final Integer AUTH_FAILED_ERROR=702;
	/**权限错误*/
	public static final Integer SECURITY_REFUSE_ERROR=703;
	/**数据库写入错误*/
	public static final Integer SQL_ERROR=704;
}
