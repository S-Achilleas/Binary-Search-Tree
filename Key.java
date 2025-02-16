public class Key {
    private String word;

    public Key(String key) {
        this.word = key;
    }

    public String getWord() {
        return word;
    }

    public boolean equals(Key w) {
        return this.word.equals(w.getWord());
    }


    public boolean less(Key w) {
        return  this.word.compareTo(w.getWord()) < 0;
    }
}

