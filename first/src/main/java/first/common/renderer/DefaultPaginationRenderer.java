package first.common.renderer;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class DefaultPaginationRenderer extends AbstractPaginationRenderer {
	 
	public DefaultPaginationRenderer() {
		firstPageLabel = "<li class=\"paginate_button previous\" tabindex=\\\"0\\\"><a href=\"#\" onclick=\"{0}({1}); return false;\"><<</a></li>"; 
		previousPageLabel = "<li class=\"paginate_button previous\" tabindex=\"0\"><a href=\"#\" onclick=\"{0}({1}); return false;\"><</a></li>";
		currentPageLabel = "<li class=\"paginate_button active\" tabindex=\"0\"><a href=\"#\">{0}</a></li>";
		otherPageLabel = "<li class=\"paginate_button\" tabindex=\"0\"><a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel = "<li class=\"paginate_button next\" tabindex=\"0\"><a href=\"#\" onclick=\"{0}({1}); return false;\">></a></li>";
		lastPageLabel = "<li class=\"paginate_button next\" tabindex=\\\"0\\\"><a href=\"#\" onclick=\"{0}({1}); return false;\">>></a></li>";
	}
}
