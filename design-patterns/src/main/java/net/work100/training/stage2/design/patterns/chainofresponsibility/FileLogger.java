package net.work100.training.stage2.design.patterns.chainofresponsibility;

/**
 * <p>Title: FileLogger</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-chain-of-responsibility-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 15:39
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class FileLogger extends AbstractLogger {
    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
