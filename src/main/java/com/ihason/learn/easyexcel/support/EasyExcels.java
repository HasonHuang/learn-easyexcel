package com.ihason.learn.easyexcel.support;

import java.util.*;
import java.util.stream.Collectors;

public class EasyExcels {

    public static List<Map<Integer, Object>> convertToMaps(List<Map<String, Object>> data, List<List<String>> includeHeaders) {
        List<Map<Integer, Object>> result = new ArrayList<>(data.size());

        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> row = data.get(i);
            Map<Integer, Object> newRow = new LinkedHashMap<>();
            result.add(newRow);
            // 将row按headers的顺序提取值并放进Map<Integer,Object>里
            for (int j = 0; j < includeHeaders.size(); j++) {
                String header = includeHeaders.get(j).get(0);
                Object value = row.get(header);
                newRow.put(j, value);
            }
        }

        return result;
    }

    /**
     * 将源数据转换为二维数组。不在 {@code includeHeaders} 里的属性将被忽略，不会转换。
     *
     * @param data 源数据
     * @param includeHeaders ☞要转换的属性。
     * @return 能被 EasyExcel 识别的二维数组数据
     */
    public static List<List<Object>> convertToArrays(List<Map<String, Object>> data, List<List<String>> includeHeaders) {
        List<List<Object>> result = new ArrayList<>(data.size());

        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> row = data.get(i);
            List<Object> newRow = new ArrayList<>(includeHeaders.size());
            result.add(newRow);
            // 将row按headers的顺序提取值并放进Map<Integer,Object>里
            for (List<String> headers : includeHeaders) {
                String header = headers.get(0);
                newRow.add(row.get(header));
            }
        }

        return result;
    }

    public static List<List<String>> generateHeadersFromFirstLine(List<Map<String, Object>> data) {
        // 从第一行数据提取表头
        Map<String, Object> first = data.get(0);
        return first.keySet()
                .stream()
                .map(Collections::singletonList)
                .collect(Collectors.toList());
    }

    public static List<List<String>> generateHeadersFromAllLine(List<Map<String, Object>> data) {
        // 从每一行数据提取表头
        Set<String> distinctHeaders = new LinkedHashSet<>();
        for (Map<String, Object> row : data) {
            distinctHeaders.addAll(row.keySet());
        }
        return distinctHeaders.stream()
                .map(Collections::singletonList)
                .collect(Collectors.toList());
    }


}
