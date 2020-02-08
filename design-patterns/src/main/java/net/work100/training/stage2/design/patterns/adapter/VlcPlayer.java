package net.work100.training.stage2.design.patterns.adapter;

/**
 * <p>Title: VlcPlayer</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-adapter-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 14:13
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name : " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // 什么也不做
    }
}
