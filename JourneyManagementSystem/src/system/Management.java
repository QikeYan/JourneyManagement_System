package system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Management {
	SimpleDateFormat df = new SimpleDateFormat("HH:mm");
	File runningtrainfile = new File("runningtrain.txt");
	File assignfile = new File("assign.txt");
	File pausefile = new File("pause.txt");
	final String[] trainlist={"","train1","train2","train3","train4","train5","train6","train7"};
	
	public String readRunningTrain(){
		 String temp = null;
		 String runningTrainContent="*********No. : Status  Route  Journey  STOPA  STOPB  STOPC  STOPD  Train  Driver*********\r\n\r\n";
		    int line=1;
		    
		    try{
		    	BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
			    while ((temp = reader.readLine()) != null) {	    	
			    	runningTrainContent=runningTrainContent+line+" :  "+temp+"\r\n\r\n";
			        line++;		            
			     }		  		   
			    reader.close();
		    }catch(Exception ex){
		    	ex.printStackTrace();
		    }				
		return runningTrainContent;
	}
	
	public boolean isRunning(String train){
		boolean flag=false;
		String temp="";
		if(train.equals(""))
        	train="*";
		try{
	    	BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {
		    	String[] split=temp.split(" ");
		    	if(split[7].equals(train)){
		    		flag=true;
		    		break;
		    	}
		     }		  		   
		    reader.close();
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }		
		return flag;
	}
	
	public boolean stopTrain(String train){
		boolean flag=false;
		String temp="",lineinfo;
		String[] split=null;
		try{
	    	BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {
		    	split=temp.split(" ");
		    	if(split[7].equals(train)){
		    		flag=true;
		    		break;
		    	}
		     }		  		   
		    reader.close();
		    if(flag){
		    	if(split[0].equals("pause")){
		    		flag=false;
		    	}
		    	if(flag){		    		
		    		lineinfo="pause "+split[1]+" "+split[2]+" "+split[3]+" "+split[4]+" "+split[5]+" "+split[6]+" "+split[7]+" "+split[8];
		    		BufferedReader reader1 = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));
			    	StringBuffer bf = new StringBuffer();
		    		while ((temp = reader1.readLine()) != null) {		            	           
		 	            temp=temp.trim();
		 	            String[] split1=temp.split(" ");		 	            
		 		    	if(!split1[7].equals(train)){          	
		 					bf.append(temp).append("\r\n");	
		 				}	 	            
		 	        }	    			    		
				    reader1.close();
				    FileWriter fileWriter = new FileWriter("runningtrain.txt");
				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);	        		  
				    bufferedWriter.write(bf.toString());
				    bufferedWriter.append(lineinfo).append("\r\n");
				    bufferedWriter.close();
				    fileWriter.close();
				    
				    FileWriter fileWriter1 = new FileWriter("pause.txt",true);
				    BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
				    lineinfo=train+" "+df.format(new Date());				    
				    bufferedWriter1.append(lineinfo).append("\r\n");
				    bufferedWriter1.close();
				    System.out.println("lineinfo:"+lineinfo);
				    fileWriter1.close();
		    	}
		    }
		    
		    
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
		return flag;
	}
	
	
	public boolean startTrain(String train){
		boolean flag=false;
		String temp="",lineinfo;
		String[] split=null;
		try{
	    	BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {
		    	split=temp.split(" ");
		    	if(split[7].equals(train)){
		    		flag=true;
		    		break;
		    	}
		     }		  		   
		    reader.close();
		    if(flag){
		    	if(split[0].equals("running")){
		    		flag=false;
		    	}
		    	if(flag){		    		
		    		lineinfo="running "+split[1]+" "+split[2]+" "+split[3]+" "+split[4]+" "+split[5]+" "+split[6]+" "+split[7]+" "+split[8];
		    		BufferedReader reader1 = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));
			    	StringBuffer bf1 = new StringBuffer();
		    		while ((temp = reader1.readLine()) != null) {		            	           
		 	            temp=temp.trim();
		 	            String[] split1=temp.split(" ");		 	            
		 		    	if(!split1[7].equals(train)){          	
		 					bf1.append(temp).append("\r\n");	
		 				}	 	            
		 	        }	    			    		
				    reader1.close();
				    FileWriter fileWriter = new FileWriter("runningtrain.txt");
				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);	        		  
				    bufferedWriter.write(bf1.toString());
				    bufferedWriter.append(lineinfo).append("\r\n");
				    bufferedWriter.close();
				    fileWriter.close();
				    
				    BufferedReader reader2 = new BufferedReader(new FileReader(pausefile.getAbsolutePath()));
				    StringBuffer bf2 = new StringBuffer();
				    while ((temp = reader2.readLine()) != null) {		            	           
		 	            temp=temp.trim();
		 	            String[] split2=temp.split(" ");		 	            
		 		    	if(!split2[0].equals(train)){          	
		 					bf2.append(temp).append("\r\n");	
		 				}	 	            
		 	        }
				    reader2.close();
				    FileWriter fileWriter2 = new FileWriter("pause.txt");
				    BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);	        		  
				    bufferedWriter2.write(bf2.toString());
				    bufferedWriter2.close();
				    fileWriter2.close();
		    	}
		    }
		    
		    
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
		return flag;
	}
	public String progress(String train){
		String percentage="0%",temp="";
		String status="running";
		String start="00:00",end="00:00";
		boolean flag=false;
		String[] split = null;
		try{
	    	BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
		    while ((temp = reader.readLine()) != null) {
		    	split=temp.split(" ");
		    	if(split[7].equals(train)){
		    		flag=true;
		    		break;
		    	}
		     }		  		   
		    reader.close();
		    
		    if(flag){
		    	String[] a=split[3].split("-");
	    		String[] b=split[4].split("-");
	    		String[] c=split[5].split("-");
	    		String[] d=split[6].split("-");
	    		if(a[2].equals("*")){		    					    			
	    			if(b[2].equals("*")){		    				
	    				if(c[2].equals("*")){		    					
	    					flag=false;
	    				}
	    				else
	    					start=c[2];		    						    					
	    			}
	    			else
	    				start=b[2];
	    		}
	    		else
	    			start=a[2];
	    		if(d[2].equals("*")){		    					    			
	    			if(c[2].equals("*")){		    				
	    				if(b[2].equals("*")){		    					
	    					flag=false;
	    				}
	    				else
	    					end=b[2];		    						    					
	    			}
	    			else
	    				end=c[2];
	    		}
	    		else
	    			end=d[2];
	    		NumberFormat nt = NumberFormat.getPercentInstance();
	    		nt.setMinimumFractionDigits(3);
	    		Date startdate=df.parse(start);
	    		Date enddate=df.parse(end);
	    		long intervalall=enddate.getTime()-startdate.getTime();
		    	if(split[0].equals(status)){//train is running		    		
		    		long now= df.parse(df.format(new Date())).getTime();
		    		long intervalpass=now-startdate.getTime();
		    		double percent=(double)intervalpass/(double)intervalall;		    		
		    		percentage=nt.format(percent);
		    	}
		    	else{//train pause
		    		BufferedReader reader1 = new BufferedReader(new FileReader(pausefile.getAbsolutePath()));		    	
				    while ((temp = reader1.readLine()) != null) {
				    	split=temp.split(" ");
				    	if(split[0].equals(train)){
				    		break;
				    	}
				     }
				    long now=df.parse(split[1]).getTime();
				    long intervalpass=now-startdate.getTime();
				    double percent=(double)intervalpass/(double)intervalall;		    		
		    		percentage=nt.format(percent);
				    reader1.close();
		    	}
		    }
		    
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
		
		
		return percentage;
	}
	
	/*train methods*/
	public String statusOfTrain(String train){
		String temp = null;
		String statusContent="Train: "+train+" has not been allocated";		    
		try{
		  BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
			 while ((temp = reader.readLine()) != null) {	    	
			    String[] split=temp.split(" ");
			    if(split[7].equals(train)){
			    	statusContent="Status of "+train+" is: "+split[0];
			    	break;
			    }		    	
			 }		  		   
			 reader.close();
		}catch(Exception ex){
		     ex.printStackTrace();
		}    
		if(train.equals(""))
			statusContent="Please choose a train!";
		return statusContent;
	}
	
	public String nextStopA(String train){
		String nextStopAContent="";
		String temp = null;		
		try{
			  BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
				 while ((temp = reader.readLine()) != null) {	    	
				    String[] split=temp.split(" ");
				    if(split[7].equals(train)){
				    	String[] a=split[3].split("-");
				    	if(a[1].equals("*")){
				    		nextStopAContent="   STOP A:  ----------------------";
				    	}
				    	else{
				    		long now=df.parse(df.format(new Date())).getTime();
				    		long stopa=df.parse(a[2]).getTime();
				    		if(now>stopa)
				    			nextStopAContent="   STOP A: "+train+" has passed STOP A";
				    		else
				    			nextStopAContent="   STOP A: "+train+" will arrive STOP A  at "+a[2];
				    	}				    	
				    	break;
				    }		    	
				 }		  		   
				 reader.close();
			}catch(Exception ex){
			     ex.printStackTrace();
			}
		
		return nextStopAContent;
	}
	
	public String nextStopB(String train){
		String nextStopBContent="";
		String temp = null;		
		try{
			  BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
				 while ((temp = reader.readLine()) != null) {	    	
				    String[] split=temp.split(" ");
				    if(split[7].equals(train)){
				    	String[] b=split[4].split("-");
				    	if(b[1].equals("*")){
				    		nextStopBContent="   STOP B:  ----------------------";
				    	}
				    	else{
				    		long now=df.parse(df.format(new Date())).getTime();
				    		long stopb=df.parse(b[2]).getTime();
				    		if(now>stopb)
				    			nextStopBContent="   STOP B: "+train+" has passed STOP B";
				    		else
				    			nextStopBContent="   STOP B: "+train+" will arrive STOP B  at "+b[2];
				    	}				    	
				    	break;
				    }		    	
				 }		  		   
				 reader.close();
			}catch(Exception ex){
			     ex.printStackTrace();
			}
		
		return nextStopBContent;
	}
	public String nextStopC(String train){
		String nextStopCContent="";
		String temp = null;		
		try{
			  BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
				 while ((temp = reader.readLine()) != null) {	    	
				    String[] split=temp.split(" ");
				    if(split[7].equals(train)){
				    	String[] c=split[5].split("-");
				    	if(c[1].equals("*")){
				    		nextStopCContent="   STOP C: ----------------------";
				    	}
				    	else{
				    		long now=df.parse(df.format(new Date())).getTime();
				    		long stopc=df.parse(c[2]).getTime();
				    		if(now>stopc)
				    			nextStopCContent="   STOP C: "+train+" has passed STOP C";
				    		else
				    			nextStopCContent="   STOP C: "+train+" will arrive STOP C  at "+c[2];
				    	}				    	
				    	break;
				    }		    	
				 }		  		   
				 reader.close();
			}catch(Exception ex){
			     ex.printStackTrace();
			}
		
		return nextStopCContent;
	}
	
	
	public String nextStopD(String train){
		String nextStopDContent="";
		String temp = null;		
		try{
			  BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));		    	
				 while ((temp = reader.readLine()) != null) {	    	
				    String[] split=temp.split(" ");
				    if(split[7].equals(train)){
				    	String[] d=split[6].split("-");
				    	if(d[1].equals("*")){
				    		nextStopDContent="   STOP D: ----------------------";
				    	}
				    	else{
				    		long now=df.parse(df.format(new Date())).getTime();
				    		long stopd=df.parse(d[2]).getTime();
				    		if(now>stopd)
				    			nextStopDContent="   STOP D: "+train+" has passed STOP D";
				    		else
				    			nextStopDContent="   STOP D: "+train+" will arrive STOP D  at "+d[2];
				    	}				    	
				    	break;
				    }		    	
				 }		  		   
				 reader.close();
			}catch(Exception ex){
			     ex.printStackTrace();
			}
		
		return nextStopDContent;
	}
	
	public void checkStart(){
		String temp = null;
		long starttime=0;
		try{
		  BufferedReader reader = new BufferedReader(new FileReader(assignfile.getAbsolutePath()));	
		  StringBuffer bf = new StringBuffer();
			 while ((temp = reader.readLine()) != null) {
				System.out.println("temp:"+temp);
			    String[] split=temp.split(" ");
			    for(int i=2;i<5;i++){
			    	String[] start=split[i].split("-");
			    	System.out.println("split:"+split[i]+" and i: "+i);
			    		if(!start[2].equals("*")){
			    			starttime=df.parse(start[2]).getTime();
			    			System.out.println("start2:"+start[2]);
			    			break;
			    		}
			    		else
			    			continue;
			    }
			    
			    long now = df.parse(df.format(new Date())).getTime();
				 if(now>starttime){
					 bf.append("running ").append(temp).append("\r\n");
					 System.out.println("starttime:"+starttime);
					 System.out.println("nowtime:"+now);
				 }
				
			    		    	
			 }
			 FileWriter fileWriter = new FileWriter("runningtrain.txt");
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 bufferedWriter.write(bf.toString());
			 bufferedWriter.close();
			 fileWriter.close(); 
			 reader.close();
			 
		}catch(Exception ex){
		     ex.printStackTrace();
		}	
	}
	
	
	public void checkEnd(){
		String temp = null;
		long endtime=0;
		try{
		  BufferedReader reader = new BufferedReader(new FileReader(assignfile.getAbsolutePath()));	
		  StringBuffer bf = new StringBuffer();
			 while ((temp = reader.readLine()) != null) {
				
			    String[] split=temp.split(" ");
			    for(int i=5;i>2;i--){			    	
			    	String[] end=split[i].split("-");
			    	if(!end[2].equals("*")){
			    		endtime=df.parse(end[2]).getTime();
			    		break;
			    	}
			    	else
			    		continue;
			    }
			    
			    long now = df.parse(df.format(new Date())).getTime();
				 if(now<endtime){
					 bf.append(temp).append("\r\n");	
				 }
				 		    	
			 }	
			 FileWriter fileWriter = new FileWriter("assign.txt");
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);	        		  
			 bufferedWriter.write(bf.toString());
			 bufferedWriter.close();
			 fileWriter.close();
			 reader.close();
		
			 
			 BufferedReader reader1 = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));	
			 StringBuffer bf1 = new StringBuffer();
				 while ((temp = reader1.readLine()) != null) {	    	
				    String[] split=temp.split(" ");
				    for(int i=6;i>3;i--){
				    	//System.out.println("in for loop, i is:"+i);
				    	String[] end=split[i].split("-");
				    	if(!end[2].equals("*")){
				    		endtime=df.parse(end[2]).getTime();
				    		//System.out.println("endtime:"+endtime);
				    		//System.out.println("end2:"+end[2]);
				    		break;
				    	}
				    	else
				    		continue;
				    }
				    
				    long now = df.parse(df.format(new Date())).getTime();
					 if(now<endtime){
						 bf1.append(temp).append("\r\n");
						 //System.out.println("endtime:"+endtime);
						 //System.out.println("now:"+now);
					 }
				    		    	
				 }	
				 FileWriter fileWriter1 = new FileWriter("runningtrain.txt");
				 BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);	        		  
				 bufferedWriter1.write(bf1.toString());
				 bufferedWriter1.close();
				 fileWriter1.close();
				 reader1.close();	 
			 
		}catch(Exception ex){
		     ex.printStackTrace();
		}
	}
}
