package system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RemoteControl {
	String interval="00:15";
	File createfile = new File("create.txt");
	File assignfile = new File("assign.txt");
	File recordfile = new File("record.txt");
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	final String[] trainlist={"","train1","train2","train3","train4","train5","train6","train7"};
	final String[] driverlist={"","driver1","driver2","driver3","driver4","driver5","driver6","driver7"};
	
	public String readTimetable(){				 
	    String temp = null;
	    String timetableContent="       Route  Journey    StopA            StopB            StopC             StopD\r\n\r\n";
	    int line=1;
	    
	    try{
	    	BufferedReader reader = new BufferedReader(new FileReader(createfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {	    	
		    	timetableContent=timetableContent+line+" :  "+temp+"\r\n\r\n";
		        line++;		            
		     }		  		   
		    reader.close();
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }		
		return timetableContent;		
	}
	
	public boolean createRoute(String route,String journey,String stopa,String stopb,String stopc,String stopd,String timea,String timeb,String timec,String timed){
		boolean flag=true;
	    try{		    	
	    	        
	        if(stopa.equals("")||timea.equals("")){
	        	stopa="*";
	        	timea="*";
	        }
	        if(stopb.equals("")||timeb.equals("")){
	        	stopb="*";
	        	timeb="*";
	        }
	        if(stopc.equals("")||timec.equals("")){
	        	stopc="*";
	        	timec="*";
	        }
	        if(stopd.equals("")||timed.equals("")){
	        	stopd="*";
	        	timed="*";
	        }
	        if(route.equals("")||journey.equals(""))
	        	flag=false;
	        if(flag){
	        	FileWriter fileWriter = new FileWriter("create.txt",true);
	        	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        	String lineinfo = route+" "+journey+" A-"+stopa+"-"+timea+" B-"+stopb+"-"+timeb+" C-"+stopc+"-"+timec+" D-"+stopd+"-"+timed;
	        	bufferedWriter.append(lineinfo).append("\r\n");			        
	        	bufferedWriter.close();
	        	fileWriter.close();
	        }	        
	        
	    }catch(Exception ex){
	    	flag=false;
	    	ex.printStackTrace();
	    }	
		return flag;
	}
	
	public boolean changeRoute(String route,String journey){
		String temp;
		boolean flag=false;	
		
		if(route.equals(""))
        	route="*";
        if(journey.equals(""))
        	journey="*";
		try{
	    	BufferedReader reader = new BufferedReader(new FileReader(createfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {
		    	String[] split=temp.split(" ");
		    	if(split[0].equals(route)&&split[1].equals(journey)){
		    		flag=true;
		    		break;
		    	}
		     }		  		   
		    reader.close();
		    //if it is assigned,it can not be changed
		    BufferedReader reader1 = new BufferedReader(new FileReader(assignfile.getAbsolutePath()));		    	
		    while ((temp = reader1.readLine()) != null) {
		    	String[] split=temp.split(" ");
		    	if(split[0].equals(route)&&split[1].equals(journey)){
		    		flag=false;
		    		break;
		    	}
		     }		  		   
		    reader1.close();
		    
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }

		return flag;
	}
	public boolean saveChangeRoute(String route,String journey,String stopa,String stopb,String stopc,String stopd,String timea,String timeb,String timec,String timed){
		boolean flag=true;
		String temp;
	    try{		    	
	    	BufferedReader reader = new BufferedReader(new FileReader(createfile.getAbsolutePath()));       
	        StringBuffer bf = new StringBuffer();
	        
	        while ((temp = reader.readLine()) != null) {		            	           
	            temp=temp.trim();
	            String[] split=temp.split(" ");
		    	if(!(split[0].equals(route)&&split[1].equals(journey))){          	
					bf.append(temp).append("\r\n");	
				}
	            
	        }
	        reader.close();
        
	        if(stopa.equals("")||timea.equals("")){
	        	stopa="*";
	        	timea="*";
	        }
	        if(stopb.equals("")||timeb.equals("")){
	        	stopb="*";
	        	timeb="*";
	        }
	        if(stopc.equals("")||timec.equals("")){
	        	stopc="*";
	        	timec="*";
	        }
	        if(stopd.equals("")||timed.equals("")){
	        	stopd="*";
	        	timed="*";
	        }
	        FileWriter fileWriter = new FileWriter("create.txt");
		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);		        		  
		    bufferedWriter.write(bf.toString());	        
	        String lineinfo = route+" "+journey+" A-"+stopa+"-"+timea+" B-"+stopb+"-"+timeb+" C-"+stopc+"-"+timec+" D-"+stopd+"-"+timed;
	        bufferedWriter.append(lineinfo).append("\r\n");	
	        bufferedWriter.close();
	        fileWriter.close();
	    }catch(Exception ex){
	    	flag=false;
	    	ex.printStackTrace();
	    }	
		
		return flag;
	}
	
	public boolean deleteRoute(String route,String journey){
		String temp="";
		boolean flag=false;	
		
		if(route.equals(""))
        	route="*";
        if(journey.equals(""))
        	journey="*";
		try{
	    	BufferedReader reader = new BufferedReader(new FileReader(createfile.getAbsolutePath()));		    		    	        
	    	while ((temp = reader.readLine()) != null) {
		    	String[] split=temp.split(" ");
		    	if(split[0].equals(route)&&split[1].equals(journey)){
		    		flag=true;
		    		break;
		    	}
		     }
	    	reader.close();
	    	  	
	    	if(flag){
	    		BufferedReader reader1 = new BufferedReader(new FileReader(createfile.getAbsolutePath()));
		    	StringBuffer bf = new StringBuffer();
	    		while ((temp = reader1.readLine()) != null) {		            	           
	 	            temp=temp.trim();
	 	            String[] split=temp.split(" ");
	 		    	if(!(split[0].equals(route)&&split[1].equals(journey))){          	
	 					bf.append(temp).append("\r\n");	
	 				}	 	            
	 	        }	    			    		
			    reader1.close();
			    FileWriter fileWriter = new FileWriter("create.txt");
			    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);		        		  
			    bufferedWriter.write(bf.toString());
			    bufferedWriter.close();
			    fileWriter.close();
	    	}
		    
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }	

		return flag;
	}
	
	public boolean assignTrainDriver(String route,String journey,String train,String driver){
		boolean flag=true;
		String temp="";
		if(train.equals("")||driver.equals(""))
			flag=false;
		if(flag){
			try{
				BufferedReader reader = new BufferedReader(new FileReader(assignfile.getAbsolutePath()));		    		    	        
		    	while ((temp = reader.readLine()) != null) {
		    		String[] split=temp.split(" ");
			    	if(split[6].equals(train)||split[7].equals(driver)){
			    		flag=false;
			    		break;		    		
			    	}
			    	else if(split[0].equals(route)&&split[1].equals(journey)){
			    		flag=false;
			    		break;
			    	}
			    		
			     }
		    	reader.close();
		    	  	
		    	if(flag){
		    		flag=false;
		    		String[] split = null;
		    		BufferedReader reader1 = new BufferedReader(new FileReader(createfile.getAbsolutePath()));
		    		while ((temp = reader1.readLine()) != null) {		            	           
		    			split=temp.split(" ");
				    	if(split[0].equals(route)&&split[1].equals(journey)){
				    		flag=true;
				    		break;
				    	}	 	            
		 	        }	    			    		
				    //if route journey is ok and driver train are not in use
				    if(flag){
				    	FileWriter fileWriter = new FileWriter("assign.txt",true);
					    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);		  	        
				        String lineinfo = split[0]+" "+split[1]+" "+split[2]+" "+split[3]+" "+split[4]+" "+split[5]+" "+train+" "+driver;
				        bufferedWriter.append(lineinfo).append("\r\n");	
				        bufferedWriter.close();
				        fileWriter.close();
				        
				        FileWriter fileWriter1 = new FileWriter("record.txt",true);
					    BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);		  	        
				        String lineinfo1 = "Route:"+split[0]+"   Journey:"+split[1]+"   Train:"+train+"   Driver:"+driver;
				        bufferedWriter1.append(lineinfo1).append("\r\n");	
				        bufferedWriter1.close();
				        fileWriter1.close();
				    }
				    reader1.close();
		    	}
			    
		    }catch(Exception ex){
		    	ex.printStackTrace();
		    }
		}
		return flag;
	}
	
	public String busytrain(String[] trainlist){
		String busytrainContent="";
		String temp = null;
	    try{
	    	BufferedReader reader = new BufferedReader(new FileReader(assignfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {	    	
		    	String[] split= temp.split(" ");
		    	busytrainContent=busytrainContent+split[6]+"   ";
		     }		  		   
		    reader.close();
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    if(busytrainContent.equals(""))
	    	busytrainContent="Trains are all free";
		return busytrainContent;
	}
	public String freetrain(String[] trainlist){
		String freetrainContent="",busytrainContent="";
		String temp = null;
	    try{
	    	BufferedReader reader = new BufferedReader(new FileReader(assignfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {	    	
		    	String[] split= temp.split(" ");
		    	busytrainContent=busytrainContent+split[6]+" ";
		     }		  		   
		    reader.close();
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    String[] busytrain = busytrainContent.split(" ");
	    for(int i=0;i<trainlist.length;i++){
	    	boolean flag=false;
	    	for(int j=0;j<busytrain.length;j++){
	    		if(busytrain[j].equals(trainlist[i])){
	    			flag=true;
	    			break;
	    		}
	    	}
	    	if(!flag){
	    		freetrainContent=freetrainContent+trainlist[i]+"   ";
	    	}
	    }
	    if(freetrainContent.equals("   "))
	    	freetrainContent="Trains are all on the journey";
	    
		return freetrainContent;
	}
	public String busydriver(String[] driverlist){
		String busydriverContent="";
		String temp = null;
	    try{
	    	BufferedReader reader = new BufferedReader(new FileReader(assignfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {	    	
		    	String[] split= temp.split(" ");
		    	busydriverContent=busydriverContent+split[7]+"   ";
		     }		  		   
		    reader.close();
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    if(busydriverContent.equals(""))
	    	busydriverContent="Drivers are free";
		return busydriverContent;
	}
	public String freedriver(String[] driverlist){
		String freedriverContent="",busydriverContent="";
		String temp = null;
	    try{
	    	BufferedReader reader = new BufferedReader(new FileReader(assignfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {	    	
		    	String[] split= temp.split(" ");
		    	busydriverContent=busydriverContent+split[7]+" ";
		     }		  		   
		    reader.close();
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    String[] busytrain = busydriverContent.split(" ");
	    for(int i=0;i<driverlist.length;i++){
	    	boolean flag=false;
	    	for(int j=0;j<busytrain.length;j++){
	    		if(busytrain[j].equals(driverlist[i])){
	    			flag=true;
	    			break;
	    		}
	    	}
	    	if(!flag){
	    		freedriverContent=freedriverContent+driverlist[i]+"   ";
	    	}
	    }
	    if(freedriverContent.equals("   "))
	    	freedriverContent="Drivers are all on the journey";
		return freedriverContent;
	}
	
	public String readRecord(){
		String record="";
		String temp = null;
	    int line=1;	    
	    try{
	    	BufferedReader reader = new BufferedReader(new FileReader(recordfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {	    	
		    	record=record+line+" :  "+temp+"\r\n\r\n";
		        line++;		            
		     }		  		   
		    reader.close();
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
		return record;
	}
}