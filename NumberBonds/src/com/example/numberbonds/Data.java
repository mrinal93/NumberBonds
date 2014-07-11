package com.example.numberbonds;

public class Data {
	public String name;
	public int qno,attempts, right, wrong;
	
	public Data(String name, int qno, int attempts, int wrong, int right)
	{
		this.name = name;
		this.qno = qno;
		this.attempts = attempts;
		this.wrong = wrong;
		this.right = right;
	}
	public Data()
	{
	}
}
