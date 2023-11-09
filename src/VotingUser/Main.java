package VotingUser;

import VotingUser.Model.ClientUser;
import VotingUser.View.VotingView;

import java.net.UnknownHostException;

public class Main {
	
	//ITO AY SA VOTER CLIENT
    public static void main(String[] args) throws UnknownHostException {
     Main main = new Main();
     main.startVotingApp();
    }

    private void startVotingApp() throws UnknownHostException {
        new VotingView(new ClientUser());
    }
}
