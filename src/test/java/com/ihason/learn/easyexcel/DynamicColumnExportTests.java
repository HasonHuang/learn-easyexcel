package com.ihason.learn.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.ihason.learn.easyexcel.dto.Student;
import com.ihason.learn.easyexcel.support.EasyExcels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 示例：导出动态列
 *
 * @author Hason
 */
public class DynamicColumnExportTests {

    @Test
    void shouldExport_givenMap() {
        // 准备数据
        List<Student> students = createStudents();

        // 文件路径
        Path dir = Paths.get("/Users/hason/work/repositories/github/learn-easyexcel/output");
        String fileName = dir.resolve("students.xlsx").toString();

        // 写入操作
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, Student.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("学生信息").build();

            // 写入静态列
            excelWriter.write(students, writeSheet);

            // 写入动态列
            for (Student student : students) {
                int lastColumnIndex = 2; // 假设姓名和年龄占据前两列
                Map<String, Object> others = student.getOthers();
//                for (Map.Entry<String, Object> entry : others.entrySet()) {
//                    excelWriter.fill(new FillWrapper(lastColumnIndex++, entry.getKey(), entry.getValue()), FillConfig.builder().forceNewRow(false).build(), writeSheet);
//                    // 设置列宽
//                    excelWriter.merge(lastColumnIndex - 1, lastColumnIndex, 0, 0, true);
//                }
            }
        }
    }

    @Test
    void givenJson(@TempDir Path dir) {
        // 准备数据
        List<Student> students = createStudents();
        MapType mapType = JsonTestMapper.MAPPER.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
        CollectionType type = JsonTestMapper.MAPPER.getTypeFactory().constructCollectionType(List.class, mapType);
        List<Map<String, Object>> studentMaps = JsonTestMapper.MAPPER.convertValue(students, type);

        List<List<String>> headers = EasyExcels.generateHeadersFromFirstLine(studentMaps);
//        List<List<String>> headers = EasyExcels.generateHeadersFromAllLine(studentMaps);
//        List<Map<Integer, Object>> excelMaps = EasyExcels.convertToMaps(studentMaps, headers);
        List<List<Object>> excelMaps = EasyExcels.convertToArrays(studentMaps, headers);

        // 文件路径
//        Path dir = Paths.get("~/work/repositories/github/learn-easyexcel/output");
        String fileName = dir.resolve("students.xlsx").toString();
        System.out.println("Excel文件：" + fileName);

        try (ExcelWriter excelWriter = EasyExcel.write(fileName).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("学生信息").build();

            // 写入表头
            writeSheet.setHead(headers);

            // data
            excelWriter.write(excelMaps, writeSheet);
        }
    }

    private static List<Student> createStudents() {
        Map<String, Object> dynamicColumns = new HashMap<>();
        dynamicColumns.put("兴趣", "阅读");
        dynamicColumns.put("成绩", 95);
        dynamicColumns.put("English", 88);

        Map<String, Object> dynamicColumns2 = new HashMap<>();
        dynamicColumns2.put("兴趣", "打球");
        dynamicColumns2.put("语文", 86);
        dynamicColumns2.put("English", 90);

        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", 20, dynamicColumns));
        students.add(new Student("李四", 22, dynamicColumns2));
        return students;
    }

}
