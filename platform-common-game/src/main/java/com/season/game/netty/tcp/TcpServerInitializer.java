package com.season.game.netty.tcp;

import com.season.game.servlet.Servlet;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;

public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {

	private Servlet servlet;
	
    public TcpServerInitializer(Servlet servlet){
    	this.servlet = servlet;
    }
    
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // Add the text line codec combination first,
        // �ָ�decoder, ByteBuf -> List<ByteBuf>����������һ�м���Ӧһ��ByteBuf��������ʵ����Ҫ�ָ
       // pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
          //      8192, Delimiters.lineDelimiter()));
        // the encoder and decoder are static as these are sharable
        
        pipeline.addLast("decoder", new TcpDecoder());
        pipeline.addLast("encoder", new TcpEncoder());

        // and then business logic.
        pipeline.addLast("handler", new TcpServerHandler(servlet));
    }
}
