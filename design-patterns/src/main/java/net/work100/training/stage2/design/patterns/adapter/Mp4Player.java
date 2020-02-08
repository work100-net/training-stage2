package net.work100.training.stage2.design.patterns.adapter;

/**
 * <p>Title: Mp4Player</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-adapter-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 14:15
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // 什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name : " + fileName);
    }
}
