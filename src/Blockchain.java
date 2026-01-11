import java.util.ArrayList;

public class Blockchain {

    //step 1: store blocks

    private ArrayList<Block> chain;

    //step2: constructor

    public Blockchain(){
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }

    //step 3: create genesis block

    private Block createGenesisBlock(){
        return new Block(0,"SYSTEM", "GENESIS", "0");
    }
    //step4: get last block

    public Block getLatestBlock(){
        return chain.get(chain.size()-1);
    }

    //step 5: add new block

    public void addBlock(Block newBlock){
        newBlock.previousHash = getLatestBlock().hash;
        newBlock.hash = newBlock.calculateHash();
        chain.add(newBlock);
    }
    //step 6: validate blockchain


    public boolean isChainValid(){
        for (int i = 1; i < chain.size() ; i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i-1);

            //check current hash
            if(! currentBlock.previousHash.equals(currentBlock.calculateHash())){
                return false;
            }
            //check previous hash
            if(! currentBlock.previousHash.equals(previousBlock.hash)){
                return false;
            }
        }
        return true;
    }
    //step 7: get full blockchain

    
    public ArrayList<Block> getChain(){
        return chain;
    }
}
