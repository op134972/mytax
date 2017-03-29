package cn.ustb.core.exception;

public class ServiceException extends SysException {

	public ServiceException() {
		super("业务操作失败");
	}

	public ServiceException(String message) {
		super(message);
	}
	
}
