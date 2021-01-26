package com.cjsanon.thrillio.constants;

public enum BookGenre {
    ART("Art"),
    BIOGRAPHY("Biography"),
    CHILDREN("Children"),
    FICTION("Fiction"),
    HISTORY("History"),
    MYSTERY("Mystery"),
    PHILOSOPHY("Philosophy"),
    RELIGION("Religion"),
    ROMANCE("Romance"),
    SELF_HELP("Self help"),
    TECHNICAL("Technical");

    BookGenre(String name) {
        this.name = name;
    }
    private final String name;
    public String getName() {
        return name;
    }
}
