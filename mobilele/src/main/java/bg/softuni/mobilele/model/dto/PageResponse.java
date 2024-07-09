package bg.softuni.mobilele.model.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.data.web.PagedModel.PageMetadata;

public class PageResponse<T> {
  private List<T> content;
  private PageMetadata page;

  @NotNull
  public List<T> getContent() {
    return content != null ? content : List.of();
  }

  public PageResponse<T> setContent(List<T> content) {
    this.content = content;
    return this;
  }

  public PageMetadata getPage() {
    return page;
  }

  public PageResponse<T> setPage(PageMetadata page) {
    this.page = page;
    return this;
  }
}
