import java.io.*;
import java.util.*;

public class AssassinManager {
    // YOUR CODE GOES HERE
	AssassinNode alive;
	AssassinNode dead; 
	
	
	 LinkedList<AssassinNode> killRing  = new LinkedList<>();
	 LinkedList<AssassinNode> graveyard = new LinkedList<>();
	 int countdead=0;
	 String lastname = null;
	 
	 
	public AssassinManager (List <String> names)  {
		
		
		if (names == null || names.size() == 0) {
			System.out.println("Illegal Argument Exception");
			System.exit(1);			
		}
		else
		{
			  AssassinNode  head = null;  
			  AssassinNode  tail = null;  
			    
			    for (int i=0; i < names.size()-1; i++)
			    {
			        AssassinNode alive = new AssassinNode(names.get(i));  
			        killRing.add(alive);
			               
			        //If list is empty set head and tail to new node  
			        if(head == null) {  
			            head = alive;
			            tail = alive; 
			        }  
			        else {  
			            // add new Node after the tail
			            tail.next = alive;  
			            //new Node is now the tail  
			            tail = alive;  
			   
			        }  
			    }  
			    alive = killRing.getFirst();
		
			    dead = null;  
			    if (graveyard.isEmpty())
			    {
			    	dead = null;
			    }
			    else
			    {
			    	dead = graveyard.getFirst();
			    }
		}	   
			
		
	}
	
	public String killRing() {
		
		String a = "";
		AssassinNode c = alive;
	
		if (killRing.size() == 1)
		{
			if (!isGameOver())
				System.out.println("Game was won by "+killRing.get(0).name);
			winner();
		}
	
		for (int i=0; i < killRing.size()-1;i++)
		{
			c=killRing.get(i);
			if (c.killer == null)
			{
			System.out.println ("  " + c.name + " is stalking " + c.next.name + ".");
			}
		
			
		}
		c=killRing.get(killRing.size()-1);
		if (c == killRing.getLast()) 
		{
			System.out.println ("  " + killRing.getLast().name + " is stalking " + killRing.getFirst().name + ".");
			
		}
		return a;
		
	}

	
	public String graveyard() {
		String a = "";
	
		AssassinNode d = dead;
		for (int j=0; j < graveyard.size(); j++)
		{
			d=graveyard.get(j);
	
			if (d.killer != null)
			{
				System.out.println(""+"  " + d.name + " was killed by " + d.killer + ".");
			}
		
		}
		return a;
	}
	
	
	
	public boolean killRingContains(String name) { 
		AssassinNode c = alive;
	
		while (c != null)
		{
			if (c.name .equalsIgnoreCase(name))
			{
				return true;
			}
			c=c.next;
		}
		return false;
	}

	
	public boolean graveyardContains(String name) { 
		AssassinNode d = dead;
		while (d != null) {
						
		if (d.killer != null)
		{
		
			if (d.name .equalsIgnoreCase(name))
			{
				return true;
			}
		}
			d=d.next;
		}
		
		return false;
	}

	public boolean isGameOver() {
		String a = "";
		AssassinNode c = alive;
		if (c!= null && c.next == null)
	//	if (killRing.size() == 1)
		{
			return true;
		}
		return false;
		}
	
	public String winner()
	{
		String a = "";
		AssassinNode c = alive;
		while (c!=null)
		{
			if (c.next == null)
				return c.name;
		}
		return null;
		
	}
	

 	
 	
	public void kill(String name)
	{
		AssassinNode c = alive;
		AssassinNode d = dead;
		
	    AssassinNode prev = null;
	    
	    AssassinNode  headk= killRing.getFirst();
	    AssassinNode tailk=  killRing.getLast();   
	    String prevname="";
	    
	    int k=0;
	    boolean foundname = false;
	 
		while (c!=null && foundname != true)
		{
			{
		
			if (c.name .equalsIgnoreCase ( name))
			{	
				foundname = true;
			
				
				if (c .equals   ( headk))
				{
				
					lastname =   killRing.getLast().name; 
					c.killer = lastname;
				
					graveyard.addFirst((c));
					dead = graveyard.getFirst();

				//	killRing.getLast().next = c.next;      
					
					killRing.remove(c);
				
			    }
				else
				{
					c.killer = prevname;  
										
					graveyard.addFirst((c));
					dead = graveyard.getFirst();

					prev.next=c.next;
					killRing.remove(c);
				
				}
				
			}
			else
			{	
				prevname = c.name;
				prev=c;
				
				c=c.next;
				
			}
			
		
		}
		}
		
	}
	
}

