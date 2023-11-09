package VotingUser.Model;

import java.awt.FlowLayout;
import java.awt.Image;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//import VotingSystem.Model.Candidate;

public class ClientUser {
	static List<String> positions = new ArrayList<>();
	private static List<Candidates> candidatesList = new ArrayList<>();
	static Candidates candidates; 
	
    public ClientUser() throws UnknownHostException {
    	
    	System.out.println("Client User");
    	InetAddress localAddress = InetAddress.getLocalHost();
    	String localIP = localAddress.getHostAddress();
    	try {
			Socket socket = new Socket(localIP, 6969);
			
			receiveDifferentPosition(localIP, socket);
			
			receiveCandidateData(localIP, socket);
			
			//displayImages();
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// Read candidatePosition from the server
		
    	
        
        
    }
    // METHOD TO RECEIVE THE NAME, SURNAME, POSITION AND IMAGE
    private static void receiveCandidateData(String localIP, Socket socket) {
    	
        try {
        	
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String receivedData;
            
            
            while ((receivedData = in.readLine()) != null) {
                String[] dataParts = receivedData.split(",");
                if (dataParts.length == 4) {
                    String name = dataParts[0];
                    String surname = dataParts[1];
                    String position = dataParts[2];
                    String imageBase64 = dataParts[3];

                    byte[] imageBytes = Base64.getDecoder().decode(imageBase64);


                    Candidates candidate = new Candidates(name, surname, position, imageBytes);
                    candidatesList.add(candidate);

                    testDisplayImage(candidate.getCandidateImage());

                    
                }
            }
            /*
            // need to delete for test purposes
            for(int i = 0; i < candidatesList.size(); i++) {
            	testDisplayImage(candidatesList.get(i).getCandidateImage());
            }
           	*/
            
            
                    //socket.close();
                
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    // need to delete for test purposes
    private static void testDisplayImage(byte[] imageBytes) {
        // Create a JFrame
        JFrame frame = new JFrame("Test Display Image");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Get the Base64-encoded image from the candidate
            //String imageBase64 = candidate.getImageBase64();

            // Decode Base64 string to byte array
            //byte[] imageBytes = candidate.getCandidateImage() ;

            // Convert byte array to ImageIcon
            ImageIcon imageIcon = new ImageIcon(imageBytes);

            // Create JLabel to display the image
            JLabel imageLabel = new JLabel(imageIcon);
            frame.add(imageLabel);

            // Make the frame visible
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // need to delete for test purposes
    private static void displayImages() {
        // Create a JFrame
        JFrame frame = new JFrame("Candidate Images");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold images
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());

        // Iterate through candidatesList and display images
        for (Candidates candidate : candidatesList) {
        	
        	 
        	System.out.println(candidate);
             
            byte[] imageBytes = candidate.getCandidateImage();

            // Convert byte array to BufferedImage
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageBytes).getImage());
            Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Create JLabel to display the image
            JLabel imageLabel = new JLabel(scaledIcon);
            imagePanel.add(imageLabel);
        }

        // Add the imagePanel to the frame
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane);

        // Make the frame visible
        frame.setVisible(true);
    }

   
    
    public void receiveDifferentPosition(String localIP, Socket socket) {
        try {
        	//Socket socket = new Socket(localIP, 6969);
            BufferedReader newPositionReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String position;

            while ((position = newPositionReader.readLine()) != null) {
                if (position.equals("EndOfPositionData")) {
                    // Received the end of position data, exit the loop
                    break;
                }

                positions.add(position);
                //System.out.println(position);
            }
            
            //socket.close();
            //newPositionReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // List<Candidates> candidates = someObject.getCandidatesList(); >>> Replace someObject with the appropriate instance
    public static List<Candidates> getCandidatesList() {
        return candidatesList;
    }
    // List<String> positions = ClientUser.getPositions();
    public static List<String> getPositions() {
        return positions;
    }

}
