package system;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Stop {
	JPanel pn,pc,pc1,pc2,pc3,pc4,ps;
	JLabel a,b,c,d;
	JButton refresh;
	
	public Stop(){
		JFrame stopFrame=new JFrame("Stop Station");
		pn=new JPanel();
		pc=new JPanel(new GridLayout(0,1));
		pc1=new JPanel();
		pc2=new JPanel();
		pc3=new JPanel();
		pc4=new JPanel();
		ps=new JPanel();
		refresh=new JButton("Refresh");
		pc.add(pc1);
		pc.add(pc2);
		pc.add(pc3);
		pc.add(pc4);
		pn.add(new JLabel("The upcoming train on each stop"));
		a=new JLabel("");
		b=new JLabel("");
		c=new JLabel("");
		d=new JLabel("");
		pc1.add(new JLabel("STOP A: "));
		pc1.add(a);
		pc2.add(new JLabel("STOP B: "));
		pc2.add(b);
		pc3.add(new JLabel("STOP C: "));
		pc3.add(c);
		pc4.add(new JLabel("STOP D: "));
		pc4.add(d);
		ps.add(refresh);
		stopFrame.add(pn,BorderLayout.NORTH);
		stopFrame.add(pc,BorderLayout.CENTER);
		stopFrame.add(ps,BorderLayout.SOUTH);	
		stopFrame.setSize(350,400);
		stopFrame.setLocationRelativeTo(null);
		stopFrame.setResizable(false);
		stopFrame.setVisible(true);
		
		refresh.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						a.setText(upcoming("STOP_A"));	
						b.setText(upcoming("STOP_B"));
						c.setText(upcoming("STOP_C"));
						d.setText(upcoming("STOP_D"));
					}
				}
		);
	}
	
	public String upcoming(String stop){
		
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		File runningtrainfile = new File("runningtrain.txt");
				
		String info="";
		String temp = null,stopa="",stopb="",stopc="",stopd="";	
		String[] split,each1,each2,each3,each4,each;
		long get;
		int index=0;
		try{
		  BufferedReader reader = new BufferedReader(new FileReader(runningtrainfile.getAbsolutePath()));
		  long now = df.parse(df.format(new Date())).getTime();
			 while ((temp = reader.readLine()) != null) {
				System.out.println("temp:"+temp);
			    split=temp.split(" ");
			    each1=split[3].split("-");
			    each2=split[4].split("-");
			    each3=split[5].split("-");
			    each4=split[6].split("-");

			    each=new String[]{each1[2]+"",each2[2]+"",each3[2]+"",each4[2]+""};			   
			    for(int i=0;i<each.length;i++){
			    	if(!(each[i].equals("*"))){			    		
			    		get=df.parse(each[i]).getTime();
			    		System.out.println(i+" get:"+get);
			    		System.out.println(i+" now:"+now);
			    		System.out.println("eachi:"+each[i]);
			    		if(now<get){
			    			index=i;
			    			break;
			    		}			    						    				
			    		else
			    			continue;
			    	}
			    	else
			    		continue;
			    }
			    switch(index){
			    case 0: stopa=stopa+split[7]+" - "+each1[2]+"      ";break;
			    case 1: stopb=stopb+split[7]+" - "+each2[2]+"      ";break;
			    case 2: stopc=stopc+split[7]+" - "+each3[2]+"      ";break;
			    case 3: stopd=stopd+split[7]+" - "+each4[2]+"      ";break;
			    default: info="error";break;
			    }
			    		    	
			 }		  		   
			 reader.close();
		}catch(Exception ex){
		     ex.printStackTrace();
		}    
		switch(stop){
		case "STOP_A":info=stopa;break;
		case "STOP_B":info=stopb;break;
		case "STOP_C":info=stopc;break;
		case "STOP_D":info=stopd;break;
		default: info="error";break;
		}
		System.out.println(stop+" info: "+info);
		return info;
	}
	
}
