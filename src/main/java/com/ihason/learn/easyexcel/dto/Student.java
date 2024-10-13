package com.ihason.learn.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * 展示写入动态列到 Excel 文件
 *
 * @author Hason
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Student {

    // index 表示顺序
    @JsonProperty(value = "姓名", index = 99)
    @ExcelProperty("姓名")
    private String name;

    @JsonProperty(value = "年龄", index = 90)
    @ExcelProperty("年龄")
    private Integer age;

    // 这个Map将存储动态列的数据，序列化JSON时总是排在最后
    private Map<String, Object> others;

    // 构造函数、getter 和 setter
    public Student(String name, Integer age, Map<String, Object> others) {
        this.name = name;
        this.age = age;
        this.others = others;
    }

    @JsonAnyGetter
    public Map<String, Object> getOthers() {
        return others;
    }

}
