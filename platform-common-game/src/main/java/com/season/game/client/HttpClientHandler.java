package com.season.game.client;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.ClientCookieEncoder;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;
import io.netty.handler.codec.http.ClientCookieEncoder;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

public class HttpClientHandler extends SimpleChannelInboundHandler<HttpObject> {
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		ByteBuf bb = null;
		try {
			bb = Unpooled.wrappedBuffer(new String("id=1").getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// post�Ƿ��ܹ���ôд���ο�HttpUploadClient TODO
		HttpRequest request = new DefaultFullHttpRequest(
	            HttpVersion.HTTP_1_1, HttpMethod.GET, "http://127.0.0.1:8034/command=user!doSomething?id=1");
	    //System.out.println(uri.getRawPath());
	    // some headers
	    request.headers().set(HttpHeaders.Names.HOST, 8043);
	    // 1.1Ĭ�ϳ־����ӣ��ǳ־�����Ҫ��CLOSE
	    // 1.0Ĭ�Ϸǳ־����ӣ��־�����Ҫ��Keep-Alive
	    //request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
	    // ����Ǹ���ģ���ʾ�ͻ���֧�ֵı��루��Ϊ�ͻ��˷���˱���붼��netty�ṩ��api�ˣ���������Ӳ�������ν��
	    request.headers().set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP);

	    // Set some example cookies.
	    // some cookies
	    // cookie is also a part of headers
	   /* request.headers().set(
	            HttpHeaders.Names.COOKIE,
	            ClientCookieEncoder.encode(
	                    new DefaultCookie("my-cookie", "foo"),
	                    new DefaultCookie("another-cookie", "bar")));*/

	    // Send the HTTP request.
	    ctx.writeAndFlush(request);
	}
	
	
    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
    	
    	// Ϊ�λ�������Σ���Ϊ����ʱ��ֱ������HttpMessage��HttpContent
        if (msg instanceof HttpResponse) {
        	System.out.println("HttpResponse#######################");
            HttpResponse response = (HttpResponse) msg;

            // ״̬�룺200
            System.out.println("STATUS: " + response.getStatus());
            // Э��汾��1.1
            System.out.println("VERSION: " + response.getProtocolVersion());
            System.out.println();

            // ͷ�����ֲ�������
            if (!response.headers().isEmpty()) {
                for (String name: response.headers().names()) {
                    for (String value: response.headers().getAll(name)) {
                        System.out.println("HEADER: " + name + " = " + value);
                    }
                }
                System.out.println();
            }

            // ����ж�chunked�Ĵ����ɾ����о�һ��
            if (HttpHeaders.isTransferEncodingChunked(response)) {
                System.out.println("CHUNKED CONTENT {");
            } else {
                System.out.println("CONTENT {");
            }
        }
        if (msg instanceof HttpContent) {
        	System.out.println("HttpContent#######################");
            HttpContent content = (HttpContent) msg;

            System.out.print(content.content().toString(CharsetUtil.UTF_8));
            System.out.flush();

            if (content instanceof LastHttpContent) {
                System.out.println("} END OF CONTENT");
            }
        }
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
