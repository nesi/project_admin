package nz.org.nesi.collaboration;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.service.AdviserService;

@Component
public class AdminFilter implements Filter{
	
	@Autowired
	private AdviserService adviserService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest sReq, ServletResponse sRes, FilterChain fc)
			throws IOException, ServletException {
		//getting token or eppn from tuakiri attribute and checking db to confirm whether it's an adviser
		try {
            HttpServletRequest request = (HttpServletRequest) sReq;
            Adviser adviser = adviserService.getAdviserByTuakiriOrEppn(request);
            boolean isAdviser = adviser == null ? false : true;
            request.setAttribute("isAdviser", isAdviser);
            if (isAdviser) {
                request.setAttribute("person", adviser);                
            }
        } catch (final Exception e) {
            System.out.println("Unexpected error in AdminFilter");
            return;
        }
        fc.doFilter(sReq, sRes);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}