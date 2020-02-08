package net.work100.training.stage2.design.patterns.adapter;

/**
 * <p>Title: AdapterPatternDemo</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-adapter-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 14:20
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
