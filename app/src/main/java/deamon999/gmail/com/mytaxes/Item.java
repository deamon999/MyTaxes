package deamon999.gmail.com.mytaxes;

public class Item {

    private String id;
    private String firstname;
    private String lastname;
    private String placeOfWork;
    private String position;
    private String linkPDF;

    private String comment;


    public String getId() {
        return id;
    }

    public void setId(String input) {
        this.id = input;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String input) {
        this.firstname = input;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String input) {
        this.lastname = input;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String input) {
        this.placeOfWork = input;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String input) {
        this.position = input;
    }

    public String getLinkPDF() {
        return linkPDF;
    }

    public void setLinkPDF(String input) {
        this.linkPDF = input;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
