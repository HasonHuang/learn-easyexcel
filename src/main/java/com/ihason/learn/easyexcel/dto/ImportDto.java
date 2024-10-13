package com.ihason.learn.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ImportDto {

    @ExcelProperty(value = "姓名", order = 2)
    private String name;

    @NumberFormat("#天")
    @ExcelProperty(value = "周期", order = 3)
    private Integer period;

    @ExcelProperty(value = "日期", order = 4)
    private LocalDate date;

    @ExcelProperty(value = "时间", order = 5)
    private LocalDateTime dateTime;

}
