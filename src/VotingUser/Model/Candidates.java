package VotingUser.Model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Candidates {
    private String candidateName;
    private String candidateLastName;
    private String candidatePosition;
    private static  byte[] candidateImage;

    public Candidates(String candidateName, String candidateLastName, String candidatePosition, byte[] candidateImage) {
        this.candidateName = candidateName;
        this.candidateLastName = candidateLastName;
        this.candidatePosition = candidatePosition;
        this.candidateImage = candidateImage;
        
    }
    
    
    public Candidates() {
        // No-argument constructor code here (optional)
    }
    
    public static List<Candidates> groupCandidatesByPosition(List<Candidates> candidates, String position) {
        return candidates.stream()
                .filter(candidate -> candidate.getCandidatePosition().equals(position))
                .collect(Collectors.toList());
    }
    
    /*
    public static Map<String, List<Candidates>> groupCandidatesByPosition(List<Candidates> candidates) {
        return candidates.stream()
                .collect(Collectors.groupingBy(Candidates::getCandidatePosition));
    }*/
    public String getCandidateName() {
        return candidateName;
    }
    
    public String getCandidateLastName() {
        return candidateLastName;
    }

    public String getCandidatePosition() {
        return candidatePosition;
    }
    
    public static  byte[] getCandidateImage() {
        return candidateImage;
    }
    public void groupPosition() {
    	
    }
}

