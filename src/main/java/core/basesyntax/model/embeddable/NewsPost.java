package core.basesyntax.model.embeddable;

import java.time.LocalDate;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news_post")
public class NewsPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate postDate;
    private String topic;
    private String content;
    @Embedded
    private PostMetadata metadata;

    public NewsPost() {
    }

    public NewsPost(PostMetadata metadata) {
        this.metadata = metadata;
    }

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
