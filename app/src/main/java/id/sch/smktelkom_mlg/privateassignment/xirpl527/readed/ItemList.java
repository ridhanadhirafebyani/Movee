package id.sch.smktelkom_mlg.privateassignment.xirpl527.readed;

/**
 * Created by Ridha Nadhira Febyan on 14-May-17.
 */

public class ItemList {
    private String judul;
    private String desc;
    private String imageUrl;

    public ItemList(String judul, String desc, String imageUrl) {
        this.judul = judul;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }

    public String getJudul() {
        return judul;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
