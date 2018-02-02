package cn.zynworld.hnister.common.utils;

/**
 * @auther Buynow Zhao
 * @create 2018/1/7
 * HTTP响应结果
 */
public class ResultBean<T> {
	private boolean isSuccess = false;
	private int status = 400;
	private T msg;

	public boolean isSuccess() {
		return isSuccess;
	}

	public ResultBean setSuccess(boolean success) {
		isSuccess = success;
		return this;
	}

	public int getstatus() {
		return status;
	}

	public ResultBean setstatus(int status) {
		this.status = status;
		return this;
	}

	public T getMsg() {
		return msg;
	}

	public ResultBean setMsg(T msg) {
		this.msg = msg;
		return this;
	}

	public static <T> ResultBean create(boolean isSuccess, int status, T msg){
		return new ResultBean<T>().setSuccess(isSuccess).setstatus(status).setMsg(msg);
	}
	public static <T> ResultBean create(boolean isSuccess){
		if (isSuccess){
			return ResultBean.success();
		}
		return ResultBean.fail();
	}

	public static <T> ResultBean success(T msg){
		return new ResultBean<T>().setSuccess(true).setstatus(200).setMsg(msg);
	}
	public static <T> ResultBean success(){
		return ResultBean.success(null);
	}
	public static <T> ResultBean fail(T msg){
		return new ResultBean<T>().setSuccess(false).setstatus(400).setMsg(msg);
	}
	public static <T> ResultBean fail(){
		return ResultBean.fail(null);
	}



}
