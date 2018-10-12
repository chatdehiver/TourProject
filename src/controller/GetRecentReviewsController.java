package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ListVO;
import service.ReviewService;

public class GetRecentReviewsController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo = request.getParameter("PageNo");
		ListVO rlist = ReviewService.getInstance().getRecentReviews(pageNo);
		request.setAttribute("rlist", rlist);
		return new ModelAndView("tabResultIndex.jsp");
	}

}