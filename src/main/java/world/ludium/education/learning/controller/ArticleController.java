package world.ludium.education.learning.controller;

import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ludium.education.learning.service.EnhancedArticleService;
import world.ludium.education.util.ResponseUtil;

@RestController
@RequestMapping(value = "/article", produces = "application/json")
public class ArticleController {

  private final EnhancedArticleService articleService;
  private final ResponseUtil responseUtil;

  public ArticleController(EnhancedArticleService articleService,
      ResponseUtil responseUtil) {
    this.articleService = articleService;
    this.responseUtil = responseUtil;
  }

  @GetMapping("/{articleId}")
  public ResponseEntity<Object> getArticle(@PathVariable UUID articleId) {
    try {
      return ResponseEntity.ok(articleService.getArticle(articleId));
    } catch (NoSuchElementException nse) {
      return responseUtil.getNoSuchElementExceptionMessage("아티클 데이터가 없습니다.", nse.getMessage());
    } catch (Exception e) {
      return responseUtil.getExceptionMessage("아티클을 조회하는 중에 에러가 발생했습니다.", e.getMessage());
    }
  }
}