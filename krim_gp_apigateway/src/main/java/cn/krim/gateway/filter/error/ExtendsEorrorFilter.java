package cn.krim.gateway.filter.error;

import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author krim
 *
 */
@Component
public class ExtendsEorrorFilter extends SendErrorFilter {

	@Override
	public int filterOrder() {
		return 25;//It should be larger than MyEorrorFilter's filterOrder
	}

	@Override
	public String filterType() {
		return FilterConstants.ERROR_TYPE;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		ZuulFilter filter  = (ZuulFilter) ctx.get("failedFilter");
		if("post".equals(filter.filterType())){
			return true;
		}
		return false;
	}
	
	
}
