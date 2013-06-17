/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author PetrK
 */
public class CharacterEncodingFilter implements Filter {
    
    private String encoding;
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request != null) {
            request.setCharacterEncoding(encoding);
        }
        if (response != null) {
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
    
}
