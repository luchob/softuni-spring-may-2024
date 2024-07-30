package bg.softuni.mobilele.model.dto;

import java.util.List;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedModel.PageMetadata;

public class PageData<T> {

  private List<T> content;
  private PageMetadata page;

  public List<T> getContent() {
    return content;
  }

  public PageData<T> setContent(List<T> content) {
    this.content = content;
    return this;
  }

  public PageMetadata getPage() {
    return page;
  }

  public PageData<T> setPage(PageMetadata page) {
    this.page = page;
    return this;
  }

  public boolean isFirst() {
    return page.number() == 0;
  }

  public boolean isLast() {
    return page.number() >= page.totalPages() - 1;
  }

  public boolean hasPrevious() {
    return !isFirst() && page.totalPages() > 1;
  }

  public boolean hasNext() {
    return !isLast() && page.totalPages() > 1;
  }
}
