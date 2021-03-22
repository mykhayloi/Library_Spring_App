package com.javatpoint.spring_boot_example_sts.controller;

public class Book {
	private String id;
	private String author;
	private String title;
	private String genre;
	private double price;
	private String publish_date;
	private String description;
	private String status;
	
	public Book() {
		super();
	}

	public Book(String id,String author,String title, String genre, double price, String publish_date, String description)
	{
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.price = price;
		this.publish_date = publish_date;
		this.description = description;
		this.status = "Available";
	}
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getGenre()
	{
		return genre;
	}
	public void setGenre(String genre)
	{
		this.genre = genre;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public String getPublishDate()
	{
		return publish_date;
	}
	public void setPublishDate(String publish_date)
	{
		this.publish_date = publish_date;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}

}
