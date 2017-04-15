package com.qunar.fresh2017.question2;

import com.google.common.base.Preconditions;
import com.google.common.base.StandardSystemProperty;

import java.io.*;
import java.net.URL;

/**
 * 文件读写工具类
 * Created by shuai.lv on 2017/2/22.
 */
public class IOUtils {


    public static final String LINE_SEPARATOR = StandardSystemProperty.LINE_SEPARATOR.value();
    public static final String DEFAULT_CHARSET = "UTF-8";

    public static void closeIgnoreException(Closeable... closeable) {
        for (Closeable c : closeable) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    // can not do anything
                }
            }
        }
    }

    public static <T> T readLines(URL url, LineProcessor<T> lineProcessor) throws IOException {
        return readLines(url, lineProcessor, DEFAULT_CHARSET);
    }

    public static <T> T readLines(URL url, LineProcessor<T> lineProcessor, String charset) throws IOException {
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(lineProcessor);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader((url.openStream()),
                    charset));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!lineProcessor.processLine(line)) {
                    break;
                }
            }
        } finally {
            IOUtils.closeIgnoreException(bufferedReader);
        }
        return lineProcessor.getResult();
    }

    public static void writeLines(String fileName, Iterable<String> lines) throws IOException {
        writeLines(fileName, lines, new Function<String, String>() {
            @Override
            public String apply(String line) {
                return line;
            }
        }, DEFAULT_CHARSET);
    }

    public static <F> void writeLines(String fileName, Iterable<F> lines, Function<F, String> function)
            throws IOException {
        writeLines(fileName, lines, function, DEFAULT_CHARSET);
    }

    public static <F> void writeLines(String fileName, Iterable<F> lines, Function<F, String> function, String charset)
            throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(IOUtils.class.getResource("/").getFile() + fileName);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
            for (F line : lines) {
                bufferedWriter.write(function.apply(line) + LINE_SEPARATOR);
            }
        } finally {
            IOUtils.closeIgnoreException(bufferedWriter);
        }
    }
}
