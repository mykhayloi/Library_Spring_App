package com.javatpoint.spring_boot_example_sts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.spring5.processor.SpringInputGeneralFieldTagProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * Hello world!
 *
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

@Controller
public class App
{
	
	private String id ="";
	private String author ="";
	private String title ="";
	private String genre ="";
	private double price =0.0;
	private String date_published ="";
	private String description ="";
	
	ArrayList<Book> library = new ArrayList<Book>();
	ArrayList<Book> filteredList = new ArrayList<Book>();
	ArrayList<Book> finalList = new ArrayList<Book>();
	
	
	@GetMapping("/")
    public String index(Model model)
    {
		model.addAttribute("user", new User());
		finalList.clear();
		return "index";
    }
	
	@PostMapping("/save")
	public String userUpdate(@ModelAttribute User user, Model model)
	{
		finalList.clear();
		model.addAttribute("user", user);

		setPrice(user.getMin(),user.getMax());
		if(user.getGenre()!=null)
		{
			setGenre(user.getGenre());
		}
		if(user.getTitle()!=null&&!user.getTitle().isEmpty())
		{
			setTitle(user.getTitle());
		}
		if(user.getAuthor()!=null&&!user.getAuthor().isEmpty())
		{
			setAuthor(user.getAuthor());
		}
		if(user.getAvail())
		{
			filterTakenBooks();
		}
		setStatus(user.getName(),user.getChoice());
		user.setChoice("");
	
		return "save";
	}
	
	
	@RequestMapping("/save")
	public String save()
	{
		return "save";
	}
	

	public ArrayList<Book> setPrice(double min, double max)
	{
		filteredList = xmlParse();
		for(int i=filteredList.size()-1;i>=0;i--)			
		{
			if(filteredList.get(i).getPrice()>max || filteredList.get(i).getPrice()<min)
			{	
			}
			else
			{
				finalList.add(filteredList.get(i));
			}
		}
		return filteredList;
	}
	
	public void setGenre(String genre)
	{
		for(int i=finalList.size()-1; i>=0;i--)
		{

			if(!finalList.get(i).getGenre().equalsIgnoreCase(genre))
			{

				finalList.remove(i);
			}

		}
		finalList.toString();
	}
	
	
	public void setTitle(String title)
	{
		for(int i=finalList.size()-1; i>=0;i--)
		{

			if(!finalList.get(i).getTitle().equalsIgnoreCase(title))
			{

				finalList.remove(i);
			}

		}

	}
	
	public void setAuthor(String author)
	{

		for(int i=finalList.size()-1; i>=0;i--)
		{

			if(!finalList.get(i).getAuthor().equalsIgnoreCase(author))
			{

				finalList.remove(i);
			}

		}
	}
	
	
	public void setStatus(String user, String book)
	{
		for(int i=0;i<finalList.size();i++)
		{
			
			String f = finalList.get(i).getId();

			if(f.equalsIgnoreCase(book))
			{

				finalList.get(i).setStatus(user);
			}
		}
	}
	
	public void filterTakenBooks()
	{
		for(int i=finalList.size()-1; i>=0;i--)
		{

			if(!finalList.get(i).getStatus().equalsIgnoreCase("Available"))
			{

				finalList.remove(i);
			}

		}
	}
	
	
	
	@RequestMapping("/filter")
	public String filter(@ModelAttribute User user)
	{
		user.getGenre();
		return "save";
	}
	
	@ModelAttribute("books")
	public ArrayList<Book> finalList()
	{

		return finalList;
	}

	
	//@ModelAttribute("books")
	public ArrayList<Book> xmlParse()
	{
		
		if(library.isEmpty())
		{
        try {
        	
        	File inputFile = new File("resources/books.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(inputFile);
        	doc.getDocumentElement().normalize();

        	NodeList nList = doc.getElementsByTagName("book");

        	
        	for(int temp=0; temp<nList.getLength(); temp++)
        	{
        		Node nNode = nList.item(temp);
        		
        		if(nNode.getNodeType() == Node.ELEMENT_NODE)
        		{
        			Element eElement = (Element) nNode;
        			
        			id = eElement.getAttribute("id");
        			author = eElement.getElementsByTagName("author").item(0).getTextContent();
        			title = eElement.getElementsByTagName("title").item(0).getTextContent();
        			genre = eElement.getElementsByTagName("genre").item(0).getTextContent();
        			price = Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent());
        			date_published = eElement.getElementsByTagName("publish_date").item(0).getTextContent();
        			description = eElement.getElementsByTagName("description").item(0).getTextContent();
        			Book book = new Book(id,author, title,genre,price,date_published, description);
        			library.add(book);
        		}
        		
        		
        	}
        	
        } catch(Exception e) {
        	e.printStackTrace();
        }
		}
		else
		{
        
		}
		return  library;
	}
	
	
	
}
