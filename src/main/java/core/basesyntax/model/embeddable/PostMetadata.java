package core.basesyntax.model.embeddable;

public class PostMetadata {
    private long size; // in bytes
    private String MD5Sum;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMD5Sum() {
        return MD5Sum;
    }

    public void setMD5Sum(String MD5Sum) {
        this.MD5Sum = MD5Sum;
    }
}
