package ir.okandroid.mvp_simple_dictionary.models;

/**
 * Created by Aliyari on 08/11/2018.
 */

public class Items {

    int id;
    String word;
    String meaning;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String description) {
        this.meaning = description;
    }
}
