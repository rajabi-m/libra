package ir.mahdi.libra.model;

import lombok.Data;

@Data
public class Asset {
    private final String type;
    private final long id;
    private String title;

    public Asset() {
        type = this.getClass().getSimpleName();
        id = -1;
    }

    public Asset(String title) {
        type = this.getClass().getSimpleName();
        id = -1;
        this.title = title;
    }

    public Asset(long id, String title) {
        type = this.getClass().getSimpleName();
        this.id = id;
        this.title = title;
    }
}
