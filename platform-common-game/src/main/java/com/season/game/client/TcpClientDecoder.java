package com.season.game.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import com.season.game.servlet.Response;

public class TcpClientDecoder extends ByteToMessageDecoder {

	
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // Wait until the length prefix is available.
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        
        // �����ʽ���ܳ� + requestId + responseContent
        
       int totalLength = in.readInt();
       if (in.readableBytes() < totalLength) {
    	   in.resetReaderIndex();
    	   return;
       }
       
       int requestId = in.readInt();
       int contentLength = totalLength - 4;
       byte[] contentBytes = new byte[contentLength];
       in.readBytes(contentBytes);
       Response response = new Response(requestId, contentBytes);
       out.add(response);
    }
}
