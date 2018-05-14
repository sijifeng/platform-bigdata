package com.season.game.netty.http;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.stream.ChunkedInput;
import io.netty.util.CharsetUtil;

public class MyChunkedInput implements ChunkedInput<HttpContent>{
	
	LinkedBlockingQueue<HttpContent> queue = new LinkedBlockingQueue<HttpContent>();
	
	private int id = 0;
	

	@Override
	public boolean isEndOfInput() throws Exception {
		//return queue.isEmpty();
		return false;
	}

	@Override
	public void close() throws Exception {
	}

	@Override
	public HttpContent readChunk(ChannelHandlerContext ctx) throws Exception {
		return queue.poll();
	}

	@Override
	public HttpContent readChunk(ByteBufAllocator byteBufAllocator) throws Exception {
		return null;
	}

	@Override
	public long length() {
		return 0;
	}

	@Override
	public long progress() {
		return 0;
	}

	public void writeChunk() {
		ByteBuf buffer = Unpooled.copiedBuffer("xxx " + id + "\n" + new Date() + "\n", CharsetUtil.UTF_8);
		id++;
		queue.offer(new DefaultHttpContent(buffer));
	}
	
	

}
