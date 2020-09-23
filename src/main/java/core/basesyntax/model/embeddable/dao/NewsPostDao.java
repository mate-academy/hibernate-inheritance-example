package core.basesyntax.model.embeddable.dao;

import core.basesyntax.model.embeddable.NewsPost;
import java.util.List;

public interface NewsPostDao {
    NewsPost save (NewsPost newsPost);

    List<NewsPost> findBySizeGreaterThan(long size);
}
