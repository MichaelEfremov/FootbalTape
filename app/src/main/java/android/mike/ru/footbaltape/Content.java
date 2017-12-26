package android.mike.ru.footbaltape;

/**
 * Created by Michael on 26.12.2017.
 */

public class Content {

    private String title;
    private String description;
    private String url;

    public Content(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

