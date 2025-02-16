public class WordFrequency {
    private Key wordKey;
    private int frequency;

    public WordFrequency(String word) {
        this.wordKey = new Key(word);
        this.frequency = 1;
    }

    public void incrementFrequency() {
        this.frequency++;
    }

    public Key getKey(){
        return wordKey;
    }

    public String toString() {
        return wordKey.getWord() + ": " + frequency;
    }
}
