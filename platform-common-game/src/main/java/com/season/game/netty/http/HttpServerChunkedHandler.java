package com.season.game.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.stream.ChunkedWriteHandler;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpServerChunkedHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg instanceof HttpRequest) {
            // HttpRequest httpRequest = (HttpRequest) msg;

            HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            response.headers().set(HttpHeaders.Names.TRANSFER_ENCODING, HttpHeaders.Values.CHUNKED);

            // Write the initial line and the header.
            ctx.writeAndFlush(response);

            MyChunkedInput input = new MyChunkedInput();
            ctx.write(input);

            while (true) {
                // ����chunked��Ϣ

                // ����1��ֱ�ӷ���httpContent
                //ByteBuf buffer = Unpooled.copiedBuffer("this is a chunk", CharsetUtil.UTF_8);
                //ctx.writeAndFlush(new DefaultHttpContent(buffer));
                input.writeChunk();
                ((ChunkedWriteHandler) ctx.channel().pipeline().get("chunkedWriter")).resumeTransfer();

                Thread.sleep(10000);
            }

        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
