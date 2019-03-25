package system;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ManagementPane extends Management{
	JPanel pn,pc,ps;
	JButton ok,start,stop,refresh;
	JComboBox<String> train;
	JLabel runningtrainlabel,percentagelabel;
	JTextArea runningTrainArea;
	JScrollPane scrollPane;
	String runningTrainContent;
	
	public ManagementPane(){
		JFrame managemengFrame=new JFrame("Welcome to Management system");
		pn=new JPanel();
		pc=new JPanel();
		ps=new JPanel();
		managemengFrame.add(pn,BorderLayout.NORTH);
		managemengFrame.add(pc,BorderLayout.CENTER);
		managemengFrame.add(ps,BorderLayout.SOUTH);
		runningtrainlabel= new JLabel("Running train:");		
		percentagelabel= new JLabel("please input a train name, and click ok button");
		runningTrainContent=readRunningTrain();
		runningTrainArea= new JTextArea(runningTrainContent,15,48);
		runningTrainArea.setLineWrap(true);
		runningTrainArea.setEditable(false);
		scrollPane=new JScrollPane(runningTrainArea);		
		pn.add(new JLabel("*****This train has run (%):"));
		pn.add(percentagelabel);
		pn.add(new JLabel("*****"));
		pc.add(runningtrainlabel);
		pc.add(scrollPane);
		ok=new JButton("OK");
		start=new JButton("Start");
		stop=new JButton("Stop");
		refresh=new JButton("Refresh");
		start.setEnabled(false);
		stop.setEnabled(false);
		train=new JComboBox<String>(trainlist);
		ps.add(new JLabel("Train:"));
		ps.add(train);
		ps.add(ok);
		ps.add(start);
		ps.add(stop);
		ps.add(refresh);
		managemengFrame.setSize(580,400);
		managemengFrame.setLocationRelativeTo(null);
		managemengFrame.setResizable(false);
		managemengFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managemengFrame.setVisible(true);
		
		ok.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean flag=isRunning((String)train.getSelectedItem());
						if(flag){
							start.setEnabled(true);
							stop.setEnabled(true);
							percentagelabel.setText(progress((String)train.getSelectedItem()));
							runningTrainArea.setText(readRunningTrain());
						}
						else{
							JOptionPane.showMessageDialog(null, "Sorry! No this train!", "Failed",JOptionPane.ERROR_MESSAGE);
							train.setSelectedIndex(0);
							percentagelabel.setText("No this train!!!");
							start.setEnabled(false);
							stop.setEnabled(false);	
						}											
					}
				}
		);
		start.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean flag=startTrain((String)train.getSelectedItem());
						if(flag){
							JOptionPane.showMessageDialog(null, "Successfully start");
							runningTrainArea.setText(readRunningTrain());
							train.setSelectedIndex(0);
						}
						else							
							JOptionPane.showMessageDialog(null, "Sorry! This train can not start again", "Failed",JOptionPane.ERROR_MESSAGE);												
						start.setEnabled(false);
						stop.setEnabled(false);		
					}
				}
		);
		stop.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean flag=stopTrain((String)train.getSelectedItem());
						if(flag){							
							JOptionPane.showMessageDialog(null, "Successfully stop");
							runningTrainArea.setText(readRunningTrain());
							train.setSelectedIndex(0);
						}
						else
							JOptionPane.showMessageDialog(null, "Sorry! This train can not stop again", "Failed",JOptionPane.ERROR_MESSAGE);										
						start.setEnabled(false);
						stop.setEnabled(false);
															
					}
				}
		);
		refresh.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						checkStart();
						checkEnd();
						runningTrainArea.setText("");
						runningTrainArea.setText(readRunningTrain());
															
					}
				}
		);
	}
}
