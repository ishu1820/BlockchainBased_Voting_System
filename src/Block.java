import java.security.MessageDigest;   //used to create SHA-256 hash
import java.util.Date;                 //gives current time

public class Block {
    //step 1: declare variables
    public int index;
    public long timestamp;
    public String voterId;
    public String candidate;
    public String previousHash;
    public String hash;

    //step2: constructor
    public Block(int index, String voterId, String candidate, String previousHash){
        this.index = index;
        this.voterId = voterId;
        this.candidate = candidate;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //step 3: hash calculation method
    public  String calculateHash(){
        String dataToHash = index + voterId + candidate + timestamp + previousHash;
        return applySHA256(dataToHash);
    }

    //step 4: SHA-256 logic
    private String applySHA256(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA_256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for(byte b : hashBytes){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length()==1){
                    hexString.append('0');
                    hexString.append(hex);
                }
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
