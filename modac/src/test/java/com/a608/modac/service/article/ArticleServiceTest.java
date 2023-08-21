package com.a608.modac.service.article;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.category.Category;
import com.a608.modac.model.todo.Todo;
import com.a608.modac.model.user.User;

import com.a608.modac.repository.ArticleRepository;
import com.a608.modac.repository.TodoRepository;
import com.a608.modac.repository.UserRepository;
import com.a608.modac.service.ArticleServiceImpl;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @InjectMocks
    ArticleServiceImpl articleService;
    @Mock
    ArticleRepository articleRepository;
    @Mock
    TodoRepository todoRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ArticleResponse.ArticleInfo articleInfo;
    @Mock
    User user;
    @Mock
    Category category;
    @Mock
    Todo todo;
    @Mock
    Article article;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .seq(1L)
                .id("alkwen0996")
                .catSkin((byte) 1)
                .email("alkwen0996@naver.com")
                .nickname("쓰문")
                .password("1234")
                .build();

        category = new Category("알고리즘", "알고리즘 템플릿파일 경로");
        todo = Todo.builder()
                .category(category)
                .seq(1L)
                .status((byte) 1)
                .title("알고리즘")
                .totalSecond("25985900")
                .user(user)
                .build();
        article = Article.builder()
                .totalSecond("1000000")
                .user(user)
                .seq(1L)
                .title("게시글1")
                .category(category)
                .filepath("게시글파일경로")
                .viewCount(0)
                .likeCount(0)
                .build();

        articleInfo = new ArticleResponse.ArticleInfo(article);
    }

    @Nested
    @DisplayName("게시글 작성시")
    class RegisterArticle {
        @Test
        @DisplayName("게시글이 작성되었다면 주어진 게시글정보 반환")
        void saveArticleSuccess() {
            // given
            ArticleRequest articleRequest = new ArticleRequest(
                    user.getSeq(),
                    todo.getSeq(),
                    (byte) 1,
                    "게시글내용1");

            when(todoRepository.findById(todo.getSeq())).thenReturn(Optional.ofNullable(todo));
            when(userRepository.findById(user.getSeq())).thenReturn(Optional.ofNullable(user));
            when(articleRepository.save(any())).thenReturn(article);

            // when
            ArticleResponse.ArticleInfo registerArticle = articleService.createArticle(articleRequest);

            // then
            assertEquals(registerArticle.getTitle(), "게시글1");
            assertEquals(registerArticle.getSeq(), 1L);
        }
    }

    @Nested
    @DisplayName("게시글 조회시")
    class FindArticle {
        @Test
        @DisplayName("해당하는 게시글의 정보가 없다면 NoSuchElementException 발생.")
        void notValidArticleSeq() {
            //given
            Long seq = 3L;
            //when
            when(articleRepository.findById(seq)).thenThrow(new NoSuchElementException("NoArticle"));
            // then
            assertThrows(NoSuchElementException.class, () -> articleService.readArticleBySeq(seq));
        }

        @Test
        @DisplayName("해당하는 게시글의 정보가 있다면 게시글 정보 반환.")
        void findArticleSuccess() {
            //given
            Long seq = 1L;
            when(articleRepository.findById(seq)).thenReturn(Optional.ofNullable(article));
            //when
            ArticleResponse.ArticleInfo getArticleInfo = articleService.readArticleBySeq(seq);
            // then
            assertEquals(getArticleInfo.getSeq(), seq);
        }
    }

    @Nested
    @DisplayName("게시글 삭제시")
    class DeleteArticle {
        @Test
        @DisplayName("해당하는 게시글의 정보가 없다면 NoSuchElementException 발생.")
        void notValidArticleSeq() {
            //given
            Long seq = 3L;
            //when
            when(articleRepository.findById(seq)).thenThrow(new NoSuchElementException("NoArticle"));
            // then
            assertThrows(NoSuchElementException.class, () -> articleService.readArticleBySeq(seq));
        }

        @Test
        @DisplayName("해당하는 게시글의 정보가 있다면 게시글 정보 반환.")
        void findArticleSuccess() {
            //given
            Long seq = 1L;
            when(articleRepository.findById(seq)).thenReturn(Optional.ofNullable(article));
            //when
            ArticleResponse.ArticleInfo getArticleInfo = articleService.readArticleBySeq(seq);
            // then
            assertEquals(getArticleInfo.getSeq(), seq);
        }

        @Test
        @DisplayName("정상삭제되었다면 해당 게시글정보 조회시 NullPointerException 발생")
        void deleteArticleSuccess() {
            //given
            Long seq = 1L;
            when(articleRepository.findById(seq)).thenReturn(Optional.ofNullable(article));
            //when
            when(articleRepository.findById(seq)).thenThrow(new NoSuchElementException("NoArticle"));
            // then
            assertThrows(NoSuchElementException.class, () -> articleService.readArticleBySeq(article.getSeq()));
        }
    }

    @Nested
    @DisplayName("조회수 증가시")
    class ViewCount {
        @Test
        @DisplayName("해당하는 게시글의 정보가 없다면 NoSuchElementException 발생.")
        void notValidArticleSeq() {
            //given
            Long seq = 3L;
            //when
            when(articleRepository.findById(seq)).thenThrow(new NoSuchElementException("NoArticle"));
            // then
            assertThrows(NoSuchElementException.class, () -> articleService.readArticleBySeq(seq));
        }

        @Test
        @DisplayName("해당하는 게시글의 정보가 있다면 게시글 정보 반환.")
        void findArticleSuccess() {
            //given
            Long seq = 1L;
            when(articleRepository.findById(seq)).thenReturn(Optional.ofNullable(article));
            //when
            ArticleResponse.ArticleInfo getArticleInfo = articleService.readArticleBySeq(seq);
            // then
            assertEquals(getArticleInfo.getSeq(), seq);
        }

        @Test
        @DisplayName("해당하는 게시글의 정보가 있다면 조회수 증가")
        void upViewCountSuccess() {
            //given
            Long seq = 1L;
            String point = "30";
            when(articleRepository.findById(seq)).thenReturn(Optional.ofNullable(article));
            //when
            Optional<Article> byId = articleRepository.findById(seq);
            // then
            assertEquals(byId.get().getViewCount(), 1);
        }
    }

}
