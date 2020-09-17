package core.basesyntax.model.embeddable;

import java.time.LocalDate;

public class NewsPost {
    private LocalDate postDate;
    private String topic;
    private String content;
    private PostMetadata metadata;

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PostMetadata metadata) {
        this.metadata = metadata;
    }
}
