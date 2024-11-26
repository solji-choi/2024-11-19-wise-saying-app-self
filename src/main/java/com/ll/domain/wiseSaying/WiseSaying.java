package com.ll.domain.wiseSaying;

class WiseSaying {
    int id;
    String content;
    String author;

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    int getId() {
        return id;
    }

    String getContent() {
        return content;
    }

    String getAuthor() {
        return author;
    }

    void setContent(String content) {
        this.content = content;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"content\": \"" + content + "\",\n" +
                "  \"author\": \"" + author + "\"\n" +
                "}";
    }
}