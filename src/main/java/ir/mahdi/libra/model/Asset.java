package ir.mahdi.libra.model;

import lombok.Data;

@Data
public class Asset {
    private final String type;
    private final Long id;
    private String title;

    public Asset(){
        type = this.getClass().getSimpleName();
        id = null;
    }

    public Asset(String title){
        type = this.getClass().getSimpleName();
        id = null;
        this.title = title;
    }

    public Asset(Long id, String title){
        type = this.getClass().getSimpleName();
        this.id = id;
        this.title = title;
    }
}
