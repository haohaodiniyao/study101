package com.good.study101;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
/**
 * 监听指定路径下文件的创建、修改和删除
 * @author yaokai
 *
 */
public class WatchProperties {

	public static void main(String[] args) {
		Path path = Paths.get("D:\\usr\\test123\\aac");
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
			WatchKey key = null;
			while(true){
				try {
					key = watchService.take();
					for(WatchEvent<?> event:key.pollEvents()){
						System.out.println(event.context()+"#"+event.kind()+"#"+event.count());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				boolean reset = key.reset();
				if(!reset)
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
