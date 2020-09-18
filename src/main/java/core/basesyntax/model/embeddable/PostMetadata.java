package core.basesyntax.model.embeddable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "size", column = @Column(name = "metadata_size")),
        @AttributeOverride(name = "hashSum", column = @Column(name = "metadata_hash_sum"))
})
public class PostMetadata {
    private long size; // in bytes
    private String hashSum;

    public PostMetadata() {
    }

    public PostMetadata(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getHashSum() {
        return hashSum;
    }

    public void setHashSum(String hashSum) {
        this.hashSum = hashSum;
    }
}
