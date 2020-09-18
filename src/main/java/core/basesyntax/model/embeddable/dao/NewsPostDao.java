package core.basesyntax.model.embeddable.dao;

import java.util.List;
import core.basesyntax.model.embeddable.NewsPost;

public interface NewsPostDao {
    NewsPost save(NewsPost post);

    List<NewsPost> getWithMetadataLargerThan(long size);
}
