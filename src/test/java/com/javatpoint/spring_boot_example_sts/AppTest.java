package com.javatpoint.spring_boot_example_sts;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.javatpoint.spring_boot_example_sts.controller.Book;
import com.javatpoint.spring_boot_example_sts.controller.User;

/**
 * Unit test for simple App.
 */
public class AppTest 
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
	
	User user = new User();
    
    @Test
    public void setGenreTest()
    {
    	user.setGenre("Fantasy");
    	listToFinal();
    	setGenre(user.getGenre());

    	for(int i=0;i<finalList.size();i++)
    	{
 
    		if(!finalList.get(i).getGenre().equals("Fantasy"))
    		{
    			System.out.println("false");
    			assertTrue(false);
    		}
		}
    }
    
    @Test
    public void setTitleTest()
    {
    	user.setTitle("Splish Splash");
    	listToFinal();
    	setTitle(user.getTitle());

    	for(int i=0;i<finalList.size();i++)
    	{
    		if(!finalList.get(i).getTitle().equals("Splish Splash"))
    		{
    			System.out.println("false");
    			assertTrue(false);
    		}
		}
    }
    
    @Test
    public void setAuthorTest()
    {
    	user.setAuthor("Gambardella, Matthew");
    	listToFinal();
    	setAuthor(user.getAuthor());

    	for(int i=0;i<finalList.size();i++)
    	{

    		if(!finalList.get(i).getAuthor().equals("Gambardella, Matthew"))
    		{
    			System.out.println("false");
    			assertTrue(false);
    		}
		}
    }
    
    @Test
    public void setPriceTest()
    {
    	user.setMin(5);
    	user.setMax(15);
    	listToFinal();
    	setPrice(user.getMin(),user.getMax());

    	for(int i=0;i<finalList.size();i++)
    	{

    		if(finalList.get(i).getPrice()<5 && finalList.get(i).getPrice()>10)
    		{
    			System.out.println("false");
    			assertTrue(false);
    		}
		}
    }
    
    
    
    
    
    public void listToFinal()
    {
    	ArrayList<Book> testBook = new ArrayList<Book>();
    	testBook = xmlParse();
    	for(int i=0;i<testBook.size();i++)
    	{
    		finalList.add(testBook.get(i));
    	}

    }
    
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
	
	public ArrayList<Book> finalList()
	{

		return finalList;
	}
}
