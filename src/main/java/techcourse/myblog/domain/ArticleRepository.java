package techcourse.myblog.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Repository
public class ArticleRepository {
    private List<Article> articles = new ArrayList<>();

    public Long addArticle(Article article) {
        this.articles.add(article);

        return article.getArticleId();
    }

    public Optional<Article> findArticleById(final Long articleId) {
        return articles.stream()
                .filter(x -> x.getArticleId().equals(articleId))
                .findAny();
    }

    public List<Article> findAll() {
        return articles;
    }

    public void deleteArticle(Long articleId) {
        findArticleById(articleId)
                .ifPresent(a -> findArticleIndexByArticle(a)
                        .ifPresent(i -> articles.remove(i)));
    }

    public void updateArticle(Long articleId, Article updatedArticle) {
        findArticleById(articleId)
                .ifPresent(a -> a.update(updatedArticle));
    }

    private OptionalInt findArticleIndexByArticle(Article article) {
        return IntStream.range(0, articles.size())
                .filter(i -> articles.get(i).hasSameArticleId(article))
                .findFirst();
    }
}
