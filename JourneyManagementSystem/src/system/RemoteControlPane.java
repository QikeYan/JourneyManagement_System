package system;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RemoteControlPane extends RemoteControl{
	JPanel pn,pc,ps;
	JButton create,change,check,assign,record;
	JTextArea timetableArea;
	JScrollPane scrollPane;
	String timetableContent;
	
	public RemoteControlPane(){
		JFrame frame=new JFrame("  Welcome to Remote control");
		pn=new JPanel();
		pc=new JPanel();
		ps=new JPanel();
		frame.add(pn,BorderLayout.NORTH);
		frame.add(pc,BorderLayout.CENTER);
		frame.add(ps,BorderLayout.SOUTH);
		pn.add(new JLabel("Timetable"));
		timetableContent=readTimetable();
		timetableArea= new JTextArea(timetableContent,15,45);
		timetableArea.setLineWrap(true);
		timetableArea.setEditable(false);
		timetableArea.setFont(new Font("Times New Roman", Font.BOLD, 14));
		timetableArea.setWrapStyleWord(true);
		scrollPane=new JScrollPane(timetableArea);
		pc.add(scrollPane);
		create=new JButton("Create");
		change=new JButton("Change");
		check=new JButton("Check");
		assign=new JButton("assign");
		record=new JButton("record");
		ps.add(create);
		ps.add(change);
		ps.add(check);
		ps.add(assign);
		ps.add(record);
		frame.setSize(580,400);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		create.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						create();											
					}
				}
		);
		change.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						change();			
					}
				}
		);
		check.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						check();											
					}
				}
		);
		assign.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						assign();			
						
					}
				}
		);
		record.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						record();						
						
					}
				}
		);
	}//constructor
	
	public void create(){
		JPanel pc,ps,pc1,pc2,pc3;
		JButton create,save;
		JTextField route,journey,stopa,stopb,stopc,stopd,timea,timeb,timec,timed;
		
		JFrame createFrame=new JFrame("create!");		
		createFrame.setSize(580,200);
		createFrame.setLocationRelativeTo(null);
		createFrame.setResizable(false);
		createFrame.setVisible(true);
		pc=new JPanel();
		ps=new JPanel();
		createFrame.add(pc,BorderLayout.CENTER);
		createFrame.add(ps,BorderLayout.SOUTH);
		route=new JTextField(12);
		journey=new JTextField(12);
		stopa=new JTextField(6);
		timea=new JTextField(6);
		stopb=new JTextField(6);
		timeb=new JTextField(6);
		stopc=new JTextField(6);
		timec=new JTextField(6);
		stopd=new JTextField(6);
		timed=new JTextField(6);
		pc1=new JPanel();
		pc2=new JPanel();
		pc3=new JPanel();
		pc1.add(new JLabel("Route"));
		pc1.add(route);
		pc1.add(new JLabel("Journey"));
		pc1.add(journey);
		pc2.add(new JLabel("STOP A"));
		pc2.add(stopa);
		pc2.add(new JLabel("TIME A"));
		pc2.add(timea);
		pc2.add(new JLabel("STOP B"));
		pc2.add(stopb);
		pc2.add(new JLabel("TIME B"));
		pc2.add(timeb);
		pc3.add(new JLabel("STOP C"));
		pc3.add(stopc);
		pc3.add(new JLabel("TIME C"));
		pc3.add(timec);
		pc3.add(new JLabel("STOP D"));
		pc3.add(stopd);	
		pc3.add(new JLabel("TIME D"));
		pc3.add(timed);
		pc.add(pc1);
		pc.add(pc2);
		pc.add(pc3);
		create=new JButton("continue create");
		save=new JButton("Close");
		ps.add(create);
		ps.add(save);
		create.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean flag=createRoute(route.getText(),journey.getText(),stopa.getText(),stopb.getText(),stopc.getText(),stopd.getText(),timea.getText(),timeb.getText(),timec.getText(),timed.getText());											
						if(flag){
							JOptionPane.showMessageDialog(null, "Successfully create");
							timetableArea.setText(readTimetable());
							route.setText("");
							journey.setText("");							
							stopa.setText("");
							stopb.setText("");
							stopc.setText("");
							stopd.setText("");
							timea.setText("");
							timeb.setText("");
							timec.setText("");
							timed.setText("");
						}
						else
							JOptionPane.showMessageDialog(null, "Sorry! route or journey can not be empty", "Failed",JOptionPane.ERROR_MESSAGE);
						
					}
				}
		);
		save.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						createFrame.dispose();											
					}
				}
		);
		
		
	}
	
	public void change(){
		JPanel pc,ps,pc1,pc2,pc3;
		JButton change,delete,save,close;
		JTextField route,journey,stopa,stopb,stopc,stopd,timea,timeb,timec,timed;
		
		JFrame changeFrame=new JFrame("change!");		
		changeFrame.setSize(580,200);
		changeFrame.setLocationRelativeTo(null);
		changeFrame.setResizable(false);
		changeFrame.setVisible(true);
		pc=new JPanel();
		ps=new JPanel();
		changeFrame.add(pc,BorderLayout.CENTER);
		changeFrame.add(ps,BorderLayout.SOUTH);
		route=new JTextField(10);
		journey=new JTextField(10);
		stopa=new JTextField(6);
		timea=new JTextField(6);
		stopb=new JTextField(6);
		timeb=new JTextField(6);
		stopc=new JTextField(6);
		timec=new JTextField(6);
		stopd=new JTextField(6);
		timed=new JTextField(6);
		stopa.setEditable(false);
		stopb.setEditable(false);
		stopc.setEditable(false);
		stopd.setEditable(false);
		timea.setEditable(false);
		timeb.setEditable(false);
		timec.setEditable(false);
		timed.setEditable(false);
		pc1=new JPanel();
		pc2=new JPanel();
		pc3=new JPanel();
		pc1.add(new JLabel("Route"));
		pc1.add(route);
		pc1.add(new JLabel("Journey"));
		pc1.add(journey);
		pc2.add(new JLabel("STOP A"));
		pc2.add(stopa);
		pc2.add(new JLabel("TIME A"));
		pc2.add(timea);
		pc2.add(new JLabel("STOP B"));
		pc2.add(stopb);
		pc2.add(new JLabel("TIME B"));
		pc2.add(timeb);
		pc3.add(new JLabel("STOP C"));
		pc3.add(stopc);
		pc3.add(new JLabel("TIME C"));
		pc3.add(timec);
		pc3.add(new JLabel("STOP D"));
		pc3.add(stopd);	
		pc3.add(new JLabel("TIME D"));
		pc3.add(timed);
		pc.add(pc1);
		pc.add(pc2);
		pc.add(pc3);
		
		change=new JButton("Change");
		delete=new JButton("Delete");
		save=new JButton("Save Change");
		close=new JButton("Close");
		ps.add(change);
		ps.add(save);
		ps.add(delete);
		ps.add(close);

		
		change.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {						
						boolean flag=changeRoute(route.getText(),journey.getText());
						if(flag){
							stopa.setEditable(true);
							stopb.setEditable(true);
							stopc.setEditable(true);
							stopd.setEditable(true);
							timea.setEditable(true);
							timeb.setEditable(true);
							timec.setEditable(true);
							timed.setEditable(true);
						}
						else
							JOptionPane.showMessageDialog(null, "This route can not be changed", "Sorry",JOptionPane.ERROR_MESSAGE);						
					}
				}
		);
		save.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean flag=saveChangeRoute(route.getText(),journey.getText(),stopa.getText(),stopb.getText(),stopc.getText(),stopd.getText(),timea.getText(),timeb.getText(),timec.getText(),timed.getText());										
						if(flag){
							route.setText("");
							journey.setText("");							
							stopa.setText("");
							stopb.setText("");
							stopc.setText("");
							stopd.setText("");
							timea.setText("");
							timeb.setText("");
							timec.setText("");
							timed.setText("");
							stopa.setEditable(false);
							stopb.setEditable(false);
							stopc.setEditable(false);
							stopd.setEditable(false);
							timea.setEditable(false);
							timeb.setEditable(false);
							timec.setEditable(false);
							timed.setEditable(false);
							JOptionPane.showMessageDialog(null, "Successfully changed");
							timetableArea.setText(readTimetable());
						}
						else
							JOptionPane.showMessageDialog(null, "Sorry", "Failed",JOptionPane.ERROR_MESSAGE);
					}
				}
		);
		delete.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean flag=deleteRoute(route.getText(),journey.getText());
						if(flag){
							JOptionPane.showMessageDialog(null, "Successfully deleted");
							timetableArea.setText(readTimetable());							
						}
						else
							JOptionPane.showMessageDialog(null, "This route is not exist", "Failed",JOptionPane.ERROR_MESSAGE);
						route.setText("");
						journey.setText("");
					}
				}
		);
		close.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						changeFrame.dispose();											
					}
				}
		);
	}
	
	public void check(){
		JPanel busytrainpane,freetrainpane,busydriverpane,freedriverpane,buttonpane;
		JLabel busytrainlabel,freetrainlabel,busydriverlabel,freedriverlabel;
		JButton back,refresh;
		
		JFrame checkFrame=new JFrame("checking!!");
		checkFrame.setLayout(new GridLayout(5,1));
		checkFrame.setSize(580,200);
		checkFrame.setLocationRelativeTo(null);
		checkFrame.setResizable(false);
		checkFrame.setVisible(true);
		busytrainpane=new JPanel();
		freetrainpane=new JPanel();
		busydriverpane=new JPanel();
		freedriverpane=new JPanel();
		buttonpane=new JPanel();
		back=new JButton("Back");
		refresh=new JButton("Refresh");
		buttonpane.add(refresh);
		buttonpane.add(back);
		checkFrame.add(busytrainpane);
		checkFrame.add(freetrainpane);
		checkFrame.add(busydriverpane);
		checkFrame.add(freedriverpane);
		checkFrame.add(buttonpane);
		busytrainlabel=new JLabel("Running trains are shown here.");
		freetrainlabel=new JLabel("Free trains are shown here.");
		busydriverlabel=new JLabel("Running drivers are shown here.");
		freedriverlabel=new JLabel("Free drivers are shown here.");
		busytrainpane.add(new Label("Busy trains: "));
		busytrainpane.add(busytrainlabel);
		freetrainpane.add(new Label("Free trains: "));
		freetrainpane.add(freetrainlabel);
		busydriverpane.add(new Label("Busy drivers: "));
		busydriverpane.add(busydriverlabel);
		freedriverpane.add(new Label("Free drivers: "));
		freedriverpane.add(freedriverlabel);
		refresh.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						busytrainlabel.setText(busytrain(trainlist));
						freetrainlabel.setText(freetrain(trainlist));
						busydriverlabel.setText(busydriver(driverlist));
						freedriverlabel.setText(freedriver(driverlist));
					}
				}
		);		
		back.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						checkFrame.dispose();											
					}
				}
		);
		
	}
	
	public void assign(){
		JPanel routepane,journeypane,trainpane,driverpane,buttonpane;
		JTextField route,journey;
		JButton assign;
		JComboBox<String> train,driver;		
		JFrame assignFrame=new JFrame("assign");
		assignFrame.setLayout(new GridLayout(6,1));
		assignFrame.setSize(280,380);
		assignFrame.setLocationRelativeTo(null);
		assignFrame.setResizable(false);
		assignFrame.setVisible(true);
		routepane=new JPanel();
		journeypane=new JPanel();
		trainpane=new JPanel();
		driverpane=new JPanel();
		buttonpane=new JPanel();
		route=new JTextField(6);
		journey=new JTextField(6);
		train=new JComboBox<String>(trainlist);
		driver=new JComboBox<String>(driverlist);
		routepane.add(new JLabel("Route: "));
		routepane.add(route);
		journeypane.add(new JLabel("Journey: "));
		journeypane.add(journey);
		trainpane.add(new JLabel("Train: "));
		trainpane.add(train);
		driverpane.add(new JLabel("Driver: "));
		driverpane.add(driver);
		assign=new JButton("Assign");
		buttonpane.add(assign);
		assignFrame.add(routepane);
		assignFrame.add(journeypane);
		assignFrame.add(trainpane);
		assignFrame.add(driverpane);
		assignFrame.add(buttonpane);
		
		assign.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {												
						boolean flag=assignTrainDriver(route.getText(),journey.getText(),(String)train.getSelectedItem(),(String)driver.getSelectedItem());
						if(flag){
							route.setText("");
							journey.setText("");
							train.setSelectedIndex(0);
							driver.setSelectedIndex(0);
						}
						else
							JOptionPane.showMessageDialog(null, "assign unsuccessfully", "Failed",JOptionPane.ERROR_MESSAGE);
																		
					}
				}
		);
	}
	
	public void record(){
		JPanel pn,pc;
		JTextArea record;
		JScrollPane scrollPane;
		
		JFrame recordFrame=new JFrame("Record");
		recordFrame.setSize(480,280);
		recordFrame.setLocationRelativeTo(null);
		recordFrame.setResizable(false);
		recordFrame.setVisible(true);
		
		pn=new JPanel();
		pc=new JPanel();
		record=new JTextArea(readRecord(),10,43);
		record.setLineWrap(true);
		record.setEditable(false);
		record.setFont(new Font("Times New Roman", Font.BOLD, 13));
		scrollPane=new JScrollPane(record);
		recordFrame.add(pn,BorderLayout.NORTH);
		recordFrame.add(pc,BorderLayout.CENTER);
		pn.add(new JLabel("Route                   Journet                          Train                        Driver"));
		pc.add(scrollPane);
		 
		
	}	
}
