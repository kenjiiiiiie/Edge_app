package app.helianthus.edge;

public class ListData {

    private String listNumber;
    private String listTitle;

    public ListData (String listNumber, String listTitle) {

        this.listNumber = listNumber;
        this.listTitle = listTitle;

    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getListNumber() {
        return listNumber;
    }

    public void setListNumber(String listNumber) {
        this.listNumber = listNumber;
    }
}
