package deamon999.gmail.com.mytaxes;

import java.util.ArrayList;

public class TaxesResualt {
    private Page page;
    private ArrayList<Item> items;

    public Page getPage() {
        return page;
    }

    public void setPage(Page input) {
        this.page = input;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> input) {
        this.items = input;
    }
}
