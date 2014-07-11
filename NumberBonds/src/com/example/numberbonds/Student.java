package com.example.numberbonds;

public class Student {
	
	String name;
	String date;
	String level;
	int id;
	String question1;
	String question2;
	String question3;
	String question4;
	String question5;
	String question6;
	String question7;
	String question8;
	String question9;
	String question10;
	String mistakes;
	
	public Student(String name,	String date,String level, int id,String question1,	String question2,	String question3,
	String question4,	String question5,	String question6,	String question7,	String question8,	
	String question9,	String question10, String mistakes)
	{
		this.name = name;
		this.date =date;
		this.level=level;
		this.id = id;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
		this.question5 = question5;
		this.question6 = question6;
		this.question7 = question7;
		this.question8 = question8;
		this.question9 = question9;
		this.question10 = question10;
		this.mistakes = mistakes;
	}

	public Student(Student student) {
		this.name = student.name;
		this.date = student.date;
		this.level=student.level;
		this.question1 = student.question1;
		this.question2 = student.question2;
		this.question3 = student.question3;
		this.question4 = student.question4;
		this.question5 = student.question5;
		this.question6 = student.question6;
		this.question7 = student.question7;
		this.question8 = student.question8;
		this.question9 = student.question9;
		this.question10 = student.question10;
		this.mistakes =student.mistakes;
	}
}
