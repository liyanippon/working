package model.javabean;

/**
 * Created by admin on 2017/2/27.
 */

public class AccountReason {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountReason(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
