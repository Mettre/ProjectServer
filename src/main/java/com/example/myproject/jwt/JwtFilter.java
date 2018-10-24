package com.example.myproject.jwt;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.example.myproject.constant.CommonConstant;
import com.example.myproject.exception.ErrorCode;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.utils.BigDecimalUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;

        Result<Object> result = new Result<>();
        SecretKey key = BigDecimalUtils.generalKey();
        //客户端将token封装在请求头中，格式为（Bearer后加空格）：Authorization：Bearer +token
        final String authHeader = request.getHeader(CommonConstant.AUTHORITIES);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            result = new ResultUtil<Object>().setNotLoggedInMsg();
        } else {
            try {
                //去除Bearer 后部分
                final String jwt = authHeader.substring(7);
                //解密token，拿到里面的对象claims
                final Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
                //将对象传递给下一个请求
                request.setAttribute("claims", claims);
                result.setCode(Integer.parseInt(ErrorCode.UNKNOW));
            } catch (Exception e) {
                result = new ResultUtil<Object>().setAuthenticationFailureMsg();
            }
        }

        if (result.getCode() == Integer.parseInt(ErrorCode.NOTLOGGEDIN)) {// 验证失败
            PrintWriter writer = null;
            OutputStreamWriter osw = null;
            try {
                osw = new OutputStreamWriter(res.getOutputStream(), "UTF-8");
                writer = new PrintWriter(osw, true);
                String jsonStr = new ObjectMapper().writeValueAsString(result);
                writer.write(jsonStr);
                writer.flush();
                writer.close();
                osw.close();
            } catch (UnsupportedEncodingException e) {
                logger.error("过滤器返回信息失败:" + e.getMessage(), e);
            } catch (IOException e) {
                logger.error("过滤器返回信息失败:" + e.getMessage(), e);
            } finally {
                if (null != writer) {
                    writer.close();
                }
                if (null != osw) {
                    osw.close();
                }
            }
            return;
        }

        chain.doFilter(req, res);
    }

}