package deamon999.gmail.com.mytaxes;

public class Page {
    private int batchSize;
    private int totalItems;

    public int getBatchSize(){
        return batchSize;
    }
    public void setBatchSize(int input){
        this.batchSize = input;
    }
    public int getTotalItems(){
        return totalItems;
    }
    public void setTotalItems(int input){
        this.totalItems = input;
    }
}
