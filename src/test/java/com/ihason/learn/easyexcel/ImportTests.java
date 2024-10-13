package com.ihason.learn.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.ihason.learn.easyexcel.dto.ImportDto;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ImportTests {

    @Test
    public void read() throws IOException {
        String filePath = "classpath:data.xlsx";
        File file = ResourceUtils.getFile(filePath);

        List<ImportDto> dtos = new ArrayList<>();
        MyListener listener = new MyListener(dtos::addAll);
        EasyExcel.read(file, ImportDto.class, listener)
                .sheet()
                .doRead();

        // 写出
        String exportFileName = "/Users/hason/Documents/log/excel/data" + LocalDateTime.now() + ".xlsx";
        File exportFile = ResourceUtils.getFile(exportFileName);
        EasyExcel.write(exportFile, ImportDto.class)
                .sheet()
                .doWrite(dtos);

    }

    static class MyListener extends PageReadListener<ImportDto> {

        private final List<ImportDto> data = new ArrayList<>();

        public MyListener(Consumer<List<ImportDto>> consumer) {
            super(consumer, 1);
        }

        @Override
        public void invoke(ImportDto data, AnalysisContext context) {
            super.invoke(data, context);
        }

        @Override
        public void onException(Exception exception, AnalysisContext context) throws Exception {
            super.onException(exception, context);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            super.doAfterAllAnalysed(context);
        }
    }

}
