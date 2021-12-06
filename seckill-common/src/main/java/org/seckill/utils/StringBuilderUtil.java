package org.seckill.utils;

/**
 * ThreadLocal缓存可重用的StringBuilder
 */
public class StringBuilderUtil {
    private static final ThreadLocal<StringBuilderHelper> threadLocalStringBuilderHelper = ThreadLocal.withInitial(StringBuilderHelper::new);

    public static StringBuilder getStringBuilder() {
        return threadLocalStringBuilderHelper.get().getStringBuilder();
    }

    static final class StringBuilderHelper {
        final StringBuilder sb;

        StringBuilderHelper() {
            sb = new StringBuilder();
        }

        StringBuilder getStringBuilder() {
            sb.setLength(0);
            return sb;
        }
    }
}
