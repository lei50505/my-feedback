package cn.rest.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import cn.rest.util.ResponseDto;

@Component
public class SecurityFilter implements Filter {
    
    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        log.info("SecurityFilter过滤前 " );
        if ("1".equals(request.getParameter("id"))) {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.OK.value());
            PrintWriter writer = response.getWriter();
            ResponseDto<Object> resDto = new ResponseDto<Object>(40000,"error",null);
            JSONObject jsonObject = JSONObject.fromObject(resDto);
            writer.write(jsonObject.toString());
            writer.flush();
            writer.close();
            return;
        }
        chain.doFilter(request, response);
        log.info("SecurityFilter过滤后 ");
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
