package VotingUser;
import javax.swing.*;

import VotingUser.Model.ClientUser;
import VotingUser.View.VotingView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;

public class Main {
	
	//ITO AY SA VOTER CLIENT
    public static void main(String[] args) throws UnknownHostException {
     new VotingView();
    }	
}
