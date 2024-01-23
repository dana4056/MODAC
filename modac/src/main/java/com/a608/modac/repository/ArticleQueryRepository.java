package com.a608.modac.repository;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.QArticle;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import static com.a608.modac.model.article.ArticleResponse.*;

@Repository
public class ArticleQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QArticle qArticle = QArticle.article;

    public ArticleQueryRepository(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Page<ArticleInfoResponse> searchArticleByUser(final Long userSeq, final Long articleSeq, final Pageable pageable) {
        final List<Article> articles = jpaQueryFactory.selectFrom(qArticle)
                .where(
                        ltArticleSeq(articleSeq), // no-offset 방식 페이지네이션 처리
                        qArticle.user.seq.eq(userSeq)
                ).orderBy(qArticle.seq.desc())
                .limit(pageable.getPageSize())
                .fetch();

        final List<ArticleInfoResponse> responses = new ArrayList<>();

        for (Article article : articles) {
            responses.add(new ArticleInfoResponse(article));
        }

        return new PageImpl<>(responses);
    }

    // 마지막 페이지인지 확인하는 메서드
    private Slice<ArticleInfoResponse> checkLastPage(final List<ArticleInfoResponse> responses, final Pageable pageable) {
        boolean hasNext = false;

        if (responses.size() > pageable.getPageSize()) {
            hasNext = true;
            responses.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(responses, pageable, hasNext);
    }

    // no-offset 방식 처리하는 메서드
    private BooleanExpression ltArticleSeq(final Long articleSeq) {
        if (articleSeq == null) {
            return null;
        }

        return qArticle.seq.lt(articleSeq);
    }

}
