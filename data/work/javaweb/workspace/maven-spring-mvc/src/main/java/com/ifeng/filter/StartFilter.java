package com.ifeng.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.java_websocket.WebSocketImpl;
import com.ifeng.socket.WsServer;

public class StartFilter implements Filter{
	public void destroy() {

    }

    public void init(FilterConfig arg0) throws ServletException {
        this.startWebsocketInstantMsg();
    }

    /**
     * 启动即时聊天服务
     */
    public void startWebsocketInstantMsg() {
        WebSocketImpl.DEBUG = false;
        WsServer s;
        s = new WsServer(8887);
        s.start();
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, javax.servlet.FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		this.startWebsocketInstantMsg();
	}
}
