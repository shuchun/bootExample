package com.example.mvcdemo.base.baseClass;



public class FileWrapper {

	/**
	 * 文件二进制
	 */
	private byte [] byteFile;  //传入的文件二进制
	
	/**
	 * 目标文件地址
	 */
	private String targetFilePath;    //目标文件地址 
	

	public byte[] getByteFile() {
		return byteFile;
	}

	public void setByteFile(byte[] byteFile) {
		this.byteFile = byteFile;
	}

	public String getTargetFilePath() {
		return targetFilePath;
	}

	public void setTargetFilePath(String targetFilePath) {
		this.targetFilePath = targetFilePath;
	}

	
}
