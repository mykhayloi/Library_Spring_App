package com.javatpoint.spring_boot_example_sts.controller;



public class User {

	private String name;
	private String genre;
	private String title;
	private String author;
	private Double min = 0.0;
	private Double max = 10000.0;
	private String choice;
	private boolean avail;
	
	
	public String getName()
	{
		return name;
	}
	public void setName(String i)
	{
		name = i;
	}
	public String getGenre()
	{
		return genre;
	}
	public void setGenre(String genre)
	{
		this.genre = genre;
	}
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public double getMin()
	{
		return min;
	}
	public void setMin(double min)
	{
		this.min = min;
	}
	public double getMax()
	{
		return max;
	}
	public void setMax(double max)
	{
		this.max = max;
	}
	public String getChoice()
	{
		return choice;
	}
	public void setChoice(String choice)
	{
		this.choice=choice;
	}
	
	public boolean getAvail()
	{
		return avail;
	}
	public void setAvail(boolean avail)
	{
		this.avail = avail;
	}
}
