package se.lexicon.tor;

public enum Cash {
    _1kr(1),
    _5kr(5),
    _10kr(10),
    _20kr(20),
    _50kr(50),
    _100kr(100),
    _500kr(500),
    _1000kr(1000);

    public final int value;

    Cash(int value){
        this.value = value;
    }
}
