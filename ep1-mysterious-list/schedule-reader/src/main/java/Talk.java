public class Talk {

    private String title;
    private String author;
    private String complexity;

    public Talk(String title, String author, String complexity) {
        this.title = title;
        this.author = author;
        this.complexity = complexity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getComplexity() {
        return complexity;
    }
}
