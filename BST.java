import java.io.PrintStream;

public class BST implements  WordCounter{

    private class TreeNode{
        private WordFrequency item;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(WordFrequency wordFrequency){
            this.item = wordFrequency;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public void insert(String w) {

    }

    @Override
    public WordFrequency search(String w) {
        return null;
    }

    @Override
    public void remove(String w) {

    }

    @Override
    public void load(String filename) {

    }

    @Override
    public int getNumTotalWords() {
        return 0;
    }

    @Override
    public int getNumDistinctWords() {
        return 0;
    }

    @Override
    public int getFrequency(String w) {
        return 0;
    }

    @Override
    public WordFrequency getMaxFrequency() {
        return null;
    }

    @Override
    public double getMeanFrequency() {
        return 0;
    }

    @Override
    public void addStopWord(String w) {

    }

    @Override
    public void removeStopWord(String w) {

    }

    @Override
    public void printTreeByWord(PrintStream stream) {

    }

    @Override
    public void printΤreeByFrequency(PrintStream stream) {

    }
}
