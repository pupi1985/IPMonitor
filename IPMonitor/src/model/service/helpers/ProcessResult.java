package model.service.helpers;

public class ProcessResult {

	private String output;
	private int exitCode;

	public ProcessResult(String output, int exitCode) {
		this.output = output;
		this.exitCode = exitCode;
	}

	public String getOutput() {
		return output;
	}

	public int getExitCode() {
		return exitCode;
	}

}
