package net.work100.training.stage2.design.patterns.adapter;

/**
 * <p>Title: AdvancedMediaPlayer</p>
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
public interface AdvancedMediaPlayer {
    /**
     * 播放VLC
     *
     * @param fileName 文件名
     */
    void playVlc(String fileName);

    /**
     * 播放MP4
     *
     * @param fileName 文件名
     */
    void playMp4(String fileName);
}
