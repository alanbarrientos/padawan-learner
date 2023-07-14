package codes.balan.englishsubtitleprocessor;

public class Word {
    private String word ;
    private TypeWord typeWord;

    public Word(String word, TypeWord typeWord) {
        this.word = word;
        this.typeWord = typeWord;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public TypeWord getTypeWord() {
        return typeWord;
    }

    public void setTypeWord(TypeWord typeWord) {
        this.typeWord = typeWord;
    }
}
