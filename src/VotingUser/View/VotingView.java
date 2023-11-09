package VotingUser.View;

import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import VotingUser.Model.Candidates;
import VotingUser.Model.ClientUser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JRadioButton;

public class VotingView extends JFrame {

	private JPanel contentPane;
	JLabel positionLabel;
	
	JPanel scrollPanel;
	
	public VotingView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 150, 1280, 800);
		this.setTitle("Vote");
		this.setResizable(false);
		getContentPane().setLayout(null);
				
		
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 1280, 800);
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setBackground(Color.white);
		
		JLabel electionLabel = new JLabel("Vote Wisely");
		electionLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		electionLabel.setBounds(477, 25, 240, 51);
		contentPane.add(electionLabel);
		
		positionLabel = new JLabel("President");
		positionLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 30));
		positionLabel.setBounds(29, 25, 240, 51);
		contentPane.add(positionLabel);
		
		

		scrollPanel = new JPanel();
		scrollPanel.setLayout(null);
		//scrollPanel.setBackground(Color.blue);
		scrollPanel.setPreferredSize(new Dimension(1160,  1500));
		
		
		JScrollPane votingScrollPane = new JScrollPane(scrollPanel);
		
		try {
			new ClientUser();
			addToPanel();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		ImageIcon candidate1Icon = new ImageIcon(Candidates.getCandidateImage()); // Replace with the actual path to your image
		
		// Start ng caniddate 1
		 
		JLabel candidate1Label = new JLabel();
		candidate1Label.setBounds(175, 40, 250, 250);
		candidate1Label.setIcon(resizeImageIconToLabelSize(candidate1Label, candidate1Icon));
		scrollPanel.add(candidate1Label);
		
		JRadioButton candidate1RadButton = new JRadioButton("Aundray Michael");
		candidate1RadButton.setFont(new Font("Sitka Text", Font.BOLD, 20));
		candidate1RadButton.setBounds(175, 300, 335, 44);
		scrollPanel.add(candidate1RadButton);
		// Hanggang dito candidate 1
		
		// Start ng caniddate 2
		JLabel candidate2Label = new JLabel();
		candidate2Label.setBounds(800, 40, 250, 250);
		candidate2Label.setIcon(resizeImageIconToLabelSize(candidate1Label, candidate1Icon));
		scrollPanel.add(candidate2Label);
		
		JRadioButton candidate2RadButton = new JRadioButton("Denmark Rodnie");
		candidate2RadButton.setFont(new Font("Sitka Text", Font.BOLD, 20));
		candidate2RadButton.setBounds(800, 300, 335, 44);
		scrollPanel.add(candidate2RadButton);
		// Hanggang dito candidate 2
		*/
		
		// ITO YUN SAMPLE LALAGYAN NUN MGA SESEND NG KABILANG DEVICE
		//addCandidate(scrollPanel, "Audrey Jerwin", 175, 465, resizeImageIconToLabelSize(candidate1Label, candidate1Icon));
		
		
		votingScrollPane.setBounds(0, 100, 1265, 800);
		contentPane.add(votingScrollPane);
		

		getContentPane().add(contentPane, BorderLayout.NORTH);		
		this.setVisible(true);
	}
	
	public void addToPanel() {
		List<Candidates> candidates = ClientUser.getCandidatesList();
		List<String> positions = ClientUser.getPositions();
		System.out.println("Hello");
		
		
		int xPosition = 175, yPosition = 300;
		int xPosition2 = 800, yPosition2 = 300;
		
		int j = 1;
		for(int i = 0; i < positions.size(); i++) {
			
			List<Candidates> positionSpecificCandidates = Candidates.groupCandidatesByPosition(candidates, positions.get(i));
			for (Candidates candidate : positionSpecificCandidates) {
			    System.out.println(candidate.getCandidateName() + ", " + candidate.getCandidatePosition());
			    
			    
			        if (j % 2 == 1 ) {
			            System.out.println("odd");
			            byte[] imageBytes = candidate.getCandidateImage();
			            ImageIcon candidate1Icon = new ImageIcon(imageBytes);
			            addCandidate(scrollPanel, candidate.getCandidateName(), candidate.getCandidateLastName(), xPosition, yPosition, candidate1Icon);
			            yPosition += 425;
			        } 
			        if(j % 2 == 0){
			        	System.out.println("even");
			        	byte[] imageBytes = candidate.getCandidateImage();
			            ImageIcon candidate1Icon = new ImageIcon(imageBytes);
			            addCandidate(scrollPanel, candidate.getCandidateName(), candidate.getCandidateLastName(), xPosition2, yPosition2, candidate1Icon);
			            yPosition2 += 425;
			        }
			        j++;
			        
			    }
			    
			
			
		}
	}
	
	public void addCandidate(JPanel scrollPanel, String name, String surname, int x1, int y1, ImageIcon icon) {
		JLabel candidateLabel = new JLabel();
	    candidateLabel.setBounds(x1, y1, 250, 250);
	    candidateLabel.setIcon(resizeImageIconToLabelSize(candidateLabel, icon));
	    scrollPanel.add(candidateLabel);
		
		JRadioButton candidateRadButton = new JRadioButton(name + " " +surname);
	    candidateRadButton.setFont(new Font("Sitka Text", Font.BOLD, 20));
	    candidateRadButton.setBounds(x1, (y1 + 260), 335, 44); // y + 260
	    scrollPanel.add(candidateRadButton);  
	}

	
	 public static ImageIcon resizeImageIconToLabelSize(JLabel label, ImageIcon icon) {
	        // Get the size of the JLabel
	        int labelWidth = label.getWidth();
	        int labelHeight = label.getHeight();

	        // Resize the ImageIcon to match the size of the JLabel
	        Image img = icon.getImage();
	        Image resizedImage = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
	        ImageIcon resizedIcon = new ImageIcon(resizedImage);

	        return resizedIcon;
	    }
}
 