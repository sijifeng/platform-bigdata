package com.season.game.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import com.season.game.netty.tcp.TcpDecoder;
import com.season.game.netty.tcp.TcpEncoder;
import com.season.game.netty.tcp.TcpServerHandler;
import com.season.game.servlet.Servlet;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

	//private HttpServerHandler serverHandler;
	
	private Servlet servlet;
	
	// �Ƿ������ͷ�ʽ
	private boolean isChunked;
	
    public HttpServerInitializer(Servlet servlet, boolean isChunked){
    	this.servlet = servlet;
    	this.isChunked = isChunked;
    }
    
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        
        pipeline.addLast("encoder", new HttpResponseEncoder());
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        if (isChunked)
        	pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());

        // and then business logic.
        if (isChunked)
        	pipeline.addLast("handler", new HttpServerChunkedHandler());
        else
        	pipeline.addLast("handler", new HttpServerHandler(servlet));
    }
}
