package system;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Train extends Management{	
	JPanel pn,pc,ps,pc1,pc2;
	JComboBox<String> train;
	JButton ok,start,stop,msg;
	JLabel remainstoplabel,stopalabel,stopblabel,stopclabel,stopdlabel,statuslabel1,statuslabel2;
	
	public Train(){
		JFrame trainFrame=new JFrame("Train");		
		pn=new JPanel();
		pc=new JPanel(new GridLayout(0,1));
		pc1=new JPanel(new GridLayout(5,0));
		pc2=new JPanel();
		ps=new JPanel();
		trainFrame.add(pn,BorderLayout.NORTH);
		trainFrame.add(pc,BorderLayout.CENTER);
		trainFrame.add(ps,BorderLayout.SOUTH);
		train=new JComboBox<String>(trainlist);
		ok=new JButton("OK");
		start=new JButton("start");
		stop=new JButton("stop");
		msg=new JButton("send message");
		start.setEnabled(false);
		stop.setEnabled(false);
		msg.setEnabled(false);
		remainstoplabel=new JLabel("********   Remain stops and time are shown as follow   *******");
		stopalabel=new JLabel("   STOP A   ");
		stopblabel=new JLabel("   STOP B   ");
		stopclabel=new JLabel("   STOP C   ");
		stopdlabel=new JLabel("   STOP D   ");
		statuslabel1=new JLabel("*******  Status of train  RUNNING OR PAUSE  ******");
		statuslabel2=new JLabel("");
		pn.add(new JLabel("Choose a train: "));
		pn.add(train);
		pn.add(ok);
		pc1.add(remainstoplabel);
		pc1.add(stopalabel);
		pc1.add(stopblabel);
		pc1.add(stopclabel);
		pc1.add(stopdlabel);
		pc2.add(statuslabel1);
		pc2.add(statuslabel2);
		pc.add(pc1);
		pc.add(pc2);
		ps.add(start);
		ps.add(stop);
		ps.add(msg);
		trainFrame.setSize(350,400);
		trainFrame.setLocationRelativeTo(null);
		trainFrame.setResizable(false);
		trainFrame.setVisible(true);
		
		ok.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						String statusContent=statusOfTrain((String)train.getSelectedItem());
						statuslabel2.setText(statusContent);
						boolean flag=isRunning((String)train.getSelectedItem());
						if(flag){
							start.setEnabled(true);
							stop.setEnabled(true);
							msg.setEnabled(true);
							
							stopalabel.setText(nextStopA((String)train.getSelectedItem()));
							stopblabel.setText(nextStopB((String)train.getSelectedItem()));
							stopclabel.setText(nextStopC((String)train.getSelectedItem()));
							stopdlabel.setText(nextStopD((String)train.getSelectedItem()));
						}
						else{
							start.setEnabled(false);
							stop.setEnabled(false);
							msg.setEnabled(false);
							stopalabel.setText("   STOP A   ");
							stopblabel.setText("   STOP B   ");
							stopclabel.setText("   STOP C   ");
							stopdlabel.setText("   STOP D   ");
							JOptionPane.showMessageDialog(null, "This train has not been allocated", "Failed",JOptionPane.ERROR_MESSAGE);
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
							train.setSelectedIndex(0);
							statuslabel2.setText("");
						}
						else
							JOptionPane.showMessageDialog(null, "Sorry! This train can not start again", "Failed",JOptionPane.ERROR_MESSAGE);
						start.setEnabled(false);
						stop.setEnabled(false);
						msg.setEnabled(false);
						stopalabel.setText("   STOP A   ");
						stopblabel.setText("   STOP B   ");
						stopclabel.setText("   STOP C   ");
						stopdlabel.setText("   STOP D   ");
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
							train.setSelectedIndex(0);
							statuslabel2.setText("");
						}
						else
							JOptionPane.showMessageDialog(null, "Sorry! This train can not stop again", "Failed",JOptionPane.ERROR_MESSAGE);										
						start.setEnabled(false);
						stop.setEnabled(false);	
						msg.setEnabled(false);
						stopalabel.setText("   STOP A   ");
						stopblabel.setText("   STOP B   ");
						stopclabel.setText("   STOP C   ");
						stopdlabel.setText("   STOP D   ");
					}
				}
		);
		msg.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Message is sent!!");										
					}
				}
		);
	}
}
