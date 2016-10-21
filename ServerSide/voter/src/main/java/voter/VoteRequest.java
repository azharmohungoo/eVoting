package voter;

/**
 * Created by Gift on 08/09/16.
 */
public class VoteRequest {

    String partyName;
    String voterID;
    String voterPassword;
    String voteType;

    public String getVoteType() {
        return voteType;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public String getVoterID() {
        return voterID;
    }

    public String getVoterPassword() {
        return voterPassword;
    }

    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }

    public void setVoterPassword(String voterPassword) {
        this.voterPassword = voterPassword;
    }


    public String  getPartyName()
    {
        return partyName;
    }

    public void setPartyName(String _partyName)
    {
        partyName = _partyName;
    }

}
