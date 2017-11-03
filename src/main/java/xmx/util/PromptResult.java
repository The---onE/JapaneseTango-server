package xmx.util;

public class PromptResult extends Result {
	private int status;
	private String prompt;

	public int getStatus() {
		return status;
	}

	@Override
	public PromptResult setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getPrompt() {
		return prompt;
	}

	@Override
	public PromptResult setPrompt(String prompt) {
		this.prompt = prompt;
		return this;
	}
}
