package com.example.springBoot.utils;

public class PageUtils {

    /**
     * 根据传入的页数、每页上的最多记录数计算这一页面的开始位置 offset，最小为 0.
     *
     * @param pageNumber 页码 (从 1 开始)
     * @param pageSize   每页上的最多记录数
     * @return 开始的位置 offset
     */
    public static int offset(int pageNumber, int pageSize) {
        // 校正参数，pageNumber 从 1 开始，pageSize 最小为 1
        pageNumber = Math.max(1, pageNumber);
        pageSize   = Math.max(1, pageSize);

        int offset = (pageNumber-1) * pageSize; // 计算此页开始的位置 offset
        return offset;
    }
}
