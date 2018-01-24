package cn.zynworld.hnister.common.utils;

/**
 * @auther Buynow Zhao
 * @create 2018/1/7
 * HTTP响应结果
 */
public class ResultBean {
	private boolean isSuccess = false;
	private int stauts = 400;
	private Object msg;

	public boolean isSuccess() {
		return isSuccess;
	}

	public ResultBean setSuccess(boolean success) {
		isSuccess = success;
		return this;
	}

	public int getStauts() {
		return stauts;
	}

	public ResultBean setStauts(int stauts) {
		this.stauts = stauts;
		return this;
	}

	public Object getMsg() {
		return msg;
	}

	public ResultBean setMsg(Object msg) {
		this.msg = msg;
		return this;
	}

	public static ResultBean create(boolean isSuccess, int stauts, Object msg){
		return new ResultBean().setSuccess(isSuccess).setStauts(stauts).setMsg(msg);
	}
	public static ResultBean create(boolean isSuccess){
		if (isSuccess){
			return ResultBean.success();
		}
		return ResultBean.fail();
	}

	public static ResultBean success(Object msg){
		return new ResultBean().setSuccess(true).setStauts(200).setMsg(msg);
	}
	public static ResultBean success(){
		return ResultBean.success(null);
	}
	public static ResultBean fail(Object msg){
		return new ResultBean().setSuccess(false).setStauts(400).setMsg(msg);
	}
	public static ResultBean fail(){
		return ResultBean.fail(null);
	}



}
