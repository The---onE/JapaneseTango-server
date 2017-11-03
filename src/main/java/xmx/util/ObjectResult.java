package xmx.util;

public class ObjectResult<T> extends Result {
	private int status;
	private String prompt;
	private T object;

	public int getStatus() {
		return status;
	}

	@Override
	public ObjectResult<T> setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getPrompt() {
		return prompt;
	}

	@Override
	public ObjectResult<T> setPrompt(String prompt) {
		this.prompt = prompt;
		return this;
	}

	public T getObject() {
		return object;
	}

	public ObjectResult<T> setObject(T object) {
		this.object = object;
		return this;
	}
}
