package net.work100.training.stage2.design.patterns.adapter;

/**
 * <p>Title: MediaPlayer</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-adapter-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 14:11
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface MediaPlayer {
    /**
     * 播放
     *
     * @param audioType 格式
     * @param fileName  文件名
     */
    void play(String audioType, String fileName);
}
