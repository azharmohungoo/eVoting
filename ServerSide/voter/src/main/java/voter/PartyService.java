package voter;


public class PartyService
{
    private String partyId;
    private String password;
    private String partyName;
    private int nationalVoteCount = 0;
    private int provincialVoteCount = 0;
    private String blockchainNodeAddress;
    private String ipAddress;
    private String partyDescription;
    private String imgURL;

    public PartyService() {
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public int getNationalVoteCount() {
        return nationalVoteCount;
    }

    public void setNationalVoteCount(int nationalVoteCount) {
        this.nationalVoteCount = nationalVoteCount;
    }

    public int getProvincialVoteCount() {
        return provincialVoteCount;
    }

    public void setProvincialVoteCount(int provincialVoteCount) {
        this.provincialVoteCount = provincialVoteCount;
    }

    public String getBlockchainNodeAddress() {
        return blockchainNodeAddress;
    }

    public void setBlockchainNodeAddress(String blockchainNodeAddress) {
        this.blockchainNodeAddress = blockchainNodeAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPartyDescription() {
        return partyDescription;
    }

    public void setPartyDescription(String partyDescription) {
        this.partyDescription = partyDescription;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}